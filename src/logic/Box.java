package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alopex on 25/6/2016.
 */
public class Box implements Item {

    private int xSize, ySize;
    private float xPos, yPos;
    protected float xVel, yVel;
    private float yGravity;
    protected boolean onFloor;
    private int xFieldSize, yFieldSize;
    private static final int MAX_FALLING_SPEED = 19;
    protected List<Item> obstacles;

    public Box(int xSize, int ySize, float xPos, float yPos) {

        this.xSize = xSize;
        this.ySize = ySize;
        this.xPos = xPos;
        this.yPos = yPos;
        this.onFloor = false;

        this.xVel = 0;
        this.yVel = 0;

    }

    public void setFieldVariables(float gravity, int xSize, int ySize, List<Item> obstacles) {
        this.yGravity = gravity;
        this.xFieldSize = xSize;
        this.yFieldSize = ySize;
        this.obstacles = obstacles;
    }

    private boolean intersects(Item item) {
        //check all 4 corners and see if it intersects with obstacle
        float xLowerBound = item.getXPos();
        float xUpperBound = xLowerBound + item.getXSize();
        float yLowerBound = item.getYPos();
        float yUpperBound = yLowerBound + item.getYSize();

        //check corners; add to arraylist first then iterate through
        ArrayList<Float[]> corners = new ArrayList<Float[]>();
        Float[] topLeft = {xPos, yPos};
        Float[] topRight = {xPos+xSize, yPos};
        Float[] bottomLeft = {xPos, yPos+ySize};
        Float[] bottomRight = {xPos+xSize, yPos+ySize};

        corners.add(topLeft);
        corners.add(topRight);
        corners.add(bottomLeft);
        corners.add(bottomRight);

        for(Float[] corner : corners) {
            if(corner[0] > xLowerBound && corner[0] < xUpperBound && corner[1] > yLowerBound && corner[1] < yUpperBound) {

                return true;
            }
        }
        return false;
    }

    private static boolean intersects(float x, float y, Item item) {
        //check all 4 corners and see if it intersects with obstacle
        float xLowerBound = item.getXPos();
        float xUpperBound = xLowerBound + item.getXSize();
        float yLowerBound = item.getYPos();
        float yUpperBound = yLowerBound + item.getYSize();

        return x > xLowerBound && x < xUpperBound && y > yLowerBound && y < yUpperBound;

    }

    private Item getIntersectingItem() {
        for(Item item : obstacles) {
            if(this.intersects(item)) {
                return item;
            }
        }
        return null;
    }

    //for debugging
    public boolean isIntersectingItem() {
        for(Item item : obstacles) {
            if(this.intersects(item)) {
                return true;
            }
        }
        return false;
    }

    public void move() {
        boolean hasTouchedFloor = false;
        float initialX = xPos;
        float initialY = yPos;

        yVel += yGravity;
        if(yVel >MAX_FALLING_SPEED) {
            yVel = MAX_FALLING_SPEED;
        }

        xPos += xVel;
        yPos += yVel;

        //alg v1
        //alg to expel box when it's inside an object
        while(isIntersectingItem()) {
            Item item = getIntersectingItem();
            //determine if initial position of item is below or above or in line with obstacle

            if(initialY >= item.getYPos()+item.getYSize()) { //case where item enters from bottom (travelling upwards)

                yPos = item.getYPos() + item.getYSize();
                yVel = 0;

            } else if(initialY + ySize <= item.getYPos()) { //case where item enters from top (travelling downwards)

                yPos = item.getYPos() - ySize;
                yVel = 0;
                hasTouchedFloor = true;

            } else if(initialX + xSize <= item.getXPos()){ //item would have hit the barrier from the side and stopped from the side

                xPos = item.getXPos() - xSize;
                xVel = 0;

            } else if(initialX >= item.getXPos() + item.getXSize()) {
                xPos = item.getXPos() + item.getXSize();
                xVel = 0;

            } else {
                //object has somehow gotten stuck
                System.out.println("should not see this");
                //expel object below the obstacle (lol)
                yPos = item.getYPos() + item.getYSize();
                yVel = 0;
                System.out.println("object just went through");
            }

        }

        onFloor = hasTouchedFloor;
    }

    @Override
    public float getXPos() {
        return xPos;
    }

    @Override
    public float getYPos() {
        return yPos;
    }

    @Override
    public int getXSize() {
        return xSize;
    }

    @Override
    public int getYSize() {
        return ySize;
    }

    @Override
    public String toString() {
        return "xPos = " + xPos + " yPos = " + yPos + " xVel = " + xVel + " yVel = " + yVel;
    }
}
