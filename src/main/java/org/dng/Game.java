package org.dng;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * this class tasks are:
 * registration players, choose a winner
 * this entity defines fact characterized by the following properties:
 * - uniqueness (it can be only once)
 * - has at least two players
 */
public class Game implements IGameService{
    private HashSet<Player> players;
    private HashMap<Player, Integer> playerAndRating;
    private String gameName;

    /**
     * add player to storage of Players
     * structure SET is used to have only one instance of user
     * @param pl - player
     */
    @Override
    public void addPlayer(Player pl){
        players.add(pl);
        playerAndRating.put(pl, pl.getPlayerRating());//fix ratings of players, that takes part in this game
    }

    public HashSet<Player> getPlayers() {
        return players;
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

    @Override
    public boolean Play() {
        if(players.size()<2) {
            switch (players.size()){
                case 0 ->{
                    System.out.println("There are no players to play game!");
                }
                case 1 ->{
                    System.out.println("The game with only one player is called `Elections` - go to central electoral committee ;)" );
                }
            }
            return false;
        }
        Player winner = ChooseWinner();
        Service.increaseRating(winner);
        playerAndRating.put(winner, winner.getPlayerRating());//save rating for player in current game
        return true;
    }
}
