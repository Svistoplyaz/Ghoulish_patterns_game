package ghoulish.util;

import ghoulish.creatures.*;
import ghoulish.game.Battle;
import ghoulish.game.TurningStateMachine;
import ghoulish.labyrinth.Labyrinth;
import ghoulish.labyrinth.Part;
import ghoulish.window.Visualiser;
import javafx.util.Pair;

import java.util.Random;

import static ghoulish.game.State.*;
import static ghoulish.util.MoveAnswer.Answer.*;

public class MoveAnswer {
    public enum Answer{
        canMove, cannotMove, startBattle;
    }

    private static MoveAnswer instance;
    private Labyrinth lab = Labyrinth.getInstance();
    private Layer1 layer1 = Layer1.getInstance();
    private Player player = Player.getInstance();
    private Random random = new Random();

    public static MoveAnswer getInstance() {
        if(instance == null)
            instance = new MoveAnswer();
        return instance;
    }

    public Answer canMovePlayer(int y, int x) {
        boolean canmove = lab.canMoveHere(y, x) || lab.getPart(y, x).hasDoor();

        if(!canmove)
            return cannotMove;
        else if(noMonster(y,x)){
            return canMove;
        }else
            return startBattle;
    }

    public boolean noWallOrClosedDoor(int y, int x) {
        return lab.canMoveHere(y, x) && (!lab.getPart(y, x).hasDoor()||lab.getPart(y, x).hasDoor() && lab.canMoveHere(y, x));
    }

    public boolean noPlayer(int y, int x) {
        return player.getY() != y || player.getX()!=x ;
    }

    public boolean noMonster(int y, int x) {
        return !layer1.hasMonster(y,x);
    }

    public Answer moveMonster(Monster monster, AI ai){
        Pair<Integer, Integer> cur = ai.monsterMove(monster.getY(), monster.getX(), 1000);
        if(noPlayer(monster.getY()+cur.getKey(),monster.getX()+cur.getValue())) {
            monster.move(cur.getKey(), cur.getValue());
            monsterMoved(monster);
            return canMove;
        }else{
            return startBattle;
        }
    }

    public boolean canMoveMonster(int y, int x) {
        return lab.canMoveHere(y, x) && !layer1.hasMonster(y, x);
    }

    public void playerMoved(){
        int y = player.getY();
        int x = player.getX();

        Part part = lab.getPart(y, x);
        if(part.hasDoor()){
            part.openDoor();
            Visualiser.getInstance().localBackgroundRedraw(y,x);
        }

        if(part.hasTrap()){
            lab.collapseDanger(y,x);
            Visualiser.getInstance().localBackgroundRedraw(y,x);

            if(random.nextBoolean())
                playerChangedHP(random.nextInt(4)+1);
        }
    }

    public void playerTryToLoot(){
        int y = player.getY();
        int x = player.getX();

        Part part = lab.getPart(y, x);
        if(part.hasLoot()){
            lab.collapseLoot(y,x);
            Visualiser.getInstance().localBackgroundRedraw(y,x);

            playerChangedHP(-1);
        }
    }

    public void playerChangedHP(int value){
        player.inflictDamage(value);

        if(player.getHp() < 1)
            TurningStateMachine.getInstance().state = Death;
    }

    public void monsterMoved(Monster monster){
        int y = monster.getY();
        int x = monster.getX();

        Part part = lab.getPart(y, x);

        if(part.hasTrap()){
            lab.collapseDanger(y,x);
            Visualiser.getInstance().localBackgroundRedraw(y,x);

            if(random.nextBoolean())
                monsterChangedHP(monster,random.nextInt(2)+1);
        }
    }

    private void monsterChangedHP(Monster monster, int value) {
        monster.inflictDamage(value);

        if(monster.getHp() < 1)
            layer1.monsterDied(monster);
    }

    public void fight(Battle battle){
        Creature creature1 = battle.attacker;
        Creature creature2 = battle.defender;

        while(creature1.getHp() > 0 && creature2.getHp() > 0){
            creature2.inflictDamage(random.nextInt(2)+1);
            if(creature2.getHp() > 0)
                creature1.inflictDamage(random.nextInt(2)+1);
        }
    }
}
