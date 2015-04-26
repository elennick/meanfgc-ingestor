package com.evanlennick.meanfgc.ingestor.utils;

public enum Game {
    USF4("Ultra Street Fighter 4"),
    SSF4("Super Street Fighter 4"),
    SF4("Street Fighter 4"),
    MKX("Mortal Kombat X");

    private String name;

    private Game(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
