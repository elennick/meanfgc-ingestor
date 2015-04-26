package com.evanlennick.meanfgc.dao;

import com.evanlennick.meanfgc.dao.models.Channel;
import com.google.inject.Guice;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Optional;

import static org.fest.assertions.api.Assertions.assertThat;

public class ChannelsDaoTest {

    private ChannelsDao dao;

    @BeforeClass
    public void setup() {
        dao = Guice.createInjector().getInstance(ChannelsDao.class);
    }

    @AfterClass
    public void cleanup() {
        dao.deleteChannelByChannelId("test channel 1");
    }

    @Test
    public void testSavingChannel() {
        Channel channelSaved = new Channel();
        channelSaved.setChannelId("test channel 1");
        channelSaved.setLastUpdated(new Date());
        channelSaved.setName("Test Channel");
        dao.saveModel(channelSaved);

        Optional<Channel> channelGotten = dao.getChannelByChannelId(channelSaved.getChannelId());

        assertThat(channelGotten.isPresent()).isTrue();
        assertThat(channelGotten.get()).isEqualsToByComparingFields(channelSaved);
    }

    @Test
    public void testUpdatingExistingChannel() {
        //todo write this
    }
}
