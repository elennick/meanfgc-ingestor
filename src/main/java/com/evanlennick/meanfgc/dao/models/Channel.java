package com.evanlennick.meanfgc.dao.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Channel extends MongoModel {

    private String name;

    @JsonProperty(value="last_updated")
    private Date lastUpdated;

    @JsonProperty(value="youtube_id")
    private String channelId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
