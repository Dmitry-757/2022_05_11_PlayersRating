package org.dng;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//public class Service implements IStatisticService{
public class Service {

    //добавляет рейтинг игроку, в случае его выигрыша в игре
//    @Override
    public static void increaseRating(Player player) {
        player.increasePlayerRating();
    }

    //выводит список игр, в которые играют все игроки на сайте
//    @Override
    public static HashSet<Game> getAllPlayersGamesList(HashSet<Player> players) {
        HashSet<Game> gamesSet = new HashSet<>();
        for (Player player:players){
            for(Game game:player.getGamesSet()){
                gamesSet.add(game);
            }
        }
        return gamesSet;
    }

    //??????????????????????????????????????
    //выводит рейтинг по имени игрока и игре
//    @Override
    public static int getPlayersRating(Player player) {
        return player.getPlayerRating();
    }

    //выводит 10 лучших игроков в определенной игре
//    @Override
    public static List<Player> get10BestPlayers(Game game) {
        return game.getPlayers().stream()
//                .sorted((o1, o2) -> o1.getPlayerRating() - o2.getPlayerRating())
                .sorted(Comparator.comparingInt(Player::getPlayerRating))
                .limit(10)
                .toList();
    }

//    @Override
    public static List<Player> get10BestPlayers(Set<Game> games) {
        return games.stream()
                .flatMap((g)->g.getPlayers().stream())
                .sorted(Comparator.comparingInt(Player::getPlayerRating))
                .limit(10)
                .toList();
    }
}
