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
public class Game implements IGameService {
    private HashSet<Player> players = new HashSet<>();
    private HashMap<Player, Integer> playerAndRatingInGame = new HashMap<>();
    private String gameName;
    private boolean gamePlayed = false;

    public Game() {
        Service.addGame2Set(this);
        gameName = "game_"+Service.getGameSet().size();
        Service.log("Instance of Game created");
    }

    public boolean isGamePlayed() {
        return gamePlayed;
    }

    public String getGameName() {
        return gameName;
    }

    /**
     * add player to storage of Players
     * structure SET is used to have only one instance of user
     */
    @Override
    public boolean addPlayer(Player pl) {
        if (pl != null) {
            players.add(pl);
            pl.addGame(this);
            playerAndRatingInGame.put(pl, pl.getPlayerRating());//fix ratings of players, that takes part in this game
            Service.log("Game.addPlayer(player) player = `" + pl.getNicName() + "` done successfully");
            return true;
        } else
            System.out.println("Cant add player to game "+ gameName+". Player is undefined!");
        return false;
    }

    public HashSet<Player> getPlayers() {
        return players;
    }

    public HashMap<Player, Integer> getPlayerAndRating() {
        return playerAndRatingInGame;
    }

    /**
     * Randomly select a player number in a set of players
     * the set is converted to an array and returns the element at the previously selected index
     *
     * @return winner of game
     */
    @Override
    public Player ChooseWinner() {
        int minId = 0;
        int maxId = players.size() - 1;
        int winnerId = (new Random().ints(minId, maxId)).limit(1).findFirst().orElse(0);

        //plug!!!
//        winnerId = 0;

        Player winner = (Player) players.toArray()[winnerId];
        Service.log("Game.ChooseWinner() winner = `" + winner.getNicName() + "` done successfully");

        return winner;
    }

    @Override
    public boolean Play() {
        if (players.size() < 2) {
            switch (players.size()) {
                case 0 -> System.out.println("There are no players to play game!");
                case 1 -> System.out.println("The game with only one player is called `Elections` - go to central electoral committee ;)");
            }
            return false;
        }
        if (this.gamePlayed){
            System.out.println("Game has been already played! Go to central electoral committee ;)");
            return false;
        }

        Player winner = ChooseWinner();
        Service.increaseRating(winner);
        playerAndRatingInGame.put(winner, winner.getPlayerRating());//save rating for player in current game
        this.gamePlayed = true;

        Service.log("Game.Play() winner = `" + winner.getNicName() + "` done successfully");

        return true;
    }
}
