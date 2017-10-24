package ghoulish.labyrinth;

import javax.imageio.ImageIO;
import java.io.File;

public class GreatWall extends Part {

    public GreatWall(){
        try{
            texture = ImageIO.read(new File("resources/", "GreatWall.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean attemptMove(){
        return false;
    }
}
