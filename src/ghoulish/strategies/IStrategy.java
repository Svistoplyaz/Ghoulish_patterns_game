package ghoulish.strategies;

import ghoulish.game.MonsterNavigator;
import ghoulish.game.MoveAnswer;
import javafx.util.Pair;

import java.util.Random;

/**
 * Created by Alexandr on 11.12.2017.
 */
public interface IStrategy {
    int[][] possibleMoves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    Random random = new Random();

    Pair<Integer, Integer> move(MoveAnswer moveAnswer, MonsterNavigator monsterNavigator);
}
