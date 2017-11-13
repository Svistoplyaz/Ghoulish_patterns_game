package ghoulish.game;

import ghoulish.creatures.Creature;

public class Battle {
    public Creature attacker;
    public Creature defender;

    Battle(Creature att, Creature def){
        attacker = att;
        defender = def;
    }
}
