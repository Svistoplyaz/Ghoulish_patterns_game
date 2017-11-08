package ghoulish.labyrinth;

import ghoulish.Main;
import ghoulish.util.TextureContainer;

import java.awt.image.BufferedImage;


public abstract class Part {
    String textureName;
    Part parent = null;
    int y;
    int x;

    Part(int i, int j, Part _parent, String s) {
        y = i;
        x = j;
        parent = _parent;
        textureName = s;
    }

    Part(int i, int j, String s) {
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

    public int getPictureY() {
        return y * Main.scale;
    }

    public int getPictureX() {
        return x * Main.scale;
    }

    public abstract boolean attemptMove();

    public BufferedImage getTexture() {
        return TextureContainer.getTexture(this.getTextureName());
    }

    public String getTextureName() {
        if (this.parent == null)
            return textureName;

        return this.parent.getTextureName() + " " + textureName;
    }

    public boolean trapCheck() {
        return parent != null && parent.trapCheck();

    }

    public boolean lootCheck() {
        return parent != null && parent.lootCheck();
    }

    public Part collapseDanger() {
        if (this instanceof Trap)
            return parent;

        if (this.parent instanceof Trap) {
            this.parent = this.parent.parent;
            return this;
        } else if (parent != null)
            return parent.collapseDanger();

        return null;
    }

    public Part collapseLoot() {
        if (this instanceof Bones)
            return parent;

        if (this.parent instanceof Bones) {
            this.parent = this.parent.parent;

            return this;
        } else if (parent != null)
            return parent.collapseLoot();

        return null;
    }

    public Part getParent() {
        return parent;
    }

    public boolean hasTrap() {
        if (this.parent == null)
            return false;

        return this instanceof Trap || parent.hasTrap();

    }

    public boolean hasDoor() {
        if (this.parent == null)
            return false;

        return this instanceof Door || parent.hasDoor();
    }

    public boolean hasLoot() {
        if (this.parent == null)
            return false;

        return this instanceof Bones || parent.hasLoot();
    }

    public void openDoor() {
        if (parent == null)
            return;

        parent.openDoor();
    }
}
