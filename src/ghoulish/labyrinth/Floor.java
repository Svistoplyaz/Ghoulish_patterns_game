package ghoulish.labyrinth;

import javax.imageio.ImageIO;
import java.io.File;

public class Floor extends Part{
    String directory = "resources/Labyrinth/Floor/";

    public Floor(String s){
        try{
            texture = ImageIO.read(new File(directory + s));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean attemptMove(){
        return true;
    }
}
