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

    public Player(String nicName) throws Exception {
        if(MainApp.playersNicSet.contains(nicName))
            throw new Exception("Player cant be created! NicName `"+nicName+"` already exist!");
        this.nicName = nicName;
        MainApp.playersNicSet.add(nicName);
    }

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
