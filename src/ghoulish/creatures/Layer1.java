package ghoulish.creatures;

import ghoulish.util.IMonsterGiver;
import ghoulish.util.MonsterPlacer;
import ghoulish.util.MonsterReader;

import java.util.ArrayList;

public class Layer1 {
    private static Layer1 instance = null;
    public ArrayList<Monster> creatures = new ArrayList<>();
    final boolean clever = true;

    private Layer1() {
        IMonsterGiver monsterGiver;
        if(clever)
            monsterGiver = new MonsterPlacer();
        else
            monsterGiver = new MonsterReader();
        creatures = monsterGiver.readMonsters();
    }

    public static synchronized Layer1 getInstance() {
        if (instance == null)
            instance = new Layer1();
        return instance;
    }

    public boolean hasMonster(int y, int x) {
        for (Monster creature : creatures)
            if (creature.getY() == y && creature.getX() == x)
                return true;

        return false;
    }

    public Monster getMonster(int y, int x) {
        for (Monster creature : creatures)
            if (creature.getY() == y && creature.getX() == x)
                return creature;

        return null;
    }

    public void monsterDied(Monster monster) {
        creatures.remove(monster);
    }

    public boolean allMonstersMoved(int turn){
        for(Monster mon : creatures){
            if(mon.yourTurn != (turn+1))
                return false;
        }

        return true;
    }
}
