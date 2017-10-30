package ghoulish;

import ghoulish.window.GameFrame;

import javax.swing.*;

public class Main {
    public static int scale = 40;

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        GameFrame gf = new GameFrame();

        long endTime = System.nanoTime();
        System.out.println(endTime - startTime+"");

    }
}
