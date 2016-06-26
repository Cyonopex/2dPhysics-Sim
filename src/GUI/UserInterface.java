package GUI;

import logic.GameField;
import logic.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Alopex on 25/6/2016.
 */
public class UserInterface implements Runnable {

    private JFrame frame;
    private GameField game;
    private DrawingBoard board;
    private Player player;

    public UserInterface(GameField game, Player player) {
        this.game = game;
        this.player = player;
    }

    public void run() {
        frame = new JFrame("Physics Simulator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.setResizable(false);

        frame.pack();
        frame.setVisible(true);

    }

    private void createComponents(Container container) {
        board = new DrawingBoard(game);
        board.setPreferredSize(new Dimension(game.getXSize(), game.getYSize()));

        KeyboardListener listener = new KeyboardListener(player, game);
        board.addKeyListener(listener);

        container.add(board);
    }

    public DrawingBoard getDrawingBoard() {
        return board;
    }
}
