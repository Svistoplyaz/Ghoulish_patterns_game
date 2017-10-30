package ghoulish.labyrinth;

import ghoulish.util.TextureContainer;

import java.awt.image.BufferedImage;


public abstract class Part {
    String textureName;
    Part parent = null;
    int y;
    int x;

    Part(int i, int j, Part _parent, String s){
        y = i;
        x = j;
        parent = _parent;
        textureName = parent.getTextureName() + " " + s;
    }

    Part(int i, int j, String s){
        y = i;
        x = j;
        textureName = s;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public abstract boolean attemptMove();

    public BufferedImage getTexture(){
        return TextureContainer.getTexture(textureName);
    }

    public String getTextureName(){
        return textureName;
    }

    public boolean trapCheck() {
        return parent != null && parent.trapCheck();

    }

    public boolean lootCheck() {
        return parent != null && parent.lootCheck();
    }

    public Part collapseDanger(){
        if(this instanceof Trap)
            return parent;

        if(this.parent instanceof Trap) {
            this.parent = this.parent.parent;
            return this;
        }
        else if(parent != null)
            return parent.collapseDanger();

        return null;
    }

    public Part collapseLoot(){
        if(this instanceof Bones)
            return parent;

        if(this.parent instanceof Bones) {
            this.parent = this.parent.parent;
            return this;
        }
        else if(parent != null)
            return parent.collapseLoot();

        return null;
    }
}
