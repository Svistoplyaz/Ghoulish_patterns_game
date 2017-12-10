package ghoulish.graphics;

import ghoulish.Main;
import ghoulish.creatures.Layer1;
import ghoulish.creatures.Monster;
import ghoulish.creatures.Player;
import ghoulish.game.ISubscriber;
import ghoulish.labyrinth.Layer0;
import ghoulish.labyrinth.Part;
import ghoulish.util.TextureContainer;
import ghoulish.window.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

public class Visualiser implements ISubscriber {
    private static Visualiser instance;
    public GamePanel gamePanel = GamePanel.getInstance();
    private Layer0 lab = Layer0.getInstance();
    private Player player = Player.getInstance();
    private ArrayList<Monster> mobs = Layer1.getInstance().creatures;
    private BufferedImage background;
    private int height = (lab.getN()) * Main.scale;
    private int width = (lab.getM()) * Main.scale;
    private BufferedImage gamescreen;
    private LinkedList<ICommand> queue = new LinkedList<>();

    private Visualiser() {
        background = globalBackgroundRedraw();

        drawFullGameScreen();
        Layer1.getInstance().addSub(this);
        Player.getInstance().addSub(this);
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
        draw(g, part);
//        g.drawImage(part.getTexture(), part.getPictureX(), part.getPictureY(), Main.scale, Main.scale, null);
    }

    public void drawFullGameScreen() {
        Composite composite = new Composite(width, height);

        composite.addElement(lab.getParts());
        for (Monster creature : mobs) {
            composite.addElement(creature);
        }
        composite.addElement(player);

        gamescreen = composite.getTexture();
        Graphics g = gamescreen.getGraphics();

        int quant = player.getHp();

        int i;
        for (i = 0; i < quant / 2; i++) {
            draw(g,TextureContainer.getTexture("resources/Creature/Player/FullHeart.png"), i * 1.0 / 2, 0.0, 0.5, 0.5);
        }
        if (quant % 2 == 1) {
            draw(g,TextureContainer.getTexture("resources/Creature/Player/HalfHeart.png"), i * 1.0 / 2, 0.0, 0.5, 0.5);
        }

        repaint();


        while(!queue.isEmpty()){
            queue.poll().execute();
        }
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

    public void addCommand(ICommand com){
        queue.add(com);
    }

    public void draw(Graphics g, TextureHolder textureHolder) {
        g.drawImage(textureHolder.getTexture(), textureHolder.getX() * Main.scale, textureHolder.getY() * Main.scale, Main.scale, Main.scale, null);
    }

    public void draw(Graphics g, BufferedImage texture, double x, double y, double scaleX, double scaleY) {
        g.drawImage(texture, (int) (x * Main.scale), (int) (y * Main.scale), (int)(scaleX*Main.scale), (int)(scaleY*Main.scale), null);
    }

    public void repaint() {
        gamePanel.setImage(gamescreen);
        gamePanel.repaint();
    }

    @Override
    public void update() {
        player = Player.getInstance();
        mobs = Layer1.getInstance().creatures;
    }
}
