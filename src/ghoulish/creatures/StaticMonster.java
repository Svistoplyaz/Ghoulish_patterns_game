package ghoulish.creatures;

public class StaticMonster extends Monster implements Cloneable {
    StaticMonster(int _y, int _x, int _hp, String texture, int tu) {
        super(_y, _x, _hp, texture, tu);
    }

    public boolean blind() {
        return false;
    }

    public boolean dynamic() {
        return false;
    }

    @Override
    public boolean smart() {
        return false;
    }

    @Override
    public StaticMonster clone() {
        return new StaticMonster(y, x, hp, textureName, yourTurn);
    }
}
