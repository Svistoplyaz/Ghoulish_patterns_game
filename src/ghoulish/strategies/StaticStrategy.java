package ghoulish.strategies;

import ghoulish.creatures.Monster;
import ghoulish.game.MonsterNavigator;
import ghoulish.game.MoveAnswer;
import javafx.util.Pair;

/**
 * Created by Alexandr on 11.12.2017.
 */
public class StaticStrategy implements IStrategy {
    Monster monster;

    public StaticStrategy(Monster mon){
        monster = mon;
    }

    @Override
    public Pair<Integer, Integer> move(MoveAnswer moveAnswer, MonsterNavigator monsterNavigator) {
        return new Pair<>(0, 0);
    }
}
