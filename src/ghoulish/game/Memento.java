package ghoulish.game;

import ghoulish.creatures.*;
import ghoulish.labyrinth.*;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Alexandr on 09.12.2017.
 */
public class Memento{
    private Layer1 layer1;
    private Player player;
    private int turn;
    private State state;
    private Battle battle;
    private Date date;

    Memento(Layer1 _l1, Player pl, int t, State st, Battle bat){
        layer1 = _l1;
        player = pl;
        turn = t;
        state = st;
        battle = bat;
        date = new Date();
    }

    public Layer1 getLayer1() {
        return layer1;
    }

    public Player getPlayer() {
        return player;
    }

    public int getTurn() {
        return turn;
    }

    public State getState() {
        return state;
    }

    public Battle getBattle() {
        return battle;
    }

    @Override
    public String toString(){
        return turn+" "+state.toString()+" " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
    }
}
