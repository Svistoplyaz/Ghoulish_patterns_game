package ghoulish.creatures;

import ghoulish.Main;
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

    public int getPictureX() {
        return x * Main.scale;
    }

    public int getPictureY() {
        return y * Main.scale;
    }

    public int getHp() {
        return hp;
    }

    public boolean inflictDamage(int damage) {
        hp -= damage;

        return hp > 0;
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
