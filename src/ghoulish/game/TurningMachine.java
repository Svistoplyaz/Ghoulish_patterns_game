package ghoulish.game;

import ghoulish.creatures.AI;
import ghoulish.creatures.Creature;
import ghoulish.creatures.Layer1;
import ghoulish.creatures.Player;
import ghoulish.labyrinth.Labyrinth;
import ghoulish.window.GamePanel;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class TurningMachine {
    boolean inProgress;
    GamePanel gamePanel;
    LinkedList<Trio> queue = new LinkedList<>();
    AI ai;

    TurningMachine(GamePanel _gamePanel){
        ai = new AI(Labyrinth.getInstance());
        inProgress = false;
        gamePanel = _gamePanel;

        Timer time = new Timer();

        time.schedule(new TimerTask() {

            @Override
            public void run() {
                if(!inProgress && !queue.isEmpty()){
                    Trio cur = queue.poll();
                    moveCreature(cur.cr, cur.y, cur.x);
                }
            }
        }, 0,50);
    }

    public void queueMove(Creature creature, int dy, int dx){
        queue.add(new Trio(creature, dx, dy));
        creature.move(dy,dx);
    }

    public void moveCreature(Creature creature, int dy, int dx){
        inProgress = true;

        Timer time = new Timer();

        time.schedule(new TimerTask() {
            int i = 0;
            int frames = 20;

            @Override
            public void run() {
                if (i >= frames) {
                    if(creature instanceof Player) {
                        ai.calculatePath(creature.getIY(), creature.getIX());
                        nextTurn();
                    }

                    inProgress = false;
                    time.cancel();
                }else{
                    creature.move(dy*1.0/frames, dx*1.0/frames);
                    gamePanel.repaint();
                }

                i = i + 1;
            }
        }, 0,20);
//
//        while(inProgress);

        int i = 1;
    }

    public void nextTurn(){
        ArrayList<Creature> creatures = Layer1.getInstance().creatures;

        for(Creature creature : creatures){
            Pair<Integer, Integer> cur = ai.monsterMove(creature.getIY(),creature.getIX(),10);
//            creature.move(cur.getKey(), cur.getValue());

            queueMove(creature, cur.getKey(), cur.getValue());
        }

        inProgress = false;
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
