package ghoulish.states;

import ghoulish.game.TurningStateMachine;

/**
 * Created by Alexander on 14.12.2017.
 */
public class DeathTurn implements IStateForMachine{
    @Override
    public void execute(TurningStateMachine tS) {
        tS.mediator.drawGrey();
    }
}
