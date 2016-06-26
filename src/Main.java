import GUI.UserInterface;
import logic.GameField;
import logic.Box;
import logic.Obstacle;
import logic.Player;

import javax.swing.*;

/**
 * Created by Alopex on 25/6/2016.
 */
public class Main {
    public static void main(String args[]) {

        GameField game = new GameField(800, 600, 2);
        Player player = new Player(20, 20, 0, 400);
        game.addPlayer(player);

        Obstacle leftWall = new Obstacle(-100, 0, 100, 600);
        Obstacle rightWall = new Obstacle(800, 0, 100, 600);
        Obstacle floor = new Obstacle(0, 500, 800, 500);

        Obstacle firstLowWall = new Obstacle(700, 450, 30, 600);
        Obstacle firstStep = new Obstacle(600, 400, 50, 50);
        Obstacle secondStep = new Obstacle(500, 380, 50, 50);
        Obstacle thirdLine = new Obstacle(300, 370, 120, 20);
        Obstacle leftColumn = new Obstacle(220, 250, 20, 150);
        Obstacle rightColumn = new Obstacle(300, 370, 20, 100);
        Obstacle secondFloor = new Obstacle(110, 450, 200, 20);
        Obstacle leftSecondWall = new Obstacle(100, 200, 20, 270);
        Obstacle firstLadder = new Obstacle(110, 360, 30, 30);
        Obstacle secondLadder = new Obstacle(200, 310, 40, 30);

        game.addItem(leftWall, rightWall, floor, firstLowWall, firstStep, secondStep, thirdLine, leftColumn, rightColumn, secondFloor, leftSecondWall, firstLadder, secondLadder);

        UserInterface ui = new UserInterface(game, player);
        SwingUtilities.invokeLater(ui);

        while (ui.getDrawingBoard() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("The drawing board hasn't been created yet.");
            }
        }
        game.setDrawingBoard(ui.getDrawingBoard());
        game.start();
    }
}