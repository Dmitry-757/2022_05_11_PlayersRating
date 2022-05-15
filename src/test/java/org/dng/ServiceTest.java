package org.dng;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ServiceTest {

    //    регистрирует игроков в системе (должна быть проверка, занят ли ник)
    @Test
    void duplicatedPlayerRegisterInSystem(){
        Player p1 = null;
        String nicName = "Petrov";
        try {
            p1 = new Player(nicName);
            p1 = new Player(nicName);
            Assertions.fail("Exception was expected!");
        } catch (Exception e) {
            System.out.println("Test done. Caught exception = "+ e.getMessage());
            Assertions.assertNotEquals("", "Player cant be created! NicName `" + nicName + "` already exist!");
        }
    }

    @Test
    void playerRegisterInSystem(){
        Player p1 = null;
        String nicName = "Petrov";
        try {
            p1 = new Player(nicName);
        } catch (Exception e) {
            Assertions.fail("Exception was not expected!");
        }

        //checking if the name was added to the set
        Assertions.assertEquals(Service.getPlayersNicSet().containsKey(nicName),true);
        //checking if the instance of Player contained in the set of players
        Assertions.assertEquals(Service.getPlayerSet().contains(p1),true);
    }



    @Test
    void getAllPlayersGamesList() {
    }

    @Test
    void getPlayersRating() {
    }

    @Test
    void get10BestPlayers() {
    }

    @Test
    void testGet10BestPlayers() {
    }
}