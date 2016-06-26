package logic;

/**
 * Created by Alopex on 26/6/2016.
 */
public class Obstacle implements Item {

    private float xPos, yPos;
    private int xSize, ySize;

    public Obstacle(float xPos, float yPos, int xSize, int ySize) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;

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
}
