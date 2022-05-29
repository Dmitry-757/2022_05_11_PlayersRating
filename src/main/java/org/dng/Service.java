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
    public static Set<Game> getAllPlayersGamesSet(Set<Player> players) {
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
    public static LinkedHashMap<Player, Integer> get10BestPlayers(Game game) {
        HashMap<Player, Integer> playerIntegerHashMap = game.getPlayerAndRating();

//        Player p1 = null, p2 = null, p3 = null, p4 = null;
//        try {
//            p1 = new Player("Petrov");
//            p2 = new Player("Ivanov");
//            p3 = new Player("Sidorov");
//            p4 = new Player("Pupkin");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        HashMap<Player, Integer> playerIntegerHashMap = new HashMap<>();
//        playerIntegerHashMap.put(p1,1);
//        playerIntegerHashMap.put(p2,2);
//        playerIntegerHashMap.put(p3,3);
//        playerIntegerHashMap.put(p4,4);

        LinkedHashMap<Player, Integer> sortedHashMap = new LinkedHashMap<>();

//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
//        playerIntegerHashMap
//                .entrySet().stream()
//                .sorted(Map.Entry.<Player,Integer>comparingByValue().reversed())
//                .limit(10)
//                .forEachOrdered(x -> System.out.println(x.getKey().getNicName()+" rating "+x.getValue()));
//        ;
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!");

        playerIntegerHashMap
                .entrySet().stream()
                .sorted(Map.Entry.<Player,Integer>comparingByValue().reversed())
                .limit(10)
                .forEachOrdered(x -> sortedHashMap.put(x.getKey(), x.getValue()));
        return sortedHashMap;
    }

    //выводит 10 лучших игроков в определенной игре без использования стримов
    public static TreeMap<Player, Integer> get10BestPlayersWithOutStream(Game game) {
        HashMap<Player, Integer> playerIntegerHashMap = game.getPlayerAndRating();

        Comparator<Player> valueComparator =  new Comparator<Player>() {
            public int compare(Player k1, Player k2) {
                int compare = playerIntegerHashMap.get(k2) - playerIntegerHashMap.get(k1);
                if (compare == 0) return 1;
                else return compare;
            }
        };

        TreeMap<Player, Integer> sortedMap = new TreeMap<>(valueComparator);
        sortedMap.putAll(playerIntegerHashMap);

        return sortedMap;
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
