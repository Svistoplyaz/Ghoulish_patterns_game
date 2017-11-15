package ghoulish.window;

import ghoulish.game.KeyReader;
import ghoulish.graphics.Visualiser;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.im.InputContext;
import java.util.Locale;

public class GameFrame extends JFrame {
    //    Game game = Main.game;
//    TurningStateMachine turn = TurningStateMachine.getInstance();
    KeyReader turn = new TurningMachineProxy();
    KeyReader adapter = new LanguageAdapter(turn);

    public GameFrame() {
        this.setLocation(20, 20);
        this.add(Visualiser.getInstance().gamePanel);
        this.pack();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                Locale locale = InputContext.getInstance().getLocale();
                switch (locale.getLanguage()){
                    case "ru":
                        adapter.pressKey(e.getKeyChar());
                        break;
                    case "en":
                        turn.pressKey(e.getKeyChar());
                        break;
                    default:
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

}
