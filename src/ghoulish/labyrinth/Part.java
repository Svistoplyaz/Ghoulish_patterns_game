package ghoulish.labyrinth;

import java.awt.image.BufferedImage;

public abstract class Part {
    BufferedImage texture;
    Part parent = null;

    public Part(){

    }

    public abstract boolean attemptMove();

    public BufferedImage getTexture(){
        return texture;
    }
}
