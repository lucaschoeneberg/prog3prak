package ab04.ui;

import ab04.gamelogic.BallPosition;
import ab04.util.Interaktionsbrett;

public class Ball implements Movable {

    private final int MAX_SPEED = 10, MIN_SPEED = 1;
    private int speedX, speedY;
    private BallPosition position;
    private Rectangle form;

    public Ball() {
        this.position = BallPosition.WITHIN_FIELD;
        this.form = new Rectangle(0, 0, 5, 5);
        this.speedX = 4;
        this.speedY = 2;
    }

    @Override
    public void move(int dx, int dy) {
        this.form.move(dx, dy);
        this.setBallPosition();
    }

    public void moveTo(int x, int y) {
        this.form.moveTo(x, y);
    }

    public void makeBallMoveFaster() {
        if (this.speedX < MAX_SPEED && this.speedX > 0)
            this.speedX++;
        else if (this.speedX > -MAX_SPEED && this.speedX < 0)
            this.speedX--;
    }

    public void makeBallMoveSlower() {
        if (this.speedX > MIN_SPEED && this.speedX > 0)
            this.speedX--;
        else if (this.speedX < -MIN_SPEED && this.speedX < 0)
            this.speedX++;
    }

    public void revertHorizontalMovement() {
        this.speedX *= -1;
    }

    public void revertVerticalMovement() {
        this.speedY *= -1;
    }

    public void draw(Interaktionsbrett ib) {
        this.form.drawFilling(ib);
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public BallPosition getPosition() {
        return this.position;
    }

    public Rectangle getForm() {
        return this.form;
    }

    public void setRandomStartingPointLeftSide() {
        int x = (int) (Math.random() * 320 + 40);
        int y = (int) (Math.random() * 600 + 20);
        this.moveTo(x, y);
    }

    public void setRandomStartingPointRightSide() {
        int x = (int) (Math.random() * 320 + 360);
        int y = (int) (Math.random() * 600 + 20);
        this.moveTo(x, y);
    }

    public void setBallPosition() {
        if (this.form.getX() < 20) {
            this.position = BallPosition.OUTSIDE_LEFT;
        } else if (this.form.getX() > 760) {
            this.position = BallPosition.OUTSIDE_RIGHT;
        } else {
            this.position = BallPosition.WITHIN_FIELD;
        }
    }
}