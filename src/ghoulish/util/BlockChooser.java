package ghoulish.util;

import ghoulish.labyrinth.*;

import java.io.FileReader;
import java.util.Random;

public class BlockChooser {
    String[][] curlab;
    int n, m;
    Random r = new Random();

    public BlockChooser(){
        LabReader lr = null;
        try {
            lr = new LabReader(new FileReader("resources/Labyrinth.in"));
        }catch (Exception e){
            e.printStackTrace();
        }

        curlab = lr.fillLab();
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public Part[][] constructLab(){
        n = curlab.length;
        m = curlab[0].length;

        Part[][] ans = new Part[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++)
                if(curlab[i][j].equals("W"))
                    ans[i][j] = chooseWall(i,j);
                else if (curlab[i][j].contains("F")){
                    ans[i][j] = chooseFloor(curlab[i][j]);
                }
        }

        return ans;
    }

    private Part chooseWall(int y, int x){
        boolean bot = false, top = false, right = false, left = false;

        if(y - 1 >= 0 && curlab[y - 1][x].equals("W"))
            top = true;

        if(y + 1 < n && curlab[y + 1][x].equals("W"))
            bot = true;

        if(x - 1 >= 0 && curlab[y][x - 1].equals("W"))
            left = true;

        if(x + 1 < m && curlab[y][x + 1].equals("W"))
            right = true;

        if(bot && top && left && right)
            return new Wall("HTM.png");
        else if(bot && top && left)
            return new Wall("HTL.png");
        else if(bot && top && right)
            return new Wall("HTR.png");
        else if(top && left && right)
            return new Wall("HTT.png");
        else if(bot && left && right)
            return new Wall("HTB.png");
        else if(bot && left)
            return new Wall("CBL.png");
        else if(bot && right)
            return new Wall("CBR.png");
        else if(top && left)
            return new Wall("CTL.png");
        else if(top && right)
            return new Wall("CTR.png");
        else if(top && bot)
            return new Wall("VHole.png");
        else if(left && right) {
            int choose = r.nextInt()%2;

            if(choose == 0)
                return new Wall("H.png");
            else
                return new Wall("HHole.png");
        }
        else if(top)
            return new Wall("ET.png");
        else if(bot)
            return new Wall("EB.png");
        else if(left)
            return new Wall("EL.png");
        else if(right)
            return new Wall("ER.png");
        else
            return new Wall("Alone.png");
    }

    private Part chooseFloor(String s){
        boolean skeleton = s.contains("S"), trap = s.contains("T");

        Part ans = new Floor("Floor.png");

        if(trap)
            ans = new Trap(ans, "Trap.png");

        if(skeleton)
            ans = new Skeleton(ans, "Bones.png");

        return ans;
    }
}
