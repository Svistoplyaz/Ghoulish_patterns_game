package ghoulish.labyrinth;

import ghoulish.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bones extends Part {

    public Bones(int i, int j, Part _p, String s){
        super(i,j,_p,s);
    }

    public boolean attemptMove(){
        return true;
    }

    public boolean lootCheck(){
        return true;
    }
}
