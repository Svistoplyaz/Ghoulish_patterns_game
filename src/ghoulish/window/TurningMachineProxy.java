package ghoulish.window;

import ghoulish.game.KeyReader;
import ghoulish.game.TurningStateMachine;

public class TurningMachineProxy implements KeyReader {
    TurningStateMachine turningStateMachine = new TurningStateMachine();
    @Override
    public void pressKey(char key) {
        synchronized (turningStateMachine.thread) {
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
