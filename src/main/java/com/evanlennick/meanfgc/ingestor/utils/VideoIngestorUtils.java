package com.evanlennick.meanfgc.ingestor.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Util methods for source classes to use.
 */
public class VideoIngestorUtils {

    private VideoIngestorUtils() {
        //should never instantiate util class
    }

    public static Optional<Game> parseGameFromTitle(String title) { //todo find a better way to do this
        if (StringUtils.containsIgnoreCase(title, "USF4") || StringUtils.containsIgnoreCase(title, "Ultra Street Fighter 4")) {
            return Optional.of(Game.USF4);
        } else if (StringUtils.containsIgnoreCase(title, "SSF4") || StringUtils.containsIgnoreCase(title, "Super Street Fighter 4")) {
            return Optional.of(Game.SSF4);
        } else if (StringUtils.containsIgnoreCase(title, "SF4") || StringUtils.containsIgnoreCase(title, "Street Fighter 4")) {
            return Optional.of(Game.SF4);
        } else if (StringUtils.containsIgnoreCase(title, "MKX") || StringUtils.containsIgnoreCase(title, "Mortal Kombat X")) {
            return Optional.of(Game.MKX);
        }

        return Optional.empty();
    }
}
