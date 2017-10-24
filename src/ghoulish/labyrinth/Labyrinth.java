package ghoulish.labyrinth;

import ghoulish.util.LabReader;

import java.io.FileReader;

public class Labyrinth {
    Part[][] parts;
    int n, m;

    public Labyrinth(String file){
        LabReader lr = null;
        try {
            lr = new LabReader(new FileReader(file));
        }catch (Exception e){
            e.printStackTrace();
        }

        parts = lr.constructLab();
        n = lr.getN();
        m = lr.getM();
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
