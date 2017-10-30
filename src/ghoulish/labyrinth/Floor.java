package ghoulish.labyrinth;

import javax.imageio.ImageIO;
import java.io.File;

public class Floor extends Part{

    public Floor(int i, int j, String s){
        super(i,j,s);
    }

    public boolean attemptMove(){
        return true;
    }
}
