package ghoulish.game;

import ghoulish.creatures.Player;

/**
 * Created by Alexander on 30.10.2017.
 */
public class MyThread extends Thread {
    int f = 1,s = 1;
    Player pl;
    int dx,dy;
    public MyThread(Player _pl, int _dx, int _dy){
        pl = _pl;
        dx = _dx;
        dy = _dy;
    }

    public void run(){
        pl.move(dy,dx);

        dx *= f;
        dy *= f;
        f *= -1;

        
    }

//    public
}
