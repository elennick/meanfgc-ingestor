package com.evanlennick.meanfgc.ingestor.sources;

import com.evanlennick.meanfgc.ingestor.VideoSource;
import com.evanlennick.meanfgc.dao.models.Video;

import java.util.List;

public class YogaFlame24VideoSource implements VideoSource {

    @Override
    public String getChannelId() {
        return "UC1UzB_b7NSxoRjhZZDicuqw";
    }

    @Override
    public List<Video> parseVideoList(List<Video> videos) {
        return null;
    }
}
