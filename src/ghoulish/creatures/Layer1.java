package ghoulish.creatures;

import ghoulish.game.ISubject;
import ghoulish.game.ISubscriber;
import ghoulish.util.IMonsterGiver;
import ghoulish.util.MonsterPlacer;
import ghoulish.util.MonsterReader;

import java.util.ArrayList;

public class Layer1 implements Cloneable, ISubject {
    private static Layer1 instance = null;
    public ArrayList<Monster> creatures = new ArrayList<>();
    ArrayList<ISubscriber> subs = new ArrayList<>();
    final boolean clever = true;

    private Layer1() {
        IMonsterGiver monsterGiver;
        if (clever)
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        Layer1 cur = (Layer1) super.clone();
        ArrayList<Monster> curCreatures = new ArrayList<>();
        for (Monster monster : creatures) {
            curCreatures.add((Monster) monster.clone());
        }
        cur.creatures = curCreatures;
        return cur;
    }

    public static synchronized void setInstance(Layer1 layer1) {
        instance = layer1;
        instance.notifySubs();
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

    public boolean allMonstersMoved(int turn) {
        for (Monster mon : creatures) {
            if (mon.yourTurn != (turn + 1))
                return false;
        }

        return true;
    }

    @Override
    public void addSub(ISubscriber sub) {
        getInstance().subs.add(sub);
    }

    @Override
    public void deleteSub(ISubscriber sub) {
        getInstance().subs.remove(sub);
    }

    @Override
    public void notifySubs() {
        for (ISubscriber subscriber : subs)
            subscriber.update();
    }
}
