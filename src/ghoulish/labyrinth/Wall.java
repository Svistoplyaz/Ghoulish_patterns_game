package ghoulish.labyrinth;

import javax.imageio.ImageIO;
import java.io.File;

public class Wall extends Part{
    private String directory = "resources/Labyrinth/Wall/";

    public Wall(String s){
        try{
            texture = ImageIO.read(new File(directory, s));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean attemptMove(){
        return false;
    }
}
