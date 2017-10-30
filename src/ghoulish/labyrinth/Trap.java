package ghoulish.labyrinth;

import ghoulish.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Trap extends Part {
    private boolean dangerous;

    public Trap(int i, int j, Part _p, String s, boolean dan){
        super(i,j,_p,s);
//        dangerous = dan;
        dangerous = true;
    }

    public boolean attemptMove(){
        return true;
    }

    public boolean trapCheck(){
        if(dangerous || parent == null)
            return dangerous;

        return parent.trapCheck();
    }
}
