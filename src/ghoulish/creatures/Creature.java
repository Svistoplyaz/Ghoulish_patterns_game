package ghoulish.creatures;

import ghoulish.util.TextureContainer;

import java.awt.image.BufferedImage;

public abstract class Creature {
    int y;
    int x;
    int hp;
    String textureName;

    public Creature(int _y, int _x, int _hp, String texture) {
        y = _y;
        x = _x;
        hp = _hp;
        textureName = texture;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean inflictDamage(int damage) {
        hp -= damage;

        if (hp <= 0)
            return false;
        return true;
    }

    public void move(int dy, int dx) {
        y += dy;
        x += dx;
    }

    public BufferedImage getTexture(){
        return TextureContainer.getTexture(textureName);
    }
}
