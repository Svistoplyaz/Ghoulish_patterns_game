package ghoulish.labyrinth;

import ghoulish.util.BlockChooser;
import ghoulish.util.LabReader;

import java.io.FileReader;

public class Labyrinth {
    private Part[][] parts;
    private int n, m;

    public Labyrinth(){
        BlockChooser blockChooser = new BlockChooser();

        parts = blockChooser.constructLab();
        n = blockChooser.getN();
        m = blockChooser.getM();
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
//        if(y < 0 || x < 0 || y >= n || x >= m)
//            return null;

        if(parts[y][x].attemptMove())
            return parts[y][x];

        return null;
    }

    public Part lootTile(int y, int x){
//        if(y < 0 || x < 0 || y >= n || x >= m)
//            return null;

        if(parts[y][x].lootCheck())
            return parts[y][x];

        return null;
    }
}
