package ghoulish.labyrinth;

import ghoulish.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bones extends PartDecorator {

    public Bones(int i, int j, Part _p, String s){
        super(i,j,_p,s);
    }

    public boolean attemptMove(){
        return true;
    }

    @Override
    public Part collapseLoot() {
        return parent;
    }

    public boolean hasLoot(){
        return true;
    }
}
