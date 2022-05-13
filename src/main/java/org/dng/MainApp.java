package org.dng;

import java.util.HashSet;
import java.util.Set;

public class MainApp {
    static Set<String> playersNicSet = new HashSet<>();
    public static void main(String[] args) {

        try {
            Player p1 = new Player("Petrov");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Player p2 = new Player("Petrov");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
