package ghoulish.graphics;

import ghoulish.creatures.Monster;
import ghoulish.creatures.Player;

import javax.swing.*;

/**
 * Created by Alexander on 10.12.2017.
 */
public class DistanceCommand implements ICommand {
    private Monster monster;

    public DistanceCommand(Monster mon) {
        monster = mon;
    }

    @Override
    public void execute() {
        int y = monster.getY() - Player.getInstance().getY();
        int x = monster.getX() - Player.getInstance().getX();

        int distance = (int) Math.sqrt(y * y + x * x);
        String ans = "Monster on square " + (monster.getY() + 1) + " " + (monster.getX() + 1) + " is " + distance + " squares from you";
        System.out.println(ans);
//        JOptionPane.showMessageDialog(null, ans);
    }
}
