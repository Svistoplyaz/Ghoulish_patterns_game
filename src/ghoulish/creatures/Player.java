package ghoulish.creatures;

import ghoulish.game.ISubject;
import ghoulish.game.ISubscriber;
import ghoulish.util.Tokenizer;

import java.io.FileReader;
import java.util.ArrayList;

public class Player extends Creature implements Cloneable, ISubject{
    public static Player instance;
    ArrayList<ISubscriber> subs = new ArrayList<>();

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

    public static synchronized void setInstance(Player player){
        instance = player;
        instance.notifySubs();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public void addSub(ISubscriber sub) {
        getInstance().subs.add(sub);
    }

    @Override
    public void deleteSub(ISubscriber sub) {
        getInstance().subs.remove(sub);
    }

    @Override
    public void notifySubs() {
        for(ISubscriber subscriber : subs)
            subscriber.update();
    }
}
