package ghoulish.creatures;

public abstract class Monster extends Creature {
    public Monster(int _y, int _x, int _hp, String texture, int tu) {
        super(_y, _x, _hp, texture, tu);
    }

    public abstract boolean blind();

    public abstract boolean dynamic();

    public abstract boolean smart();
}
