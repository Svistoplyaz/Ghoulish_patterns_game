package ghoulish.util;

import ghoulish.creatures.CleverMonster;
import ghoulish.creatures.Monster;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class MonsterReader implements IMonsterGiver{
    Tokenizer tk = null;

    public MonsterReader() {
        try {
            tk = new Tokenizer(new FileReader("resources/Monsters.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Monster> readMonsters() {
        ArrayList<Monster> creatures = new ArrayList<>();
        try {
            int n = tk.nextInt();
            for (int i = 0; i < n; i++)
                creatures.add(new CleverMonster(tk.nextInt(), tk.nextInt(), 1, "resources/Creature/Monster/CleverMonster.png", 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return creatures;
    }
}
