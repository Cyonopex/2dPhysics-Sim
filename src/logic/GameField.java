package logic;

import GUI.DrawingBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alopex on 25/6/2016.
 */
public class GameField implements Runnable {

    private int xSize, ySize;
    private float gravity;
    private boolean started;
    private Thread runThread;
    private List<Item> items;
    private Player player;
    private DrawingBoard board;
    private static final int FPS = 30;

    public GameField(int xSize, int ySize, float gravity) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.gravity = gravity;
        items = new ArrayList<Item>();
        started = false;
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void addItem(Item... item){
        items.addAll(Arrays.asList(item));
    }
    public void addPlayer(Player player) {
        this.player = player;
        player.setFieldVariables(gravity, xSize, ySize, items);
    }

    public Player getPlayer() {
        return player;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean hasGameStarted() {
        return started;
    }

    public void setDrawingBoard(DrawingBoard board) {
        this.board = board;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.currentThread();
                Thread.sleep(1000/FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.move();
            System.out.println(player);
            board.repaint();
        }
    }

    public void start() {
        boolean started = true;
        runThread = new Thread(this);
        runThread.start();
    }
}
