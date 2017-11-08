package ghoulish.game;

import ghoulish.creatures.*;
import ghoulish.util.MoveAnswer;
import ghoulish.window.Visualiser;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;

public class TurningStateMachine {
    static TurningStateMachine instance;
    public String state;
    AI ai;
    Player player = Player.getInstance();
    MoveAnswer moveAnswer = MoveAnswer.getInstance();

    private TurningStateMachine(){
        ai = new AI();
        state = "PlayerTurn";
    }

    public static TurningStateMachine getInstance() {
        if(instance == null)
            instance = new TurningStateMachine();
        return instance;
    }

    public void playerMove(int dy, int dx){
        int y = player.getY() + dy;
        int x = player.getX() + dx;
        if (state.equals("PlayerTurn") && moveAnswer.canMovePlayer(y, x)){
//            if(moveAnswer.noMonster(y,x)) {
                state = "MonsterTurn";
                Visualiser visualiser = Visualiser.getInstance();

                player.move(dy, dx);

                moveAnswer.playerMoved();

                visualiser.drawFullGameScreen();

                if (!state.equals("Death")) {
                    nextTurn();
                } else {
                    visualiser.drawGrey();
                }
//            }else{
//
//            }
        }
    }

    public void nextTurn(){
        ai.calculatePath(player.getY(), player.getX());

        ArrayList<Monster> creatures = Layer1.getInstance().creatures;

        LinkedList<Monster> queue = new LinkedList<>();
        for(Monster creature : creatures){
            queue.add(creature);
        }

        while (!queue.isEmpty()){
            Monster creature = queue.poll();
            Pair<Integer, Integer> cur = ai.monsterMove(creature.getY(),creature.getX(),1000);

            Visualiser visualiser = Visualiser.getInstance();

            creature.move(cur.getKey(),cur.getValue());
            moveAnswer.monsterMoved(creature);

            visualiser.drawFullGameScreen();
        }

        state = "PlayerTurn";
    }

    public void ePressed(){
        if(state.equals("PlayerTurn")){
            moveAnswer.playerTryToLoot();
        }
    }

    public void wPressed(){
        playerMove(-1,0);
    }

    public void aPressed(){
        playerMove(0,-1);
    }

    public void sPressed(){
        playerMove(1,0);
    }

    public void dPressed(){
        playerMove(0,1);
    }

    public void spacePressed() {
        if(state.equals("PlayerTurn"))
            nextTurn();
    }

    public static class Trio{
        Creature cr;
        int x;
        int y;

        Trio(Creature creature, int dx, int dy){
            cr = creature;
            x = dx;
            y = dy;
        }
    }

}
