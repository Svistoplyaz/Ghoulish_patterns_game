package ghoulish.game;

import ghoulish.creatures.Creature;
import ghoulish.creatures.Monster;

import java.util.ArrayList;

public class MonsterIterator implements Iterator{
    int turn;
    int cur = 0;
    ArrayList<Monster> willUse = new ArrayList<>();

    public MonsterIterator(int _turn, ArrayList<Monster> arr){
        turn = _turn;
        for(Monster monster: arr){
            if(monster.yourTurn == turn)
            willUse.add(monster);
        }
    }

    @Override
    public Monster next() {
        if(hasNext()){
            Monster ans = willUse.get(cur);
            cur++;
            return ans;
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return cur < willUse.size();
    }
}
