package org.dng;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * class contains properties providing to identify player(nicName),
 * players games set and rating of player
 */
public class Player {
    private String nicName;
    private Set<Game> gamesSet = new HashSet<>();
    private int playerRating;

    public Player(String nicName) throws Exception {
        if (Service.getPlayersNicSet().containsKey(nicName)) {
            Service.log("Instance of Player cant be created! NicName `" + nicName + "` already exist!");
            throw new Exception("Player cant be created! NicName `" + nicName + "` already exist!");
        }
        this.nicName = nicName;
        Service.addPlayerNic2Set(nicName, this);
        Service.addPlayer2Set(this);
        Service.log("Instance of Player nicName = `" + nicName + "` created");
    }

    public void increasePlayerRating() {
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

    public String getNicName() {
        return nicName;
    }
}
