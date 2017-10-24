package ghoulish.window;

import javax.swing.*;

public class GameFrame extends JFrame {
    GamePanel gp = new GamePanel();

    public GameFrame(){
        this.setLocation(20,20);
        this.setSize(gp.getWidth(),gp.getHeight());
        this.add(gp);

        this.setVisible(true);
    }
}
