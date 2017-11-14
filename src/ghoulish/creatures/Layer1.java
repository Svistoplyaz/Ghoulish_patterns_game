package ghoulish.creatures;

import ghoulish.util.Tokenizer;

import java.io.FileReader;
import java.util.ArrayList;

public class Layer1 {
    private static Layer1 instance = null;
    public ArrayList<Monster> creatures = new ArrayList<>();

    private Layer1() {
        try {
            Tokenizer tk = new Tokenizer(new FileReader("resources/Monsters.in"));
            int n = tk.nextInt();

            for (int i = 0; i < n; i++)
                creatures.add(new Monster(tk.nextInt(), tk.nextInt(), 1, "resources/Creature/Monster/Monster.png", 1));
        } catch (Exception e) {

        }
    }

    public static synchronized Layer1 getInstance() {
        if (instance == null)
            instance = new Layer1();
        return instance;
    }

    public boolean hasMonster(int y, int x) {
        for (Monster creature : creatures)
            if (creature.y == y && creature.x == x)
                return true;

        return false;
    }

    public Monster getMonster(int y, int x) {
        for (Monster creature : creatures)
            if (creature.y == y && creature.x == x)
                return creature;

        return null;
    }

    public void monsterDied(Monster monster) {
        creatures.remove(monster);
    }

    public boolean allMonstersMoved(int turn){
        for(Monster mon : creatures){
            if(mon.yourTurn != (turn+1))
                return false;
        }

        return true;
    }
}
