package ru.ifmo.rain;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import ru.ifmo.rain.viewer.NewsViewer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RBCViewer implements NewsViewer, BundleActivator {
    public void start(BundleContext context) throws Exception {
        context.registerService(NewsViewer.class, this, null);
    }

    public void stop(BundleContext context) throws Exception {

    }

    @Override
    public String shortName() {
        return "NewsRBC";
    }

    @Override
    public String getUrl() {
        return "http://static.feed.rbc.ru/rbc/internal/rss.rbc.ru/rbc.ru/newsline.rss";
    }

    public List<String> getHeadlines() throws IOException {
        Document doc = Jsoup.connect(getUrl()).get();
        return doc.select("title").stream().map(Element::text).collect(Collectors.toList());
    }
}
