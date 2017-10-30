package ghoulish.util;

import ghoulish.creatures.Player;

import java.io.FileReader;

public class PlayerReader {
    private FileReader in;

    public PlayerReader(FileReader _in){
        in = _in;
    }

    public Player readPlayer(){
        Tokenizer tk = new Tokenizer(in);
        int y = 0, x = 0;

        try {
            y = tk.nextInt();
            x = tk.nextInt();
        }catch (Exception e){
            e.printStackTrace();
        }

        return new Player(y, x, 10, "resources/Creature/Player/Player.png");
    }
}
