package ghoulish.creatures;

import ghoulish.labyrinth.Labyrinth;
import ghoulish.util.Tokenizer;

import java.io.FileReader;
import java.util.ArrayList;

public class Layer1 {
    private static Layer1 instance = null;
    public ArrayList<Creature> creatures = new ArrayList<>();

    private Layer1(){
        try {
            Tokenizer tk = new Tokenizer(new FileReader("resources/Monsters.in"));
            int n = tk.nextInt();

            for(int i = 0; i < n; i++)
                creatures.add(new Monster(tk.nextInt(), tk.nextInt(), 1, "resources/Creature/Monster/Monster.png"));
        }catch (Exception e){

        }
    }

    public static synchronized Layer1 getInstance() {
        if (instance == null)
            instance = new Layer1();
        return instance;
    }
}
