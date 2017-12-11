package ghoulish.creatures;

import ghoulish.strategies.IStrategy;

public abstract class Monster extends Creature implements Cloneable {
    public static int angerRange = 7;
    public IStrategy strategy;
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

    public abstract void accept(IVisitor visitor);

    public void setStrategy(IStrategy strat){
        strategy = strat;
    }

    public IStrategy getStrategy() {
        return strategy;
    }
}
