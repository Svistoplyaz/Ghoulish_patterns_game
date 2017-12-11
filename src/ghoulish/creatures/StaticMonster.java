package ghoulish.creatures;

import ghoulish.strategies.StaticStrategy;

public class StaticMonster extends Monster implements Cloneable {
    StaticMonster(int _y, int _x, int _hp, String texture, int tu) {
        super(_y, _x, _hp, texture, tu);
        setStrategy(new StaticStrategy(this));
    }

    public boolean blind() {
        return false;
    }

    public boolean dynamic() {
        return false;
    }

    public boolean smart() {
        return false;
    }

    public void accept(IVisitor visitor){
        visitor.visit(this);
    }

    @Override
    public StaticMonster clone() {
        return new StaticMonster(y, x, hp, textureName, yourTurn);
    }
}
