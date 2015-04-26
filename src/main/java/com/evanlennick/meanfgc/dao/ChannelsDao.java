package com.evanlennick.meanfgc.dao;

import com.evanlennick.meanfgc.dao.models.Channel;

import java.util.Optional;

public class ChannelsDao extends MongoDao<Channel> {

    public ChannelsDao() {
        super("channels");
    }

    public Optional<Channel> getChannelByChannelId(String channelId) {
        Channel channel = getCollection().findOne("{ youtube_id : '" + channelId + "' }").as(Channel.class);
        return Optional.ofNullable(channel);
    }

    public void deleteChannelByChannelId(String channelId) {
        getCollection().remove("{ youtube_id : '" + channelId + "' }");
    }
}
