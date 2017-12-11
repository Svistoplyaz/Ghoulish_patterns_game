package ghoulish.window;

import ghoulish.game.Memento;
import ghoulish.game.State;
import ghoulish.game.TurningStateMachine;

import javax.swing.*;
import java.util.ArrayList;

public class TurningMachineProxy implements IHandler {
    private TurningStateMachine turningStateMachine = new TurningStateMachine();
    private ArrayList<Memento> mementos = new ArrayList<>();
    private IHandler next;

    @Override
    public void setNext(IHandler handler) {
        next = handler;
    }

    @Override
    public void handle(char key) {
        System.out.println("=========================================");
        synchronized (turningStateMachine.thread) {
            switch (key) {
                case '[':
                    if(turningStateMachine.state != State.None)
                        mementos.add(turningStateMachine.save());
                    break;
                case ']':
                    if(mementos.size() > 0) {
                        Memento choice = (Memento) JOptionPane.showInputDialog(null, "Choose wisely", "Choose your savepoint", JOptionPane.QUESTION_MESSAGE, null, mementos.toArray(), mementos.toArray()[0]);
                        if(choice != null) {
                            System.out.println(choice.toString() + "");
                            turningStateMachine.load(choice);
                        }
                    }
                    break;
                case '0':
                    turningStateMachine.countDistance();
                    break;
                case '9':
                    turningStateMachine.showStatistics();
                    break;
                case '8':
                    turningStateMachine.showHunting();
                    break;
                default:
                    switch (turningStateMachine.state) {
                        case None:
                            turningStateMachine.startGame();
                            break;
                        case PlayerTurn:
                            if (key == 'w' || key == 'a' || key == 's' || key == 'd')
                                turningStateMachine.pressKey(key);
                            else if (key == ' ')
                                turningStateMachine.skipTurn();
                            else if (key == 'e')
                                turningStateMachine.lootTile();
                            break;
                        case Battle:
                            if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6') {
                                turningStateMachine.pressKey(key);
                            }
                    }
            }
        }

        if(next != null)
            next.handle(key);
    }
}