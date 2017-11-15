package ghoulish.graphics;

import ghoulish.util.TextureContainer;

import java.awt.image.BufferedImage;

public abstract class TextureHolder {
    protected String textureName;
    protected int x;
    protected int y;
    protected int priority;

    public BufferedImage getTexture(){
        return TextureContainer.getTexture(getTextureName());
    }

    public String getTextureName() {
        return textureName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
