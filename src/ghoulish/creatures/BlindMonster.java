package ghoulish.creatures;

import ghoulish.strategies.BlindStrategy;

public class BlindMonster extends Monster {
    BlindMonster(int _y, int _x, int _hp, String texture, int tu) {
        super(_y, _x, _hp, texture, tu);
        setStrategy(new BlindStrategy(this));
    }

    @Override
    public boolean blind(){
        return true;
    }

    @Override
    public boolean dynamic(){
        return true;
    }

    @Override
    public boolean smart() {
        return false;
    }

    public void accept(IVisitor visitor){
        visitor.visit(this);
    }
}
