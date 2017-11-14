package ghoulish.window;

import ghoulish.Main;
import ghoulish.creatures.Creature;
import ghoulish.creatures.Layer1;
import ghoulish.creatures.Monster;
import ghoulish.creatures.Player;
import ghoulish.labyrinth.Labyrinth;
import ghoulish.labyrinth.Part;
import ghoulish.util.TextureContainer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Visualiser {
    private static Visualiser instance;
    public GamePanel gamePanel = GamePanel.getInstance();
    private Labyrinth lab = Labyrinth.getInstance();
    private ArrayList<Monster> mobs = Layer1.getInstance().creatures;
    private BufferedImage background;
    private Player player = Player.getInstance();
    private int height = (lab.getN()) * Main.scale;
    private int width = (lab.getM()) * Main.scale;
    BufferedImage gamescreen;


    public Visualiser() {
        background = globalBackgroundRedraw();

        drawFullGameScreen();
    }

    public static Visualiser getInstance() {
        if (instance == null)
            instance = new Visualiser();
        return instance;
    }

    private BufferedImage globalBackgroundRedraw() {

        BufferedImage ans = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Part[][] parts = lab.getParts();

        Graphics g = ans.getGraphics();
        int n = lab.getN(), m = lab.getM();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                g.drawImage(parts[i][j].getTexture(), j * Main.scale, i * Main.scale, Main.scale, Main.scale, null);
            }
        }

        return ans;
    }

    public void localBackgroundRedraw(int y, int x) {
        Graphics g = background.getGraphics();

        Part part = lab.getPart(y, x);
        g.drawImage(part.getTexture(), part.getPictureX(), part.getPictureY(), Main.scale, Main.scale, null);

    }

    public void drawFullGameScreen() {
        gamescreen = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = gamescreen.getGraphics();

        g.drawImage(background, 0, 0, null);

        int quant = player.getHp();

        int i;
        for (i = 0; i < quant / 2; i++) {
            g.drawImage(TextureContainer.getTexture("resources/Creature/Player/FullHeart.png"), i * Main.scale / 2, 0, Main.scale / 2, Main.scale / 2, null);
        }
        if (quant % 2 == 1) {
            g.drawImage(TextureContainer.getTexture("resources/Creature/Player/HalfHeart.png"), i * Main.scale / 2, 0, Main.scale / 2, Main.scale / 2, null);
        }

        for (Creature creature : mobs) {
            drawCreature(g, creature);
//            g.drawImage(creature.getTexture(), creature.getPictureX(), creature.getPictureY(), Main.scale, Main.scale, null);
        }

        drawCreature(g, player);
//        g.drawImage(player.getTexture(), player.getPictureX(), player.getPictureY(), Main.scale, Main.scale, null);

        repaint();
    }

    public void drawGrey() {
        Color curcolor;
        int cur;
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                curcolor = new Color(gamescreen.getRGB(j, i));
                cur = (int) (curcolor.getRed() * 0.299 + curcolor.getGreen() * 0.587 + curcolor.getBlue() * 0.114);
                gamescreen.setRGB(j, i, new Color(cur, cur, cur).getRGB());
            }

        repaint();
    }

    public void drawCreature(Graphics g, Creature creature) {
        drawThing(g, creature.getTexture(), creature.getX(), creature.getY());
    }

    public void drawThing(Graphics g, BufferedImage image, int x, int y) {
        g.drawImage(image, x * Main.scale, y * Main.scale, Main.scale, Main.scale, null);
    }

    public void repaint() {
        gamePanel.setImage(gamescreen);
        gamePanel.repaint();
    }
}
