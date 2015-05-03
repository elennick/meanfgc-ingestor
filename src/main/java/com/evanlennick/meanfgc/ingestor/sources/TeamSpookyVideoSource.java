package com.evanlennick.meanfgc.ingestor.sources;

import com.evanlennick.meanfgc.dao.models.Video;
import com.evanlennick.meanfgc.ingestor.VideoSource;
import com.evanlennick.meanfgc.ingestor.utils.Game;
import com.evanlennick.meanfgc.ingestor.utils.VideoIngestorUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class TeamSpookyVideoSource implements VideoSource {

    @Override
    public String getChannelId() {
        return "UCjT9Hwh4twdfvFZCV1tIsCw";
    }

    @Override
    public Video parseVideo(Video video) {
        String[] splitTitle = StringUtils.split(video.getTitle(), "-");

        String titleEvent = splitTitle[0].trim();
        String titleGame = splitTitle[1].trim();
        String titlePlayers = splitTitle[2].trim();

        //parse game
        Optional<Game> gameOptional = VideoIngestorUtils.parseGameFromTitle(titleGame);
        if (gameOptional.isPresent()) {
            video.setGame(gameOptional.get().getName());
        } else {
            video.setGame("Unknown");
        }

        //parse event
        video.setEvent(titleEvent);

        //parse players
        //todo parse it

        return video;
    }
}
