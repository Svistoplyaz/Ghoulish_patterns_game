package ghoulish.window;

import ghoulish.Main;
import ghoulish.creatures.Creature;
import ghoulish.labyrinth.Labyrinth;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    static GamePanel instance;
    BufferedImage image;

    private GamePanel(){
        Labyrinth lab = Labyrinth.getInstance();
        int height = (lab.getN()) * Main.scale;
        int width = (lab.getM()) * Main.scale;
        this.setSize(width, height);

        this.setVisible(true);
    }

    public static GamePanel getInstance() {
        if(instance == null)
            instance = new GamePanel();
        return instance;
    }

    public void setImage(BufferedImage im){
        image = im;
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(image,0,0,null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getWidth(), this.getHeight());
    }
}
