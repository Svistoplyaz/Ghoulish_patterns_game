package ghoulish.creatures;

public class CleverMonsterFactory implements IMonsterFactory{
    public Monster createMonster(int y, int x){
        return new CleverMonster(y, x, 1, "resources/Creature/Monster/CleverMonster.png", 1);
    }
}
