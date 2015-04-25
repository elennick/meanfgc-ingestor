package com.evanlennick.meanfgc.dao;

import com.evanlennick.meanfgc.dao.models.Player;
import com.evanlennick.meanfgc.dao.models.Video;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.testng.Assert.fail;

public class VideoDaoTest {

    DB db;

    Jongo jongo;

    @BeforeClass
    public void setup() {
        try {
            db = new MongoClient().getDB("meanfgc-dev");
            jongo = new Jongo(db);
        } catch (UnknownHostException uhe) {
            fail(uhe.getMessage());
            uhe.printStackTrace();
        }
    }

    @AfterClass
    public void cleanup() {
        MongoCollection videos = jongo.getCollection("videos");
        videos.remove("{ videoId : 'test-video-id' }");
    }

    @Test
    public void testSavingVideo() {
        Video videoSaved = createTestVideo();

        MongoCollection videos = jongo.getCollection("videos");
        videos.save(videoSaved);

        Video videoGotten = videos.findOne("{ videoId : 'test-video-id' }").as(Video.class);

        assertThat(videoGotten).isLenientEqualsToByIgnoringFields(videoSaved, "players");
        assertThat(videoGotten.getPlayers()).hasSameSizeAs(videoSaved.getPlayers());
    }

    private Video createTestVideo() {
        Video video = new Video();

        video.setDescription(Arrays.asList("test description"));
        video.setTitle(Arrays.asList("test title"));
        video.setGame("Test");
        video.setPlayers(Arrays.asList(new Player("test1", "test1"), new Player("test2", "test2")));
        video.setPostDate(new Date());
        video.setPostedBy("test");
        video.setType("test");
        video.setVideoId("test-video-id");

        return video;
    }
}
