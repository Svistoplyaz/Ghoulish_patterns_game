package ghoulish.util;

import ghoulish.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class TextureContainer {
    static HashMap<String, BufferedImage> textures = new HashMap<>();

    public static BufferedImage getTexture(String s){
        if(!textures.containsKey(s)){
            try {
                String[] composistion = s.split(" ");
                if(composistion.length == 1)
                    textures.put(s,ImageIO.read(new File(s)));
                else{
                    BufferedImage txtr = new BufferedImage(Main.scale, Main.scale, BufferedImage.TYPE_INT_RGB);
                    Graphics g = txtr.getGraphics();

                    for(String cur : composistion){
                        g.drawImage(getTexture(cur),0,0, Main.scale, Main.scale, null);
                    }

                    textures.put(s, txtr);
                }
            }catch (Exception e){

            }
        }

        return textures.get(s);
    }
}
