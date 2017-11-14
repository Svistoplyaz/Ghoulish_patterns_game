package ghoulish.game;

import ghoulish.creatures.*;
import ghoulish.window.Visualiser;

import java.util.ArrayList;
import java.util.LinkedList;

import static ghoulish.game.State.*;

public class TurningStateMachine {
    static TurningStateMachine instance;
    public State state;
    AI ai;
    Player player = Player.getInstance();
    MoveAnswer moveAnswer = MoveAnswer.getInstance();
    public final MyThread thread = new MyThread(this);
    public char pressedKey;
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
        changeState(MonsterTurn);

        //Запуск потока обработки ходов противников

//        synchronized (thread) {
//            thread.notify();
//        }
    }

    public void changeState(State newstate){
        if(state == Death)
            return;
        state = newstate;
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

                    int dy = 0;
                    int dx = 0;

                    switch(pressedKey){
                        case 'w':
                            dy = -1;
                            break;
                        case 's':
                            dy = 1;
                            break;
                        case 'a':
                            dx = -1;
                            break;
                        case 'd':
                            dx = 1;
                            break;
                    }

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
                            changeState(Battle);

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
                                    changeState(Battle);
                            }
                            if(state == Battle)
                                break;

                            visualiser.drawFullGameScreen();
                        }
                    }

                    if (Layer1.getInstance().allMonstersMoved(turn)) {
                        changeState(PlayerTurn);
                        turn++;
                    }
                    break;

                case Death:
                    visualiser.drawGrey();
                    try {
                        thread.wait();
                    } catch (Exception e) {

                    }
                    break;
                case Battle:
                    try {
                        thread.wait();
                    } catch (Exception e) {

                    }

                    int num = Character.getNumericValue(pressedKey);
                    if(moveAnswer.fight(currentBattle, num))
                        changeState(MonsterTurn);
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

    public void pressKey(char key){
        pressedKey = key;
        switch (pressedKey) {
            case ' ':
                nextTurn();
                break;
            case 'e':
                moveAnswer.playerTryToLoot();
                break;
            case 'r':
                thread.start();
                break;
        }
//        switch (pressedKey){
//            case 'w':
//            case 'a':
//            case 's':
//            case 'd':
//                if(state == PlayerTurn)
//                    thread.notifyAll();
//                break;
//
//            case '1':
//            case '2':
//            case '3':
//            case '4':
//            case '5':
//            case '6':
//                if(state == Battle)
//                    thread.notifyAll();
//                break;
//        }
        thread.notifyAll();
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
