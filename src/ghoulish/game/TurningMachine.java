package ghoulish.game;

import ghoulish.creatures.AI;
import ghoulish.creatures.Creature;
import ghoulish.creatures.Player;
import ghoulish.window.GamePanel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class TurningMachine {
    boolean inProgress;
    GamePanel gamePanel;
    LinkedList<Trio> queue = new LinkedList<>();
    AI ai;

    TurningMachine(GamePanel _gamePanel , AI _ai){
        ai = _ai;
        inProgress = false;
        gamePanel = _gamePanel;

        Timer time = new Timer();

        time.schedule(new TimerTask() {

            @Override
            public void run() {
                if(!inProgress && !queue.isEmpty()){
                    Trio cur = queue.poll();
                    moveCreature(cur.cr, cur.x, cur.y);
                }
            }
        }, 0,50);
    }

    public void queueMove(Creature creature, int dx, int dy){
        queue.add(new Trio(creature, dx, dy));
    }

    public void moveCreature(Creature creature, int dx, int dy){
        inProgress = true;

        Timer time = new Timer();

        time.schedule(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                if (i >= 10) {
                    if(creature instanceof Player)
                        ai.calculatePath(creature.getIY(), creature.getIX());

                    inProgress = false;
                    time.cancel();
                }else{
                    creature.move(dy*1.0/10, dx*1.0/10);
                    gamePanel.repaint();
                }

                i = i + 1;
            }
        }, 0,20);
    }

    public void nextTurn(){
        inProgress = true;

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

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Creature getCr() {
            return cr;
        }
    }

}
