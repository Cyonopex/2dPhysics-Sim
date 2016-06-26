package logic;

/**
 * Created by Alopex on 25/6/2016.
 */
public class Player extends Box {

    private static final int SIDEACCELERATION = 2;
    private static final int MAX_SPEED = 10;
    private static final float SLIDE = 0.8f;
    private static final float JUMP_ACC = 21f;
    protected boolean buttonLeft, buttonRight, buttonUp;

    public Player(int xSize, int ySize, float xPos, float yPos) {
        super(xSize, ySize, xPos, yPos);
        buttonLeft = false;
        buttonRight = false;
        buttonUp = false;
    }

    @Override
    public void move() {
        super.move();

        if(buttonLeft && !buttonRight) {
            xVel -= SIDEACCELERATION;
            if(xVel < -MAX_SPEED) xVel = -MAX_SPEED;
        } else if (buttonRight && !buttonLeft) {
            xVel += SIDEACCELERATION;
            if(xVel > MAX_SPEED) xVel = MAX_SPEED;
        } else {
            xVel *= SLIDE;
            if(Math.abs(xVel) < 0.1) xVel = 0;
        }
        if(onFloor && buttonUp) {
            yVel = -JUMP_ACC;

        }
    }

    public void setButtonLeft(boolean button) {
        this.buttonLeft = button;

    }

    public void setButtonRight(boolean button) {
        this.buttonRight = button;

    }

    public void setButtonUp(boolean button) {
        this.buttonUp = button;
    }

}
