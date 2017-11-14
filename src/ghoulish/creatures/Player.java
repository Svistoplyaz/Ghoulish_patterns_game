package ghoulish.creatures;

import ghoulish.util.Tokenizer;

import java.io.FileReader;

public class Player extends Creature {
    public static Player instance;

    private Player(int _y, int _x, int _hp, String texture,int tu){
        super(_y, _x, _hp, texture, tu);
    }

    public static Player getInstance() {
        if(instance == null) {
            try {
                Tokenizer tk = new Tokenizer(new FileReader(("resources/Player.in")));
                int y = 0, x = 0;

                    y = tk.nextInt();
                    x = tk.nextInt();

                instance = new Player(y,x,9,"resources/Creature/Player/Player.png", 1);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return instance;
    }
}
