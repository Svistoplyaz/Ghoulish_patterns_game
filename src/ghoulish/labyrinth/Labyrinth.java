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
}
