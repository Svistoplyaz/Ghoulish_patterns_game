package ghoulish.states;

import ghoulish.game.Battle;
import ghoulish.game.State;
import ghoulish.game.TurningStateMachine;

/**
 * Created by Alexander on 14.12.2017.
 */
public class PlayerTurn implements IStateForMachine {
    @Override
    public void execute(TurningStateMachine tS) {

        if (tS.player.yourTurn == tS.turn) {
            int dy = 0;
            int dx = 0;

            switch (tS.pressedKey) {
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

            int y = tS.player.getY() + dy;
            int x = tS.player.getX() + dx;

            if (dy != 0 || dx != 0) {
                switch (tS.mediator.canMovePlayer(y,x)) {
                    case canMove:
                        tS.player.move(dy, dx);
                        tS.mediator.playerMoved();
                        break;
                    case startBattle:
                        tS.currentBattle = new Battle(tS.player, tS.layer1.getMonster(y, x));
                        System.out.println(tS.currentBattle.weakSpot + "");
                        tS.changeState(State.Battle);

                }
            }

            tS.skipTurn();

            tS.mediator.repaint();
        } else {
            System.out.println("Wut");
        }
    }
}
