package com.evanlennick.meanfgc.ingestor.sources;

import com.evanlennick.meanfgc.dao.models.Video;
import com.evanlennick.meanfgc.ingestor.VideoSource;

public class CapcomFightersTVVideoSource implements VideoSource {

    @Override
    public String getChannelId() {
        return "UCPGuorlvarThSlwJpyTHOmQ";
    }

    @Override
    public Video parseVideo(Video video) {
        return video;
    }
}
