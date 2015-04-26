package com.evanlennick.meanfgc.dao.models;

import org.jongo.marshall.jackson.oid.ObjectId;

public class Player {

    @ObjectId
    private String _id;

    private String character;

    private String player;

    public Player() {

    }

    @Override
    public String toString() {
        return "Player{" +
                "_id='" + _id + '\'' +
                ", character='" + character + '\'' +
                ", player='" + player + '\'' +
                '}';
    }

    public Player(String character, String player) {
        this.character = character;
        this.player = player;
    }
}
