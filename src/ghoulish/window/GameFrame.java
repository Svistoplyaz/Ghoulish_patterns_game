package ghoulish.window;

import javax.swing.*;

public class GameFrame extends JFrame {
    GamePanel gp = new GamePanel();

    public GameFrame(){
        this.setLocation(20,20);
        this.add(gp);
        this.pack();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
