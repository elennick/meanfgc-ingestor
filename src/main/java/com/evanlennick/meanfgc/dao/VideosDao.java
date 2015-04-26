package com.evanlennick.meanfgc.dao;

import com.evanlennick.meanfgc.dao.models.Video;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class VideosDao extends MongoDao<Video> {

    @Inject
    public VideosDao() {
        super("videos");
    }

    public void saveVideo(Video video) {
        Optional<Video> videoGotten = this.getVideoByVideoId(video.getVideoId());
        if (videoGotten.isPresent()) {
            video.setId(videoGotten.get().getId());
        }

        this.saveModel(video);
    }

    public void saveVideos(List<Video> videos) {
        for (Video video : videos) {
            try {
                this.saveVideo(video);
            } catch (Exception e) {
                System.out.println("Error saving video because -> " + e.getMessage());
            }
        }
    }

    public void deleteVideoByVideoId(String videoId) {
        getCollection().remove("{ videoId : '" + videoId + "' }");
    }

    public Optional<Video> getVideoByVideoId(String videoId) {
        Video video = getCollection().findOne("{ videoId : '" + videoId + "' }").as(Video.class);
        return Optional.ofNullable(video);
    }
}
