package ghoulish.util;

import ghoulish.labyrinth.*;

import java.io.FileReader;
import java.util.Random;

public class BlockChooser {
    private String[][] curlab;
    private int n, m;
    private Random r = new Random();

    private static String textureWall = "resources/Labyrinth/Wall/";
    private static String textureFloor = "resources/Labyrinth/Floor/";

    public BlockChooser() {
        LabReader lr = null;
        try {
            lr = new LabReader(new FileReader("resources/Labyrinth.in"));
        } catch (Exception e) {
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

    public Part[][] constructLab() {
        n = curlab.length;
        m = curlab[0].length;

        Part[][] ans = new Part[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                if (curlab[i][j].equals("W"))
                    ans[i][j] = chooseWall(i, j);
                else if (curlab[i][j].contains("F")) {
                    ans[i][j] = chooseFloor(i, j, curlab[i][j]);
                }
        }

        return ans;
    }

    private Part chooseWall(int y, int x) {
        boolean bot = false, top = false, right = false, left = false;

        if (y - 1 >= 0 && curlab[y - 1][x].equals("W"))
            top = true;

        if (y + 1 < n && curlab[y + 1][x].equals("W"))
            bot = true;

        if (x - 1 >= 0 && curlab[y][x - 1].equals("W"))
            left = true;

        if (x + 1 < m && curlab[y][x + 1].equals("W"))
            right = true;

        String wallans;
        if (bot && top && left && right)
            wallans = "HTM.png";
        else if (bot && top && left)
            wallans = "HTL.png";
        else if (bot && top && right)
            wallans = "HTR.png";
        else if (top && left && right)
            wallans = "HTT.png";
        else if (bot && left && right)
            wallans = "HTB.png";
        else if (bot && left)
            wallans = "CBL.png";
        else if (bot && right)
            wallans = "CBR.png";
        else if (top && left)
            wallans = "CTL.png";
        else if (top && right)
            wallans = "CTR.png";
        else if (top && bot)
            wallans = "VHole.png";
        else if (left && right) {
            int choose = r.nextInt() % 2;

            if (choose == 0)
                wallans = "H.png";
            else
                wallans = "HHole.png";
        } else if (top)
            wallans = "ET.png";
        else if (bot)
            wallans = "EB.png";
        else if (left)
            wallans = "EL.png";
        else if (right)
            wallans = "ER.png";
        else
            wallans = "Alone.png";

        return new Wall(y, x, textureWall + wallans);
    }

    private Part chooseFloor(int y, int x, String s) {
        boolean skeleton = s.contains("S"), trap = s.contains("T"), door = s.contains("D");

        Part ans = new Floor(y, x, textureFloor + "Floor.png");

        if (trap)
            ans = new Trap(y, x, ans, textureFloor + "Trap.png");

        if (skeleton)
            ans = new Bones(y, x, ans, textureFloor + "Bones.png");

        if(door)
            ans = new Door(y, x, ans,textureFloor + "DoorClosed.png");

        return ans;
    }

}
