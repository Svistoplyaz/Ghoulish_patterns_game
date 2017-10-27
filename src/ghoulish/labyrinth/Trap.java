package ghoulish.labyrinth;

import ghoulish.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Trap extends Part {
    String directory = "resources/Labyrinth/Floor/";
    Part parent;

    public Trap(Part _p, String s){
        parent = _p;
        try{
            BufferedImage im = parent.getTexture(), tmp = ImageIO.read(new File(directory + s));
            texture = new BufferedImage(Main.scale, Main.scale, BufferedImage.TYPE_INT_RGB);

            Graphics g = texture.getGraphics();

            g.drawImage(im, 0, 0, Main.scale, Main.scale, null);
            g.drawImage(tmp, 0, 0, Main.scale, Main.scale, null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean attemptMove(){
        return true;
    }
}