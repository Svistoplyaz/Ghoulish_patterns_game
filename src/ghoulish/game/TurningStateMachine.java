package ghoulish.game;

import ghoulish.creatures.*;
import ghoulish.util.MoveAnswer;
import ghoulish.window.Visualiser;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;

import static ghoulish.game.State.*;
import static ghoulish.util.MoveAnswer.Answer.*;

public class TurningStateMachine {
    static TurningStateMachine instance;
    public State state;
    AI ai;
    Player player = Player.getInstance();
    MoveAnswer moveAnswer = MoveAnswer.getInstance();
    public final MyThread thread = new MyThread(this);
    public String pressedKey;
    public int turn;
    public Battle currentBattle;

    private TurningStateMachine() {
        ai = new AI();
        state = PlayerTurn;
        turn = 1;
    }

    public static TurningStateMachine getInstance() {
        if (instance == null)
            instance = new TurningStateMachine();
        return instance;
    }

//    public void playerMove(int dy, int dx) {
//        int y = player.getY() + dy;
//        int x = player.getX() + dx;
//        if (state == PlayerTurn && moveAnswer.canMovePlayer(y, x)) {
////            if(moveAnswer.noMonster(y,x)) {
//            Visualiser visualiser = Visualiser.getInstance();
//
//            player.move(dy, dx);
//
//            moveAnswer.playerMoved();
//
//            visualiser.drawFullGameScreen();
//
//            if (state != Death) {
//                nextTurn();
//            } else {
//                visualiser.drawGrey();
//            }
////            }else{
////
////            }
//        }
//    }

    private void nextTurn() {
        state = MonsterTurn;

        //Запуск потока обработки ходов противников

//        synchronized (thread) {
//            thread.notify();
//        }
    }

    public void startGame(){
        thread.start();
    }

    public void realThreadHeart() {
        for (; ; ) {
            Visualiser visualiser = Visualiser.getInstance();
            switch (state) {
                case PlayerTurn:
                    try {
                        thread.wait();
                    } catch (Exception e) {

                    }

                    int dy;
                    int dx;

                    if (pressedKey.equals("W")) dy = -1;
                    else if (pressedKey.equals("S")) dy = 1;
                    else dy = 0;

                    if (pressedKey.equals("A")) dx = -1;
                    else if (pressedKey.equals("D")) dx = 1;
                    else dx = 0;

                    int y = player.getY() + dy;
                    int x = player.getX() + dx;

                    switch (moveAnswer.canMovePlayer(y, x)) {
                        case canMove:
                            player.move(dy, dx);
                            moveAnswer.playerMoved();
                            player.yourTurn++;
                            break;
                        case startBattle:
                            currentBattle = new Battle(player, Layer1.getInstance().getMonster(y, x));
                            state = Battle;

                    }
                    if(player.yourTurn == turn)
                        nextTurn();

                    visualiser.drawFullGameScreen();
                    break;

                case MonsterTurn:
                    ai.calculatePath(player.getY(), player.getX());

                    ArrayList<Monster> creatures = Layer1.getInstance().creatures;

                    LinkedList<Monster> queue = new LinkedList<>();
                    queue.addAll(creatures);

                    while (!queue.isEmpty()) {
                        Monster monster = queue.poll();

                        if ((monster.yourTurn + 1) == turn) {

                            switch (moveAnswer.moveMonster(monster, ai)) {
                                case canMove:
                                    monster.yourTurn++;
                                    break;
                                case startBattle:
                                    currentBattle = new Battle(monster, player);
                                    state = Battle;
                            }
                            if(state == Battle)
                                break;

                            visualiser.drawFullGameScreen();
                        }
                    }

                    if (Layer1.getInstance().allMonstersMoved(turn)) {
                        state = PlayerTurn;
                        turn++;
                    }
                    break;

                case Death:
                    visualiser.drawGrey();
                    break;
                case Battle:
                    moveAnswer.fight(currentBattle);
                    visualiser.drawFullGameScreen();
            }
        }
    }

//    public void threadHeart() {
//        while (true) {
//            if (state == MonsterTurn) {
//                ai.calculatePath(player.getY(), player.getX());
//
//                ArrayList<Monster> creatures = Layer1.getInstance().creatures;
//
//                LinkedList<Monster> queue = new LinkedList<>();
//                queue.addAll(creatures);
//
//                while (!queue.isEmpty()) {
//                    Monster creature = queue.poll();
//                    Pair<Integer, Integer> cur = ai.monsterMove(creature.getY(), creature.getX(), 1000);
//
//                    Visualiser visualiser = Visualiser.getInstance();
//
//                    try {
//                        thread.wait();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    System.out.println("Success");
//
//                    creature.move(cur.getKey(), cur.getValue());
//                    moveAnswer.monsterMoved(creature);
//
//                    visualiser.drawFullGameScreen();
//                }
//
//                state = PlayerTurn;
//            } else
//                try {
//                    thread.wait();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//        }
//    }

    public void ePressed() {
        if (state == PlayerTurn) {
            moveAnswer.playerTryToLoot();
        }
    }

    public void wPressed() {
        pressedKey = "W";
        thread.notifyAll();
    }

    public void aPressed() {
        pressedKey = "A";
        thread.notifyAll();
    }

    public void sPressed() {
        pressedKey = "S";
        thread.notifyAll();
    }

    public void dPressed() {
        pressedKey = "D";
        thread.notifyAll();
    }

    public void spacePressed() {
        nextTurn();
        thread.notifyAll();
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
                    turningStateMachine.realThreadHeart();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
