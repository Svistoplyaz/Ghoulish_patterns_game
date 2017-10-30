package ghoulish.game;

import ghoulish.creatures.Player;

public class TurningMachine {
    private int currentTurn;
    boolean inProgress;

    TurningMachine(){
        currentTurn = 1;
        inProgress = false;
    }


    public void nextTurn(){
        inProgress = true;



        currentTurn++;
        inProgress = false;
    }

}
