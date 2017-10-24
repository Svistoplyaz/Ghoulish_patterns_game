package ghoulish.labyrinth;

import java.awt.image.BufferedImage;

public abstract class Part {
    BufferedImage texture;

    public Part(){

    }

    public abstract boolean attemptMove();

    public BufferedImage getTexture(){
        return texture;
    }
}
