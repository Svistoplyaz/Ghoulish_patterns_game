package ghoulish.creatures;

public abstract class Monster extends Creature implements Cloneable {
    public Monster(int _y, int _x, int _hp, String texture, int tu) {
        super(_y, _x, _hp, texture, tu);
    }

    public abstract boolean blind();

    public abstract boolean dynamic();

    public abstract boolean smart();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
