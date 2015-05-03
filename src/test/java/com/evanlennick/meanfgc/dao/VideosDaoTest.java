package com.evanlennick.meanfgc.dao;

import com.evanlennick.meanfgc.dao.models.Player;
import com.evanlennick.meanfgc.dao.models.Video;
import com.google.inject.Guice;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Date;

import static org.fest.assertions.api.Assertions.assertThat;

public class VideosDaoTest {

    private VideosDao dao;

    @BeforeClass
    public void setup() {
        dao = Guice.createInjector().getInstance(VideosDao.class); //todo get testng working with guice so we can do field injection instead here
    }

    @AfterClass
    public void cleanup() {
        dao.deleteVideoByVideoId("test-video-id");
    }

    @Test
    public void testSavingVideo() {
        Video videoSaved = createTestVideo();
        dao.saveVideo(videoSaved);

        Video videoGotten = dao.getVideoByVideoId("test-video-id").get();

        assertThat(videoGotten).isLenientEqualsToByIgnoringFields(videoSaved, "players");
        assertThat(videoGotten.getPlayers()).hasSameSizeAs(videoSaved.getPlayers());
    }

    private Video createTestVideo() {
        Video video = new Video();

        video.setDescription("test description");
        video.setTitle("test title");
        video.setGame("Test");
        video.setPlayers(Arrays.asList(new Player("test1", "test1"), new Player("test2", "test2")));
        video.setPostDate(new Date());
        video.setPostedBy("test");
        video.setType("test");
        video.setVideoId("test-video-id");

        return video;
    }
}
