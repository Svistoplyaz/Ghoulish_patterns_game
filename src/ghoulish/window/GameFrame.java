package ghoulish.window;

import ghoulish.Main;
import ghoulish.game.Game;
import ghoulish.game.TurningStateMachine;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame {
    //    Game game = Main.game;
    TurningStateMachine turn = TurningStateMachine.getInstance();

    public GameFrame() {
        this.setLocation(20, 20);
        this.add(Visualiser.getInstance().gamePanel);
        this.pack();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        TurningStateMachine.MyThread thread = TurningStateMachine.getInstance().thread;

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                synchronized (thread) {
                    turn.pressKey(e.getKeyChar());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

}
