package com.evanlennick.meanfgc.ingestor;

import com.evanlennick.meanfgc.dao.models.Video;

public interface VideoSource {

    public String getChannelId();

    public Video parseVideo(Video video);
}
