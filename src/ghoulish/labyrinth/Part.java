package ghoulish.labyrinth;

import ghoulish.graphics.TextureHolder;


public abstract class Part extends TextureHolder {
    Part(int i, int j, String s) {
        y = i;
        x = j;
        textureName = s;
        priority = 1;
    }

    public abstract boolean attemptMove();

    public boolean trapCheck() {
        return false;
    }

    public boolean lootCheck() {
        return false;
    }

    public boolean hasTrap() {
        return false;
    }

    public boolean hasDoor() {
        return false;
    }

    public boolean hasLoot() {
        return false;
    }

    public void openDoor(){

    }

    public Part collapseLoot() {
        return this;
    }

    public Part collapseDanger() {
        return this;
    }
}
