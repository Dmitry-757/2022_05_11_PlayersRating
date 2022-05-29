package org.dng;

import java.util.*;

public class MainApp {

    public static void main(String[] args) {

//    регистрирует игроков в системе (должна быть проверка, занят ли ник)
        System.out.println("регистрирует игроков в системе (должна быть проверка, занят ли ник)");

        Player p1 = null, p2 = null, p3 = null, p4 = null;
        try {
            p1 = new Player("Petrov");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println();
            System.out.println("Lets try to register player Petrov twice:");
            p2 = new Player("Petrov");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            p3 = new Player("Sidorov");
            p4 = new Player("Pupkin");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Game game1 = new Game();
        game1.addPlayer(p1);
        System.out.println();
        System.out.println("lets try to add to game undefined player:");
        game1.addPlayer(null);
        game1.addPlayer(p3);
        game1.addPlayer(p4);
        game1.Play();

        Game game2 = new Game();
        game2.addPlayer(p1);
        game2.addPlayer(p3);
        game2.addPlayer(p4);
        game2.Play();

        Game game3 = new Game();
        game3.addPlayer(p1);
        game3.addPlayer(p4);
        game3.addPlayer(p3);
        game3.Play();

        System.out.println();
        System.out.println("Lets try to make game without players:");
        Game game4 = new Game();
        game4.Play();

        System.out.println();
        System.out.println("Lets try to make game with only one player:");
        Game game5 = new Game();
        game5.addPlayer(p1);
        game5.Play();


//    выводит список игр, в которые играют все игроки на сайте
        System.out.println();
        System.out.println("List of all games the players play:");
        List<Game> gameList = Service.getAllPlayersGamesSet(Service.getPlayerSet())
                .stream()
//                .sorted((o1, o2) -> o1.getGameName().toUpperCase().compareTo(o2.getGameName().toUpperCase()) )
                .sorted(Comparator.comparing(o -> o.getGameName().toUpperCase()))
                .toList();
        for (Game g:gameList ) {
            System.out.println(g.getGameName());
        }

//    выводит рейтинг по имени игрока и игре
        System.out.println();
        System.out.println("вывод рейтинга по имени игрока и игре");
        System.out.println("Raiting of "+p1.getNicName()+" in "+game1.getGameName()+" is "+Service.getPlayersRating(p1,game1));

//    выводит 10 лучших игроков в определенной игре
        System.out.println();
        Service.staticPropertiesClear(); //clear previous statistics
        //create 5 games and 10 players
        System.out.println(".........................Creating new games and new players.................");
        for (int j = 0; j < 10; j++) {
            try {
                new Player("Player_"+j);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 0; i < 5; i++) {
            Game game = new Game();
            Set<Player> players = Service.getPlayerSet();
            for (Player pl:players) {
                game.addPlayer(pl);
            }
            game.Play();
        }

        System.out.println("Print 10 best players in chosen game (game_3)");
        if (Service.getGameSet().size()>0){
            //Game chosenGame = Service.getGameSet().stream().toList().get(3);
            Set<Game> gs = Service.getGameSet();
            Game chosenGame = gs.stream()
                    .filter(g->g.getGameName().contains("game_3"))
                    .toList()
                    .get(0);

            System.out.println();
            System.out.println("The 10 best players in "+chosenGame.getGameName()+" are:");
            System.out.print("| ");
            Service.get10BestPlayers(chosenGame).forEach((p,r)-> System.out.print(p.getNicName()+" rating = "+r+" | " ));
            System.out.println();
        }
        else
            System.out.println("Oops! There is no games!");

        System.out.println("******************************************************************************************");
        System.out.println();

        System.out.println("Print 10 best players in chosen game (game_3) variant 2");
        if (Service.getGameSet().size()>0){
            //Game chosenGame = Service.getGameSet().stream().toList().get(3);
            Set<Game> gs = Service.getGameSet();
            Game chosenGame = gs.stream()
                    .filter(g->g.getGameName().contains("game_3"))
                    .toList()
                    .get(0);

            System.out.println();
            System.out.println("The 10 best players in "+chosenGame.getGameName()+" are:");
            System.out.print("| ");
            TreeMap<Player, Integer> sortedMap = Service.get10BestPlayersWithOutStream(chosenGame);
            sortedMap.forEach((p,r)-> System.out.print(p.getNicName()+" rating = "+r+" | " ));
            System.out.println();
        }
        else
            System.out.println("Oops! There is no games!");

        //System.out.println("******************************************************************************************");


//    выводит 10 лучших игроков с учетом всех игр
//        System.out.println();
//        System.out.println("The data for the test was prepared in the previous step");
//        System.out.println("Print 10 best players in all games");
//
//        if (Service.getGameSet().size()>0){
//            System.out.println("The 10 best players in all games are: ");
//            System.out.print("| ");
//            Service.get10BestPlayers(Service.getGameSet()).forEach(p-> System.out.print(p.getNicName()+" rating = "+p.getPlayerRating()+" | " ));
//            System.out.println();
//        }
//        else
//            System.out.println("Oops! There is no games!");


//        System.out.println("*******************************************************************************************");
//        System.out.println("*******************************************************************************************");
//        System.out.println("*******************************************************************************************");
//        System.out.println("....................log of methods working....................");
//        Service.logPrint();
    }

}
