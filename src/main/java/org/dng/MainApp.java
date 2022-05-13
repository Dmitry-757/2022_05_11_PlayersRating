package org.dng;

import java.util.HashMap;

public class MainApp {
    static HashMap<String, Player> playersNicSet = new HashMap<>();
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

        System.out.println("******************************************************************************************");
        Service.logPrint();



    }
}
