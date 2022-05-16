package org.dng;

//import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ServiceTest {
    private String nicName2;
    @BeforeEach
    public void setUp(){
        Service.staticPropertiesClear();
    }

    //    регистрирует игроков в системе (должна быть проверка, занят ли ник)
    @Test
    void t1_duplicatedPlayerRegisteringInSystem() {
        String nicName = "Duplicatov";
        try {
            new Player(nicName);
            new Player(nicName);
            Assertions.fail("Exception was expected!");
        } catch (Exception e) {
            System.out.println("Test done. Caught exception = " + e.getMessage());
            Assertions.assertNotEquals("", "Player cant be created! NicName `" + nicName + "` already exist!");
        }
    }

    @Test
    void t2_playerRegisteringInSystem() {
        Player p1 = null;
        String nicName = "Petrov";
        try {
            p1 = new Player(nicName);
        } catch (Exception e) {
            Assertions.fail("Exception was not expected!");
        }
        //checking if the name was added to the set
        Assertions.assertTrue(Service.getPlayersNicSet().containsKey(nicName));
        //checking if the instance of Player contained in the set of players
        Assertions.assertTrue(Service.getPlayerSet().contains(p1));
    }

    @Test
    void t3_gameRegisteringInSystem() {
        Game game1 = null;
        try {
            game1 = new Game();
        } catch (Exception e) {
            Assertions.fail("Exception was not expected!");
        }

        //checking if the instance of Game contained in the set of games
        Assertions.assertTrue(Service.getGameSet().contains(game1));
    }


//    выводит список игр, в которые играют все игроки на сайте
    @Test
    void t4_getAllPlayersGamesList() {
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
        Assertions.assertTrue(Service.getPlayerSet().contains(p1));
        Assertions.assertTrue(Service.getPlayerSet().contains(p2));
        Assertions.assertTrue(Service.getPlayerSet().contains(p3));

        //get the set of all players and then the set of games that these players play
        Set<Game> gs = Service.getAllPlayersGamesList(Service.getPlayerSet());
        //checking if the game1 and game2 contained in the set of games
        Assertions.assertTrue(gs.contains(game1));
        Assertions.assertTrue(gs.contains(game2));
    }

    @Test
    void t5_PlayGameWithNoOnePlayer() {
        Game game1 = new Game();
        Assertions.assertFalse(game1.Play());
        //the property gamePlayed must not be changed
        Assertions.assertFalse(game1.isGamePlayed());
    }

    @Test
    void t6_PlayGameWithOnePlayer() {
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

        Assertions.assertFalse(game1.Play());
    }


    @Test
    void t7_PlayTheSameGameTwice() {
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
        Assertions.assertFalse(game1.Play());
    }


    @Test
    void t8_getPlayersRating() {
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
    void t9_get10BestPlayers() {
    }

    //выводит 10 лучших игроков с учетом всех игр
    @Test
    void t10_testGet10BestPlayers() {
    }
}