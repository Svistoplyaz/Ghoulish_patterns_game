package ghoulish.window;

import ghoulish.Main;
import ghoulish.labyrinth.Layer0;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    static GamePanel instance;
    BufferedImage image;
    Font font;

    private GamePanel(){
        Layer0 lab = Layer0.getInstance();
        int height = (lab.getN()) * Main.scale;
        int width = (lab.getM()) * Main.scale;
        this.setSize(width, height);

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("resources/font.ttf"));
        } catch (IOException |FontFormatException e) {
            e.printStackTrace();
        }

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
//        g.setFont(font.deriveFont(20.0f));
//        g.setColor(Color.WHITE);
//        g.drawString("This is gona be awesome", 200, 200);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getWidth(), this.getHeight());
    }
}
