package ghoulish;

import ghoulish.window.GameFrame;

import javax.swing.*;

public class Main {
    public static int scale = 40;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

//        game = new Game();
        GameFrame gf = new GameFrame();

        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime)*1.0/100+"");

    }
}
