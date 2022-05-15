package org.dng;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;


class ServiceTest {

    //    регистрирует игроков в системе (должна быть проверка, занят ли ник)
    @Test
    void duplicatedPlayerRegisteringInSystem() {
        Player p1 = null;
        String nicName = "Petrov";
        try {
            p1 = new Player(nicName);
            p1 = new Player(nicName);
            Assertions.fail("Exception was expected!");
        } catch (Exception e) {
            System.out.println("Test done. Caught exception = " + e.getMessage());
            Assertions.assertNotEquals("", "Player cant be created! NicName `" + nicName + "` already exist!");
        }
    }

    @Test
    void playerRegisteringInSystem() {
        Player p1 = null;
        String nicName = "Petrov";
        try {
            p1 = new Player(nicName);
        } catch (Exception e) {
            Assertions.fail("Exception was not expected!");
        }
        //checking if the name was added to the set
        Assertions.assertEquals(Service.getPlayersNicSet().containsKey(nicName), true);
        //checking if the instance of Player contained in the set of players
        Assertions.assertEquals(Service.getPlayerSet().contains(p1), true);
    }

    @Test
    void gameRegisteringInSystem() {
        Game game1 = null;
        try {
            game1 = new Game();
        } catch (Exception e) {
            Assertions.fail("Exception was not expected!");
        }

        //checking if the instance of Game contained in the set of games
        Assertions.assertEquals(Service.getGameSet().contains(game1), true);
    }


//    выводит список игр, в которые играют все игроки на сайте
    @Test
    void getAllPlayersGamesList() {
        Player p1 = null, p2 = null, p3 = null;

        try {
            p1 = new Player("Petrov");
            p2 = new Player("Sidorov");
            p3 = new Player("Pupkin");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Game game1 = new Game();
        game1.addPlayer(p1);
        game1.addPlayer(p2);

        Game game2 = new Game();
        game2.addPlayer(p1);
        game2.addPlayer(p3);


        //checking if the all players are contained in the set of players
        Assertions.assertEquals(Service.getPlayerSet().contains(p1), true);
        Assertions.assertEquals(Service.getPlayerSet().contains(p2), true);
        Assertions.assertEquals(Service.getPlayerSet().contains(p3), true);

        //get the set of all players and then the set of games that these players play
        Set<Game> gs = Service.getAllPlayersGamesList(Service.getPlayerSet());
        //checking if the game1 and game2 contained in the set of games
        Assertions.assertEquals(gs.contains(game1), true);
        Assertions.assertEquals(gs.contains(game2), true);
    }

    @Test
    void PlayGameWithNoOnePlayer() {
        Game game1 = new Game();
        Assertions.assertEquals(game1.Play(),false);
        //the property gamePlayed must not be changed
        Assertions.assertEquals(game1.isGamePlayed(),false);
    }

    @Test
    void PlayGameWithOnePlayer() {
        Player p1 = null, p2 = null;

        try {
            p1 = new Player("Petrov");
//            p2 = new Player("Sidorov");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Game game1 = new Game();
        game1.addPlayer(p1);
        game1.addPlayer(p2);

        Assertions.assertEquals(game1.Play(),false);
    }


    @Test
    void PlayTheSameGameTwice() {
        Player p1 = null, p2 = null;

        try {
            p1 = new Player("Petrov");
            p2 = new Player("Sidorov");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Game game1 = new Game();
        game1.addPlayer(p1);
        game1.addPlayer(p2);
        game1.Play();
        Assertions.assertEquals(game1.Play(),false);
    }


    @Test
    void getPlayersRating() {
        Player p1 = null, p2 = null, p3 = null;

        try {
            p1 = new Player("Petrov");
            p2 = new Player("Sidorov");
            p3 = new Player("Pupkin");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Game game1 = new Game();
        game1.addPlayer(p1);
        game1.addPlayer(p2);
        game1.Play();

        Game game2 = new Game();
        game2.addPlayer(p1);
        game2.addPlayer(p3);
        game2.Play();

        Game game3 = new Game();
        game3.addPlayer(p2);
        game3.addPlayer(p3);
        game3.Play();

    }

    //выводит 10 лучших игроков в определенной игре
    @Test
    void get10BestPlayers() {
    }

    //выводит 10 лучших игроков с учетом всех игр
    @Test
    void testGet10BestPlayers() {
    }
}