package ghoulish;

import ghoulish.creatures.Monster;
import ghoulish.creatures.StaticMonster;
import ghoulish.game.Battle;
import ghoulish.game.MoveAnswer;
import ghoulish.game.State;
import ghoulish.game.TurningStateMachine;
import ghoulish.graphics.ICommand;
import ghoulish.graphics.Visualiser;

/**
 * Created by Alexandr on 11.12.2017.
 */
public class Mediator {
    private MoveAnswer moveAnswer;
    private Visualiser visualiser;
    private TurningStateMachine turningStateMachine;

    public Mediator(TurningStateMachine ts){
        turningStateMachine = ts;
        moveAnswer = new MoveAnswer(this);
        visualiser = Visualiser.getInstance();
        visualiser.mediator = this;
    }

    public MoveAnswer.Answer canMovePlayer(int y, int x){
        return moveAnswer.canMovePlayer(y,x);
    }

    public void playerMoved(){
        moveAnswer.playerMoved();
    }

    public void placeToBorn(StaticMonster monster){
        moveAnswer.placeToBorn(monster);
    }

    public MoveAnswer.Answer moveMonster(Monster monster){
        return moveAnswer.moveMonster(monster);
    }

    public boolean fight(Battle battle, int num){
        return moveAnswer.fight(battle, num);
    }

    public void playerTryToLoot(){
        moveAnswer.playerTryToLoot();
    }

    public State getState(){
        return turningStateMachine.getState();
    }

    public void repaint(){
        visualiser.drawFullGameScreen();
    }

    public void drawGrey(){visualiser.drawGrey();}

    public void addCommand(ICommand command){
        visualiser.addCommand(command);
    }

    public void setMoveAnswer(MoveAnswer moveAnswer) {
        this.moveAnswer = moveAnswer;
    }

    public void setVisualiser(Visualiser visualiser) {
        this.visualiser = visualiser;
    }

    public void setTurningStateMachine(TurningStateMachine turningStateMachine) {
        this.turningStateMachine = turningStateMachine;
    }
}
