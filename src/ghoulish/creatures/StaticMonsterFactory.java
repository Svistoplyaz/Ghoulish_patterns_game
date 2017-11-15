package ghoulish.creatures;

public class StaticMonsterFactory implements IMonsterFactory{
    public Monster createMonster(int y, int x){
        return new StaticMonster(y, x, 1, "resources/Creature/Monster/StaticMonster.png", 1);
    }
}
