package com.evanlennick.meanfgc.ingestor.sources;

import com.evanlennick.meanfgc.dao.models.Player;
import com.evanlennick.meanfgc.dao.models.Video;
import com.evanlennick.meanfgc.ingestor.utils.Game;
import com.evanlennick.meanfgc.ingestor.utils.VideoIngestorUtils;
import com.evanlennick.meanfgc.ingestor.VideoSource;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public class YogaFlame24VideoSource implements VideoSource {

    @Override
    public String getChannelId() {
        return "UC1UzB_b7NSxoRjhZZDicuqw";
    }

    @Override
    public Video parseVideo(Video video) {
        //parse game
        Optional<Game> gameOptional = VideoIngestorUtils.parseGameFromTitle(video.getTitle().get(0));
        if (gameOptional.isPresent()) {
            video.setGame(gameOptional.get().getName());
        } else {
            video.setGame("Unknown");
        }

        //parse players
        //todo redo this more smartly with regex or something?
        try {
            if (gameOptional.isPresent()) {
                Game game = gameOptional.get();
                if (Game.USF4.equals(game)) {
                    String[] splitTitle = StringUtils.split(video.getTitle().get(0), "()");
                    String player1 = splitTitle[0].trim();
                    String player2 = splitTitle[2].substring(3).trim();
                    String character1 = splitTitle[1].trim();
                    String character2 = splitTitle[3].trim();

                    video.setPlayers(Arrays.asList(
                            new Player(character1, player1),
                            new Player(character2, player2)));
                }
            }
        } catch(Exception e) {
            System.out.println("error parsing player/character data for video -> " + e.getMessage());
        }

        //parse event
        //todo

        //parse type
        //todo

        return video;
    }
}
