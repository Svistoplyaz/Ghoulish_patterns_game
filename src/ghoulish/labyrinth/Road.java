package ghoulish.labyrinth;

import javax.imageio.ImageIO;
import java.io.File;

public class Road extends Part{

    public Road(){
        try{
            texture = ImageIO.read(new File("resources/", "Road.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean attemptMove(){
        return true;
    }
}
