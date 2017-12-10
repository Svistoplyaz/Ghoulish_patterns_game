package ghoulish.window;

import ghoulish.game.KeyReader;
import ghoulish.game.Memento;
import ghoulish.game.State;
import ghoulish.game.TurningStateMachine;

import javax.swing.*;
import java.util.ArrayList;

public class TurningMachineProxy implements KeyReader {
    TurningStateMachine turningStateMachine = new TurningStateMachine();
    ArrayList<Memento> mementos = new ArrayList<>();

    @Override
    public void pressKey(char key) {
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
    }
}