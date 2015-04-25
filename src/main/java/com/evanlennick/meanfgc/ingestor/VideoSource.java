package com.evanlennick.meanfgc.ingestor;

import com.evanlennick.meanfgc.dao.models.Video;

import java.util.List;

public interface VideoSource {

    public String getChannelId();

    public List<Video> parseVideoList(List<Video> videos);
}
