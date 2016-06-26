package GUI;

import logic.GameField;
import logic.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Alopex on 26/6/2016.
 */
public class KeyboardListener implements KeyListener {

    Player player;
    GameField game;

    public KeyboardListener(Player player, GameField game) {
        this.player = player;
        this.game = game;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setButtonLeft(true);
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setButtonRight(true);
        } else if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.setButtonUp(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setButtonLeft(false);
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setButtonRight(false);
        } else if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.setButtonUp(false);
        }
    }
}
