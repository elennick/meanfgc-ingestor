package com.evanlennick.meanfgc;

import com.evanlennick.meanfgc.ingestor.VideoIngestor;
import com.evanlennick.meanfgc.ingestor.sources.YogaFlame24VideoSource;
import com.evanlennick.meanfgc.dao.models.Video;
import com.google.inject.Guice;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {
        VideoIngestor ingestor = Guice.createInjector().getInstance(VideoIngestor.class);
        ingestor.ingestSource(new YogaFlame24VideoSource());
    }

}
