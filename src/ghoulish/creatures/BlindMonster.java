package ghoulish.creatures;

public class BlindMonster extends Monster {
    BlindMonster(int _y, int _x, int _hp, String texture, int tu) {
        super(_y, _x, _hp, texture, tu);
    }

    public boolean blind(){
        return true;
    }

    public boolean dynamic(){
        return true;
    }

    @Override
    public boolean smart() {
        return false;
    }
}