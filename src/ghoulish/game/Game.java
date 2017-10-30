package ghoulish.game;

import ghoulish.Main;
import ghoulish.creatures.Creature;
import ghoulish.creatures.Player;
import ghoulish.labyrinth.Labyrinth;
import ghoulish.labyrinth.Part;
import ghoulish.util.LabReader;
import ghoulish.util.PlayerReader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileReader;

public class Game {
    private Labyrinth lab;
    private int height, width;
    private BufferedImage background;
    private KeyListener keyListener;
    public Player player = null;
    TurningMachine turningMachine;

    public Game(){
        lab = new Labyrinth();
        height = (lab.getN())* Main.scale;
        width = (lab.getM())*Main.scale;

        background = globalRedraw();

        turningMachine = new TurningMachine();

        PlayerReader pr = null;
        try {
            pr = new PlayerReader(new FileReader("resources/Player.in"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        player = pr.readPlayer();
    }

    private BufferedImage globalRedraw(){
        BufferedImage ans = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Part[][] parts = lab.getParts();

        Graphics g = ans.getGraphics();
        int n = lab.getN(), m = lab.getM();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                g.drawImage(parts[i][j].getTexture(), j*Main.scale, i*Main.scale, Main.scale,Main.scale, null);
            }
        }

        return ans;
    }

    private void localRedraw(Part part){
        Graphics g = background.getGraphics();

        g.drawImage(part.getTexture(), part.getX()*Main.scale, part.getY()*Main.scale, Main.scale, Main.scale, null);

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

    public void movePlayer(int dy, int dx){
        if(turningMachine.inProgress)
            return;

        Part pr = lab.checkForMove(player.getY()+dy, player.getX()+dx);

        if(pr == null)
            return;

        if(pr.trapCheck()){
            player.inflictDamage(1);
            lab.setPart(player.getY()+dy, player.getX()+dx, pr.collapseDanger());
            localRedraw(lab.getPart(player.getY()+dy, player.getX()+dx));
        }

        player.move(dy,dx);

        turningMachine.nextTurn();
    }

    public void loot(){
        if(turningMachine.inProgress)
            return;

        Part pr = lab.lootTile(player.getY(), player.getX());

        if(pr == null)
            return;

        if(pr.lootCheck()){
            player.inflictDamage(-1);
            lab.setPart(player.getY(), player.getX(), pr.collapseLoot());
            localRedraw(lab.getPart(player.getY(), player.getX()));
        }
    }

    public void resurrect(){

    }

    public void skipTurn(){
        turningMachine.nextTurn();
    }
}
