package ghoulish.labyrinth;

import ghoulish.creatures.Creature;
import ghoulish.util.BlockChooser;

public class Labyrinth {
    private static Labyrinth instance = null;
    private Part[][] parts;
    private int n, m;

    private Labyrinth(){
        BlockChooser blockChooser = new BlockChooser();

        parts = blockChooser.constructLab();
        n = blockChooser.getN();
        m = blockChooser.getM();
    }

    public static synchronized Labyrinth getInstance() {
        if (instance == null)
            instance = new Labyrinth();
        return instance;
    }

    public int getN(){
        return n;
    }

    public int getM() {
        return m;
    }

    public Part[][] getParts() {
        return parts;
    }

    public Part getPart(int i, int j) {
        return parts[i][j];
    }

    public void setPart(int i, int j, Part part) {
        parts[i][j] = part;
    }

    public Part checkForMove(int y, int x){
        if(parts[y][x].attemptMove())
            return parts[y][x];

        return null;
    }

    public boolean canMoveHere(Creature creature, int dy, int dx){
        return parts[creature.getY()+dy][creature.getX()+dx].attemptMove();
    }

    public boolean canMoveHere(int y, int x){
        return parts[y][x].attemptMove();
    }

    public Part lootTile(int y, int x){
//        if(y < 0 || x < 0 || y >= n || x >= m)
//            return null;

        if(parts[y][x].lootCheck())
            return parts[y][x];

        return null;
    }

    public void collapseDanger(int y, int x){
        parts[y][x] = parts[y][x].collapseDanger();
    }

    public void collapseLoot(int y, int x){
        parts[y][x] = parts[y][x].collapseLoot();
    }
}
