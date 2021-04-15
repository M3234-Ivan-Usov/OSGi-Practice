package ru.ifmo.rain.stat;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import ru.ifmo.rain.viewer.NewsViewer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.BreakIterator;
import java.util.*;
import java.util.stream.Collectors;

public class NewsStat implements BundleActivator {
    private Set<String> skippers;
    private BundleContext context;

    public NewsStat() {}

    public void stat() throws InvalidSyntaxException {
        Map<String, NewsViewer> viewers = getAvailableServices();
        if (viewers.size() == 0) {
            System.out.println("No available services");
        }
        else {
            viewers.values().forEach(this::dumpViewer);
        }
    }

    public void stat(String viewerName) throws InvalidSyntaxException {
        stat(viewerName, 10);
    }

    public void stat(String viewerName, int limit) throws InvalidSyntaxException {
        NewsViewer viewer = getAvailableServices().get(viewerName);
        if (viewer == null) {
            System.err.println("No such service: " + viewerName);
            return;
        }
        try {
            List<String> headlines = viewer.getHeadlines();
            Map<String, Integer> stat = new HashMap<>();
            Locale locale = new Locale("ru");
            BreakIterator iterator = BreakIterator.getWordInstance(locale);
            for (String headline: headlines) {
                iterator.setText(headline);
                for (int begin = iterator.first(), end = iterator.next();
                     end != BreakIterator.DONE; begin = end, end = iterator.next()) {
                    String word = headline.substring(begin, end).toLowerCase(locale);
                    if (word.matches("[а-я]+") && !skippers.contains(word)) {
                        Integer counter = stat.getOrDefault(word, 0);
                        stat.put(word, ++counter);
                    }
                }
            }
            stat.entrySet().stream()
                    .sorted((x, y) -> y.getValue() - x.getValue())
                    .limit(limit).collect(Collectors.toList()).forEach(this::dumpHeadline);
        } catch (IOException e) {
            System.err.println("Failed to get headlines");
        }
    }

    private Map<String, NewsViewer> getAvailableServices() throws InvalidSyntaxException {
        return context.getServiceReferences(NewsViewer.class, null)
                .stream().map(context::getService).filter(Objects::nonNull)
                .collect(Collectors.toMap(NewsViewer::shortName, viewer -> viewer));
    }

    private void dumpHeadline(Map.Entry<String, Integer> headline) {
        System.out.println(headline.getKey() + " [" + headline.getValue() + "]");
    }

    private void dumpViewer(NewsViewer viewer) {
        System.out.println(viewer.shortName() + " -> " + viewer.getUrl());
    }

    @Override
    public void start(BundleContext context) throws Exception {
        this.context = context;
        Dictionary<String, String> properties = new Hashtable<>();
        properties.put("osgi.command.scope", "news");
        properties.put("osgi.command.function", "stat");
        context.registerService(NewsStat.class, this, properties);

        this.skippers = new HashSet<>();
        InputStream skipStream = NewsStat.class.getResourceAsStream("/skips.txt");
        Scanner scanner = new Scanner(skipStream, StandardCharsets.UTF_8);
        while (scanner.hasNextLine()) {
            skippers.add(scanner.nextLine());
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {

    }
}
