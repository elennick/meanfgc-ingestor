package com.evanlennick.meanfgc;

import com.evanlennick.meanfgc.models.Player;
import com.evanlennick.meanfgc.models.Video;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        DB db;
        try {
            db = new MongoClient().getDB("meanfgc-dev");
        } catch(UnknownHostException uhe) {
            uhe.printStackTrace();
            return;
        }

        Jongo jongo = new Jongo(db);
        MongoCollection videos = jongo.getCollection("videos");

        MongoCursor<Video> all = videos.find("{}").as(Video.class);

        for (Video video : all) {
            System.out.println(video.getVideoId());
        }
//        Video one = videos.findOne("{}").as(Video.class);
//        System.out.println(one.getVideoId());

//        Video video = new Video();
//        video.setDescription(Arrays.asList("test"));
//        video.setTitle(Arrays.asList("test"));
//        video.setGame("Test");
//        video.setPlayers(Arrays.asList(new Player("test1", "test1"), new Player("test2", "test2")));
//        video.setPostDate(new Date());
//        video.setPostedBy("test");
//        video.setType("test");
//        video.setVideoId("test-video-id");
//
//        videos.save(video);
    }
}
