package ghoulish.window;

import ghoulish.Main;
import ghoulish.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {
    Game game;

    public GamePanel(Game _game){
        game = _game;
        this.setSize(game.getWidth(), game.getHeight());
//        this.setSize(120,160);

        this.setVisible(true);

    }

    @Override
    public void paint(Graphics g){
//        System.out.println("Redrawing");
//        long startTime = System.currentTimeMillis();

        g.drawImage(game.getBackground(),0,0,null);
        g.drawImage(game.player.getTexture(), (int)(game.player.getDX() * Main.scale), (int)(game.player.getDY() * Main.scale), Main.scale, Main.scale, null);
//
//        long endTime = System.currentTimeMillis();
//        System.out.println(endTime - startTime+"");
//
//        System.out.println("Redrawn");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(game.getWidth(), game.getHeight());
    }
}
