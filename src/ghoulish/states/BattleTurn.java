package ghoulish.states;

import ghoulish.game.TurningStateMachine;

import static ghoulish.game.State.MonsterTurn;

/**
 * Created by Alexander on 14.12.2017.
 */
public class BattleTurn implements IStateForMachine{
    @Override
    public void execute(TurningStateMachine tS) {
        int num = Character.getNumericValue(tS.pressedKey);
        if (tS.mediator.fight(tS.currentBattle, num)) {
            tS.changeState(MonsterTurn);
        }
        tS.mediator.repaint();
    }
}
