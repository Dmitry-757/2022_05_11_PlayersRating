package org.dng;

import java.util.*;

//public class Service implements IStatisticService{
public class Service {
    static HashMap<String, Player> playersNicSet = new HashMap<>();
    static Set<Player> playerSet = new HashSet<>();
    static Set<Game> gameSet = new HashSet<>();
    private static String log = "";

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

    public static void addPlayer2Set(Player player) {
        Service.playerSet.add(player);
    }

    public static void addGame2Set(Game game) {
        Service.gameSet.add(game);
    }

    public static Set<Player> getPlayerSet() {
        return playerSet;
    }

    public static Set<Game> getGameSet() {
        return gameSet;
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
                .sorted(Comparator.comparingInt(Player::getPlayerRating))
//                .distinct()
                .limit(10)
                .toList();
    }


    //выводит 10 лучших игроков с учетом всех игр
    public static List<Player> get10BestPlayers(Set<Game> games) {
        return games.stream()
                .flatMap((g) -> g.getPlayers().stream())
                .sorted(Comparator.comparingInt(Player::getPlayerRating))
                .distinct()
                .limit(10)
                .toList();
    }
}
