package ghoulish.game;

import ghoulish.Main;
import ghoulish.creatures.AI;
import ghoulish.creatures.Creature;
import ghoulish.creatures.Player;
import ghoulish.labyrinth.Labyrinth;
import ghoulish.labyrinth.Part;
import ghoulish.util.LabReader;
import ghoulish.util.PlayerReader;
import ghoulish.window.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private GamePanel gp = new GamePanel(this);
    private Labyrinth lab;
    private int height, width;
    private BufferedImage background;
    public Player player = null;
    TurningMachine turningMachine;

    public Game() {
        lab = new Labyrinth();
        height = (lab.getN()) * Main.scale;
        width = (lab.getM()) * Main.scale;

        background = globalRedraw();

        turningMachine = new TurningMachine(gp, new AI(lab));

        PlayerReader pr = null;
        try {
            pr = new PlayerReader(new FileReader("resources/Player.in"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        player = pr.readPlayer();
    }

    private BufferedImage globalRedraw() {
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

    private void localRedraw(Part part) {
        Graphics g = background.getGraphics();

        g.drawImage(part.getTexture(), part.getX() * Main.scale, part.getY() * Main.scale, Main.scale, Main.scale, null);

    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public BufferedImage getBackground() {
        return background;
//        return globalRedraw();
    }

    public void movePlayer(int dy, int dx) {
        if (turningMachine.inProgress)
            return;

        int destX = player.getIX() + dx, destY = player.getIY() + dy;

        Part pr = lab.checkForMove(destY, destX);

        if (pr == null)
            return;

        if (pr.trapCheck()) {
            player.inflictDamage(1);
            lab.setPart(destY, destX, pr.collapseDanger());
            localRedraw(lab.getPart(destY, destX));
        }

        turningMachine.queueMove(player, dx, dy);

        turningMachine.nextTurn();

        gp.repaint();
    }

    public void loot() {
        int destY = player.getIY(), destX = player.getIX();

        if (turningMachine.inProgress)
            return;

        Part pr = lab.lootTile(destY, destX);

        if (pr == null)
            return;

        if (pr.lootCheck()) {
            player.inflictDamage(-1);
            lab.setPart(destY, destX, pr.collapseLoot());
            localRedraw(lab.getPart(destY, destX));
        }

        gp.repaint();
    }

    public void resurrect() {

    }

    public void skipTurn() {
        turningMachine.nextTurn();
    }

    public GamePanel getGp() {
        return gp;
    }

}
