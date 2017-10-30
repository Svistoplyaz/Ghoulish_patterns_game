package ghoulish.labyrinth;

import javax.imageio.ImageIO;
import java.io.File;

public class Wall extends Part{

    public Wall(int i, int j, String s){
        super(i,j,s);
    }

    public boolean attemptMove(){
        return false;
    }
}
