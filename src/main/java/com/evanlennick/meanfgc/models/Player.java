package com.evanlennick.meanfgc.models;

import org.jongo.marshall.jackson.oid.ObjectId;

public class Player {

    @ObjectId
    private String _id;

    private String character;

    private String player;

    public Player() {

    }
    
    public Player(String character, String player) {
        this.character = character;
        this.player = player;
    }
}
