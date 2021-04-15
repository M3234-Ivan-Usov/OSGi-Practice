package ru.ifmo.rain.viewer;

import java.io.IOException;
import java.util.List;

public interface NewsViewer {
    String shortName();

    String getUrl();

    List<String> getHeadlines() throws IOException;
}
