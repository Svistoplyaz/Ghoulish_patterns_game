package ghoulish.game;

import ghoulish.creatures.*;
import ghoulish.graphics.DistanceCommand;
import ghoulish.graphics.Visualiser;
import ghoulish.labyrinth.Layer0;

import java.util.ArrayList;
import java.util.LinkedList;

import static ghoulish.game.State.*;

public class TurningStateMachine implements KeyReader, ISubscriber {
    public State state;
    Player player = Player.getInstance();
    Layer1 layer1 = Layer1.getInstance();
    MoveAnswer moveAnswer = new MoveAnswer();
    public final MyThread thread = new MyThread(this);
    public char pressedKey;
    public int turn;
    public Battle currentBattle;

    public TurningStateMachine() {
        state = None;
        turn = 1;
        player.addSub(this);
        layer1.addSub(this);
    }

    private void nextTurn() {
        turn++;
    }

    public void changeState(State newstate) {
        if (state == Death)
            return;
        state = newstate;
//        System.out.println(state + "");
    }

    public void startGame() {
        state = PlayerTurn;
        thread.start();
    }

    public void realThreadHeart() {
        for (; ; ) {
            Visualiser visualiser = Visualiser.getInstance();

            switch (state) {
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

                        if (dy != 0 || dx != 0) {
                            switch (moveAnswer.canMovePlayer(y, x)) {
                                case canMove:
                                    player.move(dy, dx);
                                    moveAnswer.playerMoved();
                                    break;
                                case startBattle:
                                    currentBattle = new Battle(player, layer1.getMonster(y, x));
                                    System.out.println(currentBattle.weakSpot + "");
                                    changeState(Battle);

                            }
                        }

                        skipTurn();

                        visualiser.drawFullGameScreen();
                    } else {
                        System.out.println("Wut");
                    }
                    break;

                case MonsterTurn:

                    Iterator monsters = new MonsterIterator(turn, layer1.creatures);

                    while (monsters.hasNext()) {
                        Monster monster = (Monster) monsters.next();

//                        try {
//                            Thread.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }

                        if (monster instanceof StaticMonster && turn % 5 == 0)
                            moveAnswer.placeToBorn((StaticMonster) monster);
                        switch (moveAnswer.moveMonster(monster)) {
                            case canMove:
                                monster.yourTurn++;
                                break;
                            case startBattle:
                                currentBattle = new Battle(monster, player);
                                System.out.println(currentBattle.weakSpot + "");
                                changeState(Battle);
                        }
                        if (state == Battle)
                            break;

                        visualiser.drawFullGameScreen();
                    }

                    if (layer1.allMonstersMoved(turn)) {
                        changeState(PlayerTurn);
                        nextTurn();
                    }
                    break;

                case Death:
                    visualiser.drawGrey();
                    break;
                case Battle:
                    int num = Character.getNumericValue(pressedKey);
                    if (moveAnswer.fight(currentBattle, num)) {
                        changeState(MonsterTurn);
                    }
                    visualiser.drawFullGameScreen();
            }

            checkDeath();
        }
    }

    public void checkDeath() {
        if (player.getHp() < 1)
            changeState(Death);
    }

    @Override
    public void pressKey(char key) {
        pressedKey = key;
        thread.notifyAll();
    }

    public void skipTurn() {
        player.yourTurn++;
        if (state != Battle)
            changeState(MonsterTurn);
        thread.notifyAll();
    }

    public void lootTile() {
        pressedKey = 'e';
        moveAnswer.playerTryToLoot();
        thread.notifyAll();
    }

    public Memento save() {
        System.out.println(turn + " ");
        Layer1 l1 = layer1;
        try {
            l1 = (Layer1) layer1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Player pl = Player.getInstance();
        try {
            pl = (Player) Player.getInstance().clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new Memento(l1, pl, turn, state, currentBattle);
    }

    public void load(Memento memento) {
        try {
            Layer1.setInstance((Layer1) memento.getLayer1().clone());
            Player.setInstance((Player) memento.getPlayer().clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        turn = memento.getTurn();
        state = memento.getState();
        currentBattle = memento.getBattle();
        Visualiser.getInstance().drawFullGameScreen();
    }

    public void countDistance(){
        for(Monster monster : layer1.creatures)
            if(monster instanceof CleverMonster)
                Visualiser.getInstance().addCommand(new DistanceCommand(monster));
    }

    public void showStatistics(){
        IVisitor visitor = new StatisticsVisitor();
        for(Monster monster : layer1.creatures)
                monster.accept(visitor);
    }

    public void showHunting(){
        IVisitor visitor = new HunterVisitor();
        for(Monster monster : layer1.creatures)
                monster.accept(visitor);
    }

    @Override
    public void update() {
        player = Player.getInstance();
        layer1 = Layer1.getInstance();
    }

    public static class MyThread extends Thread {
        TurningStateMachine turningStateMachine;

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
