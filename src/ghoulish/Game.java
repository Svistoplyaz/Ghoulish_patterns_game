package ghoulish;

import ghoulish.labyrinth.Labyrinth;
import ghoulish.labyrinth.Part;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Game {
    Labyrinth lab;
    int height, width;
    BufferedImage background;

    public Game(){
        lab = new Labyrinth("resources/Labyrinth.in");
        height = (lab.getN() + 2)*40;
        width = (lab.getM() + 2)*40;

        background = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Part[][] parts = lab.getParts();

        Graphics g = background.getGraphics();
        int n = lab.getN(), m = lab.getM();
        for(int i = 0; i < n + 2; i++){
            for(int j = 0; j < m + 2; j++){
                g.drawImage(parts[i][j].getTexture(), j*40, i*40, null);
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
