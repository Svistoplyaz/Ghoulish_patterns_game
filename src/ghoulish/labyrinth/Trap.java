package ghoulish.labyrinth;

import javax.imageio.ImageIO;
import java.io.File;

public class Trap extends Part {
    String directory = "resources/Labyrinth/Floor/";
    Part parent;

    public Trap(Part _p, String s){
        parent = _p;
        try{
            texture = ImageIO.read(new File(s));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean attemptMove(){
        return true;
    }
}
