package ghoulish.game;

import ghoulish.creatures.Creature;
import ghoulish.creatures.Monster;

import java.util.Random;

public class Battle {
    public Creature attacker;
    public Creature defender;
    int weakSpot;

    Battle(Creature att, Creature def){
        attacker = att;
        defender = def;

        Random r = new Random();
        weakSpot = r.nextInt(6)+1;
    }

}
