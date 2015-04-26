package com.evanlennick.meanfgc.dao;

import com.evanlennick.meanfgc.dao.models.Channel;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Optional;

@Singleton
public class ChannelsDao extends MongoDao<Channel> {

    @Inject
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

    public void save(Channel channel) {
        Optional<Channel> channelGotten = this.getChannelByChannelId(channel.getChannelId());
        if (channelGotten.isPresent()) {
            channel.setId(channelGotten.get().getId());
        }

        this.saveModel(channel);
    }
}
