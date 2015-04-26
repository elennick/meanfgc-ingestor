package com.evanlennick.meanfgc.dao.models;

public class Player extends MongoModel {

    private String character;

    private String player;

    public Player() {

    }

    public Player(String character, String player) {
        this.character = character;
        this.player = player;
    }

    @Override
    public String toString() {
        return "Player{" +
                "_id='" + _id + '\'' +
                ", character='" + character + '\'' +
                ", player='" + player + '\'' +
                '}';
    }
}
