package org.dng;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game implements addPlayer2Game, PlayGame{
    private HashSet<Player> players;

    /**
     * add player to storage of Players
     * structure SET is used to have only one instance of user
     * @param pl - player
     */
    @Override
    public void addPlayer(Player pl){
        players.add(pl);
    }

    /**
     * Randomly select a player number in a set of players
     * the set is converted to an array and returns the element at the previously selected index
     * @return
     */
    @Override
    public Player ChooseWinner() {
        int minId = 0;
        int maxId = players.size()-1;
        int winnerId = (new Random().ints(minId, maxId)).limit(1).findFirst().orElse(0);
        return (Player) players.toArray()[winnerId];
    }
}
