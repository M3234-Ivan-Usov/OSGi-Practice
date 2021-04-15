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

public class LentaViewer implements NewsViewer, BundleActivator{

    public void start(BundleContext context) throws Exception {
        context.registerService(NewsViewer.class, this, null);
    }

    public void stop(BundleContext context) throws Exception {

    }

    @Override
    public String shortName() {
        return "LentaNews";
    }

    @Override
    public String getUrl() {
        return "https://lenta.ru/rss/news";
    }

    public List<String> getHeadlines() throws IOException {
        Document doc = Jsoup.connect(getUrl()).get();
        return doc.select("title").stream().map(Element::text).collect(Collectors.toList());
    }
}
