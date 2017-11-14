package ghoulish.game;

import ghoulish.creatures.*;
import ghoulish.labyrinth.Labyrinth;
import ghoulish.labyrinth.Part;
import ghoulish.window.Visualiser;
import javafx.util.Pair;

import java.util.Random;

import static ghoulish.game.State.*;
import static ghoulish.game.MoveAnswer.Answer.*;

public class MoveAnswer {
    public enum Answer {
        canMove, cannotMove, startBattle;
    }

    private static MoveAnswer instance;
    private Labyrinth lab = Labyrinth.getInstance();
    private Layer1 layer1 = Layer1.getInstance();
    private Player player = Player.getInstance();
    private Random random = new Random();

    public static MoveAnswer getInstance() {
        if (instance == null)
            instance = new MoveAnswer();
        return instance;
    }

    public Answer canMovePlayer(int y, int x) {
        boolean canmove = lab.canMoveHere(y, x) || lab.getPart(y, x).hasDoor();

        if (!canmove)
            return cannotMove;
        else if (noMonster(y, x)) {
            return canMove;
        } else
            return startBattle;
    }

    public boolean noWallOrClosedDoor(int y, int x) {
        return lab.canMoveHere(y, x) && (!lab.getPart(y, x).hasDoor() || lab.getPart(y, x).hasDoor() && lab.canMoveHere(y, x));
    }

    public boolean noPlayer(int y, int x) {
        return player.getY() != y || player.getX() != x;
    }

    public boolean noMonster(int y, int x) {
        return !layer1.hasMonster(y, x);
    }

    public Answer moveMonster(Monster monster, AI ai) {
        Pair<Integer, Integer> cur = ai.monsterMove(monster.getY(), monster.getX(), 1000);
        if (noPlayer(monster.getY() + cur.getKey(), monster.getX() + cur.getValue())) {
            monster.move(cur.getKey(), cur.getValue());
            monsterMoved(monster);
            return canMove;
        } else {
            return startBattle;
        }
    }

    public boolean canMoveMonster(int y, int x) {
        return lab.canMoveHere(y, x) && !layer1.hasMonster(y, x);
    }

    public void playerMoved() {
        int y = player.getY();
        int x = player.getX();

        Part part = lab.getPart(y, x);
        if (part.hasDoor()) {
            part.openDoor();
            Visualiser.getInstance().localBackgroundRedraw(y, x);
        }

        if (part.hasTrap()) {
            lab.collapseDanger(y, x);
            Visualiser.getInstance().localBackgroundRedraw(y, x);

            if (random.nextBoolean())
                creatureChangeHP(player, random.nextInt(4) + 1);
        }
    }

    public void playerTryToLoot() {
        int y = player.getY();
        int x = player.getX();

        Part part = lab.getPart(y, x);
        if (part.hasLoot()) {
            lab.collapseLoot(y, x);
            Visualiser.getInstance().localBackgroundRedraw(y, x);

            creatureChangeHP(player,-1);
        }
    }

    public void monsterMoved(Monster monster) {
        int y = monster.getY();
        int x = monster.getX();

        Part part = lab.getPart(y, x);

        if (part.hasTrap()) {
            lab.collapseDanger(y, x);
            Visualiser.getInstance().localBackgroundRedraw(y, x);

            if (random.nextBoolean())
                creatureChangeHP(monster, random.nextInt(2) + 1);
        }
    }
//
//    private void monsterChangedHP(Monster monster, int value) {
//        monster.inflictDamage(value);
//
//        if (monster.getHp() < 1)
//            layer1.monsterDied(monster);
//    }

    private void creatureChangeHP(Creature creature, int value) {
        creature.inflictDamage(value);

        if (creature.getHp() < 1) {
            if (creature instanceof Player)
                TurningStateMachine.getInstance().state = Death;
            else if (creature instanceof Monster)
                layer1.monsterDied((Monster) creature);
        }
    }


    public boolean fight(Battle battle, int num) {
        Creature creature1 = battle.attacker;
        Creature creature2 = battle.defender;

        if (creature2 instanceof Monster) {
            if (num == battle.weakSpot)
                creatureChangeHP(creature2, 100);
        } else
            creatureChangeHP(creature2, 1);
        if (creature2.getHp() > 0) {
            if (creature1 instanceof Monster) {
                if (num == battle.weakSpot)
                    creatureChangeHP(creature1, 100);
            } else
                creatureChangeHP(creature1, 1);
        }

        return creature1.isDead() || creature2.isDead();

    }
}
