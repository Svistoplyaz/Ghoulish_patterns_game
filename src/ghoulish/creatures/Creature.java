package ghoulish.creatures;

import ghoulish.util.TextureContainer;

import java.awt.image.BufferedImage;

public abstract class Creature {
    double y;
    double x;
    int hp;
    String textureName;

    public Creature(int _y, int _x, int _hp, String texture) {
        y = _y;
        x = _x;
        hp = _hp;
        textureName = texture;
    }

    public double getDX() {
        return x;
    }

    public double getDY() {
        return y;
    }

    public int getIX() {
        return (int)(x+0.5);
    }

    public int getIY() {
        return (int)(y+0.5);
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

    public void move(double dy, double dx) {
        y += dy;
        x += dx;
    }

    public BufferedImage getTexture(){
        return TextureContainer.getTexture(textureName);
    }
}
