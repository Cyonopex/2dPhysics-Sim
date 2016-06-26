package GUI;

import logic.GameField;
import logic.Item;
import logic.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * Created by Alopex on 25/6/2016.
 */
public class DrawingBoard extends JPanel {

    private GameField game;
    private int xSize, ySize;
    private Player player;
    private List<Item> items;

    public DrawingBoard(GameField game) {
        xSize = game.getXSize();
        ySize = game.getYSize();
        this.game = game;
        this.items= game.getItems();
        this.player = game.getPlayer();
        setBackground(Color.WHITE);
        setFocusable(true);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        for(Item item : items) {
            graphics.fillRect((int)item.getXPos(), (int)item.getYPos(), item.getXSize(), item.getYSize());
        }
        graphics.setColor(Color.BLUE);

        if(player.isIntersectingItem()) graphics.setColor(Color.RED);

        graphics.fillRect((int) player.getXPos(), (int) player.getYPos(), player.getXSize(), player.getYSize());
    }
}
