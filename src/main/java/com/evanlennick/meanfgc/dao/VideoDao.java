package com.evanlennick.meanfgc.dao;

import com.evanlennick.meanfgc.dao.models.Video;
import com.google.inject.Singleton;
import org.jongo.MongoCollection;

import java.util.List;

@Singleton
public class VideoDao extends MongoDao {

    MongoCollection videoCollection;

    public VideoDao() {
        videoCollection = jongo.getCollection("videos");
    }

    public void saveVideo(Video video) {
        videoCollection.save(video);
    }

    public void saveVideos(List<Video> videos) {
        for (Video video : videos) {
            videoCollection.save(video);
        }
    }

    public void deleteVideoByVideoId(String videoId) {
        videoCollection.remove("{ videoId : '" + videoId + "' }");
    }

    public Video getVideoByVideoId(String videoId) {
        return videoCollection.findOne("{ videoId : '" + videoId + "' }").as(Video.class);
    }
}
