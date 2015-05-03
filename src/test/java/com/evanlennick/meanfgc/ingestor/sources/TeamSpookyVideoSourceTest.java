package com.evanlennick.meanfgc.ingestor.sources;

import com.evanlennick.meanfgc.dao.models.Video;
import com.evanlennick.meanfgc.ingestor.utils.Game;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.fest.assertions.api.Assertions.assertThat;

public class TeamSpookyVideoSourceTest {

    @Test
    public void testVideoParsing_GameFromTitle() {
        TeamSpookyVideoSource source = new TeamSpookyVideoSource();

        Video mkxVideo = new Video();
        mkxVideo.setTitle("Next Level Battle Circuit 118 - MKX - Losers Final - BIFU EIF Insaynne (Kano) vs TS Sabin (Takeda)");
        Video parsedMkxVideo = source.parseVideo(mkxVideo);
        assertThat(parsedMkxVideo.getGame()).isEqualToIgnoringCase(Game.MKX.getName());
    }
}
