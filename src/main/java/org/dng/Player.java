package org.dng;

import java.util.List;
import java.util.Set;

/**
 * class contains properties providing to identify player(nicName),
 * players games set and rating of player
 */
public class Player {
    private String nicName;
    private Set<Game> gamesSet;
    private int playerRating;

    public void increasePlayerRating(){
        this.playerRating++;
    }

    public int getPlayerRating() {
        return playerRating;
    }

    public void addGame(Game game) {
        this.gamesSet.add(game);
    }

    public Set<Game> getGamesSet() {
        return gamesSet;
    }
}
