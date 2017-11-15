package ghoulish.labyrinth;

import javafx.util.Pair;

public class Door extends PartDecorator {
    private boolean opened = false;
    public Door(int i, int j, Part _p, String s){
        super(i,j,_p,s);
    }

    public void openDoor(){
        textureName = "resources/Labyrinth/Floor/DoorOpened.png";
        opened = true;
    }

    public boolean attemptMove(){
        return opened;
    }

    public boolean hasDoor() {
        return true;
    }
}
