package org.dng;

import java.util.HashMap;
import java.util.Set;

public class MainApp {

    public static void main(String[] args) {

        Player p1 = null, p2 = null, p3 = null;

        try {
            p1 = new Player("Petrov");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            p2 = new Player("Petrov");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            p2 = new Player("Sidorov");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            p3 = new Player("Pupkin");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        Game game1 = new Game();
        game1.addPlayer(p1);
        game1.addPlayer(p2);
        game1.addPlayer(p3);
        game1.Play();

        Game game2 = new Game();
        game2.addPlayer(p1);
        game2.addPlayer(p2);
        game2.addPlayer(p3);
        game2.Play();

//    выводит список игр, в которые играют все игроки на сайте
        System.out.println("список игр, в которые играют все игроки на сайте");
        Set<Game> gs = Service.getAllPlayersGamesList(Service.playerSet);
        for (Game g:gs ) {
            System.out.println(g);
        }

//    выводит рейтинг по имени игрока и игре
        System.out.println("Raiting of "+p1.getNicName()+" in game "+game1+" is "+Service.getPlayersRating(p1,game1));

//    выводит 10 лучших игроков в определенной игре
        System.out.println("The 10 best players in game "+game2+" are:");
        System.out.print("| ");
        Service.get10BestPlayers(game2).forEach(p-> System.out.print(p.getNicName()+" | " ));
        System.out.println();

//    выводит 10 лучших игроков с учетом всех игр
        System.out.println("The 10 best players in all games are: ");
        System.out.print("| ");
        Service.get10BestPlayers(Service.getGameSet()).forEach(p-> System.out.print(p.getNicName()+" | " ));
        System.out.println();


        System.out.println("******************************************************************************************");
        Service.logPrint();


    }
}
