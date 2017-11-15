package ghoulish.creatures;

import ghoulish.Main;
import ghoulish.graphics.TextureHolder;
import ghoulish.util.TextureContainer;

import java.awt.image.BufferedImage;

public abstract class Creature extends TextureHolder{
    int hp;
    public int yourTurn;

    public Creature(int _y, int _x, int _hp, String _texture, int tu) {
        y = _y;
        x = _x;
        hp = _hp;
        textureName = _texture;
        yourTurn = tu;
        priority = 2;
    }

    public int getHp() {
        return hp;
    }

    public boolean inflictDamage(int damage) {
        hp -= damage;
        return hp < 1;
    }

    public void move(int dy, int dx) {
        y += dy;
        x += dx;
    }


    public boolean isAlive(){
        return hp>0;
    }

    public boolean isDead(){
        return !isAlive();
    }
}
