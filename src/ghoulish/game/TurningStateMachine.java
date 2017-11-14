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

    private void skipTurn() {
        player.yourTurn++;
        if(state!= Battle)
            changeState(MonsterTurn);
    }

    private void nextTurn(){
        turn++;
    }

    public void changeState(State newstate){
        if(state == Death)
            return;
        state = newstate;
        System.out.println(state+"");
    }

    public void startGame(){
        thread.start();
    }

    public void realThreadHeart() {
        for (; ; ) {
            Visualiser visualiser = Visualiser.getInstance();

            switch (state){
                case PlayerTurn:
                case Battle:
                    try {
                        thread.wait();
                    } catch (Exception e) {

                    }
            }

            switch (state) {
                case PlayerTurn:
                    if (player.yourTurn == turn) {
                        int dy = 0;
                        int dx = 0;

                        switch (pressedKey) {
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
//                                player.yourTurn++;
                                break;
                            case startBattle:
                                currentBattle = new Battle(player, Layer1.getInstance().getMonster(y, x));
                                System.out.println(currentBattle.weakSpot + "");
                                changeState(Battle);

                        }

                        skipTurn();

                        visualiser.drawFullGameScreen();
                    }else{
                        System.out.println("Wut");
                    }
                    break;

                case MonsterTurn:
                    ai.calculatePath(player.getY(), player.getX());

                    ArrayList<Monster> creatures = Layer1.getInstance().creatures;

                    LinkedList<Monster> queue = new LinkedList<>();
                    queue.addAll(creatures);

                    while (!queue.isEmpty()) {
                        Monster monster = queue.poll();

                        if (monster.yourTurn== turn) {

                            switch (moveAnswer.moveMonster(monster, ai)) {
                                case canMove:
                                    monster.yourTurn++;
                                    break;
                                case startBattle:
                                    currentBattle = new Battle(monster, player);
                                    System.out.println(currentBattle.weakSpot+"");
                                    changeState(Battle);
                            }
                            if(state == Battle)
                                break;

                            visualiser.drawFullGameScreen();
                        }
                    }

                    if (Layer1.getInstance().allMonstersMoved(turn)) {
                        changeState(PlayerTurn);
                        nextTurn();
                    }
                    break;

                case Death:
                    visualiser.drawGrey();
                    break;
                case Battle:
                    int num = Character.getNumericValue(pressedKey);
                    if(moveAnswer.fight(currentBattle, num)) {
                        changeState(MonsterTurn);
                    }
                    visualiser.drawFullGameScreen();
            }
        }
    }

    public void pressKey(char key) {
        pressedKey = key;
        switch (pressedKey) {
            case ' ':
                skipTurn();
                thread.notifyAll();
                break;
            case 'e':
                moveAnswer.playerTryToLoot();
                thread.notifyAll();
                break;
            case 'r':
                thread.start();
                break;
            case 'w':
            case 'a':
            case 's':
            case 'd':
                if (state == PlayerTurn)
                    thread.notifyAll();
                break;

            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
                if (state == Battle)
                    thread.notifyAll();
                break;
//            thread.notifyAll();
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
