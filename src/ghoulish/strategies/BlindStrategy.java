package ghoulish.strategies;

import ghoulish.creatures.Monster;
import ghoulish.game.MonsterNavigator;
import ghoulish.game.MoveAnswer;
import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Created by Alexandr on 11.12.2017.
 */
public class BlindStrategy implements IStrategy {
    Monster monster;

    public BlindStrategy(Monster mon){
        monster = mon;
    }

    @Override
    public Pair<Integer, Integer> move(MoveAnswer moveAnswer, MonsterNavigator monsterNavigator) {
        int my = monster.getY();
        int mx = monster.getX();
        ArrayList<Integer> possibilities = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (moveAnswer.canMoveMonster(my + possibleMoves[i][0], mx + possibleMoves[i][1], monster.smart())) {
                possibilities.add(i);
            }
        }

        int len = possibilities.size();
        if (len == 0)
            return new Pair<>(0, 0);

        int index = possibilities.get(random.nextInt(len));

        return new Pair<>(possibleMoves[index][0], possibleMoves[index][1]);
    }
}
