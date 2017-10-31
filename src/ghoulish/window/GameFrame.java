package ghoulish.window;

import ghoulish.game.Game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame {
    Game game = new Game();

    public GameFrame(){
        this.setLocation(20,20);
        this.add(game.getGp());
        this.pack();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if(code == KeyEvent.VK_W){
                    game.movePlayer(-1,0);
                }else if(code == KeyEvent.VK_A){
                    game.movePlayer(0,-1);
                }else if(code == KeyEvent.VK_S){
                    game.movePlayer(1,0);
                }else if(code == KeyEvent.VK_D){
                    game.movePlayer(0,1);
                }else if(code == KeyEvent.VK_E){
                    game.loot();
                }else if(code == KeyEvent.VK_R){

                }else if(code == KeyEvent.VK_SPACE){
                    game.skipTurn();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

}
