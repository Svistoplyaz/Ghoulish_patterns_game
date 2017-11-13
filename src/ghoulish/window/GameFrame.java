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
                    int code = e.getKeyCode();
                    if (code == KeyEvent.VK_W) {
                        turn.wPressed();
                    } else if (code == KeyEvent.VK_A) {
                        turn.aPressed();
                    } else if (code == KeyEvent.VK_S) {
                        turn.sPressed();
                    } else if (code == KeyEvent.VK_D) {
                        turn.dPressed();
                    } else if (code == KeyEvent.VK_E) {
                        turn.ePressed();
                    } else if (code == KeyEvent.VK_R) {
                        turn.startGame();
                    } else if (code == KeyEvent.VK_SPACE) {
                        turn.spacePressed();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

}
