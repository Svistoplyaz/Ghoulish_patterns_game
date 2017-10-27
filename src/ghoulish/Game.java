package ghoulish;

import ghoulish.labyrinth.Labyrinth;
import ghoulish.labyrinth.Part;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Game {
    private Labyrinth lab;
    private int height, width;
    private BufferedImage background;

    public Game(){
        lab = new Labyrinth();
        height = (lab.getN())*Main.scale;
        width = (lab.getM())*Main.scale;

        background = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Part[][] parts = lab.getParts();

        Graphics g = background.getGraphics();
        int n = lab.getN(), m = lab.getM();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                g.drawImage(parts[i][j].getTexture(), j*Main.scale, i*Main.scale, Main.scale,Main.scale, null);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public BufferedImage getBackground() {
        return background;
    }
}
