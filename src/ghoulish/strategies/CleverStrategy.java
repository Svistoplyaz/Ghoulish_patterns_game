package ghoulish.strategies;

import ghoulish.creatures.Monster;
import ghoulish.game.MonsterNavigator;
import ghoulish.game.MoveAnswer;
import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Created by Alexandr on 11.12.2017.
 */
public class CleverStrategy implements IStrategy {
    Monster monster;

    public CleverStrategy(Monster mon){
        monster = mon;
    }

    @Override
    public Pair<Integer, Integer> move(MoveAnswer moveAnswer, MonsterNavigator monsterNavigator) {
        int py = monsterNavigator.py, px = monsterNavigator.px;
        int my = monster.getY(), mx = monster.getX();
        int[][] path = monsterNavigator.getPath();

        if (path[my][mx] == -1 || Math.sqrt((px - mx) * (px - mx) + (py - my) * (py - my)) > Monster.angerRange ||
                !moveAnswer.canMoveMonster(my + possibleMoves[path[my][mx]][0], mx + possibleMoves[path[my][mx]][1], false))
            return randomMove(moveAnswer);

        return new Pair<>(possibleMoves[path[my][mx]][0], possibleMoves[path[my][mx]][1]);
    }

    private Pair<Integer, Integer> randomMove(MoveAnswer moveAnswer) {
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
