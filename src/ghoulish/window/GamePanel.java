package ghoulish.window;

import ghoulish.Main;
import ghoulish.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {
    Game game = new Game();

    public GamePanel(){
        this.setSize(game.getWidth(), game.getHeight());
//        this.setSize(120,160);

        this.setVisible(true);


    }

    @Override
    public void paint(Graphics g){
//        System.out.println("Redrawing");
//        long startTime = System.currentTimeMillis();

        g.drawImage(game.getBackground(),0,0,null);
        g.drawImage(game.player.getTexture(), game.player.getX() * Main.scale, game.player.getY() * Main.scale, Main.scale, Main.scale, null);
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
