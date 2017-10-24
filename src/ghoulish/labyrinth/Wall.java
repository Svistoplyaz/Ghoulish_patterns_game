package ghoulish.labyrinth;

import javax.imageio.ImageIO;
import java.io.File;

public class Wall extends Part{

    public Wall(){
        try{
            texture = ImageIO.read(new File("resources/", "Wall.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean attemptMove(){
        return false;
    }
}
