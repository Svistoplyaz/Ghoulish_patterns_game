package ghoulish.graphics;

import ghoulish.Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Composite extends TextureHolder {
    int width;
    int height;
    PriorityQueue<TextureHolder> queue = new PriorityQueue<>(new Comparator<TextureHolder>() {
        @Override
        public int compare(TextureHolder o1, TextureHolder o2) {
            if (o1.priority < o2.priority)
                return -1;
            else
                return 1;
        }
    });

    Composite(int wi, int he) {
        width = wi;
        height = he;
        x = 0;
        y = 0;
        textureName = "";
    }

    @Override
    public BufferedImage getTexture() {
        BufferedImage ans = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = ans.getGraphics();

        for (TextureHolder textureHolder : queue) {
            draw(g, textureHolder);
        }

        return ans;
    }

    public void addElement(TextureHolder textureHolder) {
        queue.add(textureHolder);
    }

    public void addElement(TextureHolder[] textureHolders) {
        queue.addAll(Arrays.asList(textureHolders));

    }

    public void addElement(TextureHolder[][] textureHolders) {
        for(TextureHolder[] textureHolder: textureHolders)
            addElement(textureHolder);
    }


    public void draw(Graphics g, TextureHolder textureHolder) {
        g.drawImage(textureHolder.getTexture(), textureHolder.getX() * Main.scale, textureHolder.getY() * Main.scale, Main.scale, Main.scale, null);
    }
}
