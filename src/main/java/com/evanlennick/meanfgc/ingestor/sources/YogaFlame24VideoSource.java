package com.evanlennick.meanfgc.ingestor.sources;

import com.evanlennick.meanfgc.dao.models.Video;
import com.evanlennick.meanfgc.ingestor.Game;
import com.evanlennick.meanfgc.ingestor.VideoIngestorUtils;
import com.evanlennick.meanfgc.ingestor.VideoSource;

import java.util.Optional;

public class YogaFlame24VideoSource implements VideoSource {

    @Override
    public String getChannelId() {
        return "UC1UzB_b7NSxoRjhZZDicuqw";
    }

    @Override
    public Video parseVideo(Video video) {
        //parse game
        Optional<Game> gameOptional = VideoIngestorUtils.parseGameFromTitle(video.getTitle().get(0));
        if (gameOptional.isPresent()) {
            video.setGame(gameOptional.get().getName());
        } else {
            video.setGame("Unknown");
        }

        //parse players
        //todo redo this more smartly with regex or something?
        if(gameOptional.isPresent()) {

        }

        //parse event
        //todo

        //parse type
        //todo

        return video;
    }
}
