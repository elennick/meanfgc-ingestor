package com.evanlennick.meanfgc.dao.models;

import org.jongo.marshall.jackson.oid.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Video {

    @ObjectId
    private String _id;

    private List<String> title;

    private List<String> description;

    private List<Player> players;

    private Date postDate;

    private String postedBy;

    private String game;

    private String videoId;

    private String event;

    private String type;

    public Video() {

    }

    public Video(String videoId, String title, String description, Date postDate,
                 String game, String event, String type, Player player1, Player player2) {

        this.videoId = videoId;
        this.title = Arrays.asList(title);
        this.description = Arrays.asList(description);
        this.postDate = postDate;
        this.game = game;
        this.event = event;
        this.type = type;

        this.players = new ArrayList<>();
        this.players.add(player1);
        this.players.add(player2);
    }

    @Override
    public String toString() {
        return "Video{" +
                "_id='" + _id + '\'' +
                ", title=" + title +
                ", description=" + description +
                ", players=" + players +
                ", postDate=" + postDate +
                ", postedBy='" + postedBy + '\'' +
                ", game='" + game + '\'' +
                ", videoId='" + videoId + '\'' +
                ", event='" + event + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
