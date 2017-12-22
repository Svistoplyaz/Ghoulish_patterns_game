package ghoulish.states;

import ghoulish.creatures.Monster;
import ghoulish.creatures.StaticMonster;
import ghoulish.game.*;

import static ghoulish.game.State.Battle;
import static ghoulish.game.State.PlayerTurn;

/**
 * Created by Alexander on 14.12.2017.
 */
public class MonsterTurn implements IStateForMachine {
    @Override
    public void execute(TurningStateMachine tS) {
        Iterator monsters = new MonsterIterator(tS.turn, tS.layer1.creatures);

        while (monsters.hasNext()) {
            Monster monster = (Monster) monsters.next();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (monster instanceof StaticMonster && tS.turn % 5 == 0)
                tS.mediator.placeToBorn((StaticMonster) monster);
            switch (tS.mediator.moveMonster(monster)) {
                case canMove:
                    monster.yourTurn++;
                    break;
                case startBattle:
                    tS.currentBattle = new Battle(monster, tS.player);
                    System.out.println(tS.currentBattle.weakSpot + "");
                    tS.changeState(State.Battle);
            }
            if (tS.state == Battle)
                break;

            tS.mediator.repaint();
        }

        if (tS.layer1.allMonstersMoved(tS.turn)) {
            tS.changeState(PlayerTurn);
            tS.nextTurn();
        }
    }
}
