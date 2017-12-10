package ghoulish.creatures;

public class CleverMonster extends Monster {
    public CleverMonster(int _y, int _x, int _hp, String texture, int tu) {
        super(_y, _x, _hp, texture, tu);
    }

    public boolean blind(){
        return false;
    }

    public boolean dynamic(){
        return true;
    }

    public boolean smart() {
        return true;
    }

    public void accept(IVisitor visitor){
        visitor.visit(this);
    }
}
