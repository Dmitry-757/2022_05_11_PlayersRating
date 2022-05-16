package org.dng;

import java.util.*;
import java.util.stream.Collectors;

//public class Service implements IStatisticService{
public class Service {
    private static HashMap<String, Player> playersNicSet = new HashMap<>();
    private static Set<Game> gameSet = new HashSet<>();
    private static String log = "";

    //for tests
    public static void staticPropertiesClear(){
        playersNicSet.clear();
        gameSet.clear();
    }

    //add game to set of games
    public static void addGame2Set(Game game) {
        Service.gameSet.add(game);
    }


    //get all games
    public static Set<Game> getGameSet() {
        return gameSet;
    }

    public static HashMap<String, Player> getPlayersNicSet() {
        return playersNicSet;
    }

    public static Set<Player> getPlayerSet() {
        return playersNicSet.values().stream().collect(Collectors.toSet());
    }

    public static void addPlayerNic2Set(String nicName, Player player) {
        Service.playersNicSet.put(nicName, player);
    }

    //добавляет рейтинг игроку, в случае его выигрыша в игре
    public static void increaseRating(Player player) {
        player.increasePlayerRating();
        log("Service.increaseRating(player) player = `" + player.getNicName() + "` done successfully");
    }

    //выводит список игр, в которые играют все игроки на сайте
    public static Set<Game> getAllPlayersGamesList(Set<Player> players) {
        HashSet<Game> gamesSet = new HashSet<>();
        for (Player player : players) {
            gamesSet.addAll(player.getGamesSet());
        }

        return gamesSet;
    }

    //выводит рейтинг по имени игрока и игре
    public static int getPlayersRating(Player player, Game game) {
        return game.getPlayerAndRating().get(player);
    }

    //выводит 10 лучших игроков в определенной игре
    public static List<Player> get10BestPlayers(Game game) {
        return game.getPlayers().stream()
//                .sorted((o1, o2) -> o1.getPlayerRating() - o2.getPlayerRating())
                .sorted(Comparator.comparingInt(Player::getPlayerRating).reversed())
//                .distinct()
                .limit(10)
                .toList();
    }


    //выводит 10 лучших игроков с учетом всех игр
    public static List<Player> get10BestPlayers(Set<Game> games) {
        return games.stream()
                .flatMap((g) -> g.getPlayers().stream())
                .sorted(Comparator.comparingInt(Player::getPlayerRating).reversed())
                .distinct()
                .limit(10)
                .toList();
    }

    // ********** logging ********************
    //add record of method result in to log
    public static void log(String record) {
        StringBuilder sb = new StringBuilder();
        sb.append(log);
        sb.append(record).append("\n");
        log = sb.toString();
    }

    public static void logPrint() {
        System.out.println(log);
    }

}
