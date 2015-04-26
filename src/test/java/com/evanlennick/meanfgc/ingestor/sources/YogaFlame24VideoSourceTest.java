package com.evanlennick.meanfgc.ingestor.sources;

import com.evanlennick.meanfgc.dao.models.Video;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.fest.assertions.api.Assertions.assertThat;

public class YogaFlame24VideoSourceTest {

    @Test
    public void testVideoParsing_GameFromTitle() {
        YogaFlame24VideoSource source = new YogaFlame24VideoSource();

        Video sf4Video = new Video();
        sf4Video.setTitle(Arrays.asList("This is a title that says Street Fighter 4 in it."));
        Video parsedSf4Video = source.parseVideo(sf4Video);
        assertThat(parsedSf4Video.getGame()).isEqualToIgnoringCase("Street Fighter 4");

        Video usf4Video = new Video();
        usf4Video.setTitle(Arrays.asList("This is a title that says Ultra Street Fighter 4 in it."));
        Video parsedUsf4Video = source.parseVideo(usf4Video);
        assertThat(parsedUsf4Video.getGame()).isEqualToIgnoringCase("Ultra Street Fighter 4");

        Video mkxVideo = new Video();
        mkxVideo.setTitle(Arrays.asList("This is a title that says mkx in it."));
        Video parsedMkxVideo = source.parseVideo(mkxVideo);
        assertThat(parsedMkxVideo.getGame()).isEqualToIgnoringCase("Mortal Kombat X");
    }
}
