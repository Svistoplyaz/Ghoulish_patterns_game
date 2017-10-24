package ghoulish.window;

import ghoulish.Game;
import ghoulish.labyrinth.Labyrinth;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class GamePanel extends JPanel {
    Game game = new Game();

    public GamePanel(){
//        this.setSize(game.getWidth(), game.getHeight());
        this.setSize(120,160);

        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g){
        g.drawLine(120,0,120,120);
        g.drawLine(0,120,120,120);
//        g.drawImage(game.getBackground(),0,0,null);
    }
}
