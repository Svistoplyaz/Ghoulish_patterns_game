package ghoulish.creatures;

public class BlindMonsterFactory implements IMonsterFactory{
    public Monster createMonster(int y, int x){
        return new BlindMonster(y, x, 1, "resources/Creature/Monster/BlindMonster.png", 1);
    }
}
