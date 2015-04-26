package com.evanlennick.meanfgc.ingestor.models;

import com.evanlennick.meanfgc.dao.models.Video;

import java.util.List;
import java.util.Optional;

public class VideoPage {

    private List<Video> videos;

    private Optional<String> nextPageToken;

    private String channelId;

    private String channelTitle;

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public Optional<String> getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(Optional<String> nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
