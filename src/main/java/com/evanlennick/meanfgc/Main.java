package com.evanlennick.meanfgc;

import com.evanlennick.meanfgc.ingestor.VideoIngestor;
import com.evanlennick.meanfgc.ingestor.VideoSource;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {

    private static Injector injector;

    public static void main(String[] args) {
        injector = Guice.createInjector();
        List<VideoSource> videoSources = new ArrayList<>();

        Reflections reflections = new Reflections("com.evanlennick.meanfgc.ingestor.sources");
        Set<Class<? extends VideoSource>> sourcesInPackage = reflections.getSubTypesOf(VideoSource.class);
        for (Class<? extends VideoSource> source : sourcesInPackage) {
            VideoSource sourceInstance = injector.getInstance(source);
            videoSources.add(sourceInstance);
        }

        VideoIngestor ingestor = injector.getInstance(VideoIngestor.class);
        for (VideoSource videoSource : videoSources) {
            ingestor.ingestSource(videoSource);
        }
    }
}
