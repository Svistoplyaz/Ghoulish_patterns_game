package ghoulish.util;

import ghoulish.labyrinth.*;

public class BlockChooser {
    public Part chooseBlock(int i){
        Part ans;

        switch (i){
            case 1 : ans = new Wall();
                break;
            case 2 : ans = new Road();
                break;
            case 3 : ans = new Trap();
                break;
            default : ans = new GreatWall();

        }

        return ans;
    }
}
