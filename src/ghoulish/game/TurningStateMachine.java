package ghoulish.game;

import ghoulish.creatures.*;
import ghoulish.util.MoveAnswer;
import ghoulish.window.Visualiser;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import static ghoulish.game.State.*;

public class TurningStateMachine {
    static TurningStateMachine instance;
    public State state;
    AI ai;
    Player player = Player.getInstance();
    MoveAnswer moveAnswer = MoveAnswer.getInstance();
    public Boolean kek = true;
    public final MyThread thread = new MyThread(this);

    private TurningStateMachine() {
        ai = new AI();
        state = PlayerTurn;

    }

    public static TurningStateMachine getInstance() {
        if (instance == null)
            instance = new TurningStateMachine();
        return instance;
    }

    public void playerMove(int dy, int dx) {
        int y = player.getY() + dy;
        int x = player.getX() + dx;
        if (state == PlayerTurn && moveAnswer.canMovePlayer(y, x)) {
//            if(moveAnswer.noMonster(y,x)) {
            Visualiser visualiser = Visualiser.getInstance();

            player.move(dy, dx);

            moveAnswer.playerMoved();

            visualiser.drawFullGameScreen();

            if (state != Death) {
                nextTurn();
            } else {
                visualiser.drawGrey();
            }
//            }else{
//
//            }
        }
    }

    private void nextTurn() {
        state = MonsterTurn;

        //Запуск потока обработки ходов противников
        if (kek)
            thread.start();
        kek = false;

        synchronized (thread){
            thread.notify();
        }

        state = PlayerTurn;
    }

    public void ePressed() {
        if (state == PlayerTurn) {
            moveAnswer.playerTryToLoot();
        }
    }

    public void wPressed() {
        playerMove(-1, 0);
    }

    public void aPressed() {
        playerMove(0, -1);
    }

    public void sPressed() {
        playerMove(1, 0);
    }

    public void dPressed() {
        playerMove(0, 1);
    }

    public void spacePressed() {
        nextTurn();
    }

    public static class Trio {
        Creature cr;
        int x;
        int y;

        Trio(Creature creature, int dx, int dy) {
            cr = creature;
            x = dx;
            y = dy;
        }
    }

    public static class MyThread extends Thread {
        TurningStateMachine turningStateMachine;
        int i = 1;

        MyThread(TurningStateMachine t) {
            turningStateMachine = t;
        }

        @Override
        public void run() {
            synchronized (this) {
                try {
                    while (true) {
                        if (turningStateMachine.state == MonsterTurn) {
                            turningStateMachine.ai.calculatePath(turningStateMachine.player.getY(), turningStateMachine.player.getX());

                            ArrayList<Monster> creatures = Layer1.getInstance().creatures;

                            LinkedList<Monster> queue = new LinkedList<>();
                            queue.addAll(creatures);

                            while (!queue.isEmpty()) {
                                Monster creature = queue.poll();
                                Pair<Integer, Integer> cur = turningStateMachine.ai.monsterMove(creature.getY(), creature.getX(), 1000);

                                Visualiser visualiser = Visualiser.getInstance();

                                try {
                                    this.wait();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                System.out.println("Success");

                                creature.move(cur.getKey(), cur.getValue());
                                turningStateMachine.moveAnswer.monsterMoved(creature);

                                visualiser.drawFullGameScreen();
                            }
                        }else
                            try {
                                this.wait();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
