package ab04.ui;

import ab04.gamelogic.BallPosition;
import ab04.util.Interaktionsbrett;

public class Ball implements Movable {
    BallPosition position;
    private Rectangle form;
    private int speedX;
    private int speedY;

    public Ball() {
        this.position = BallPosition.WITHIN_FIELD;
        this.form = new Rectangle(0, 0, 5, 5);
        this.speedX = 4;
        this.speedY = 1;
    }

    @Override
    public void move(int dx, int dy) {
        this.form.move(dx, dy);
        this.setBallPosition();
    }

    public void moveTo(int x, int y) {
        this.form.moveTo(x, y);
    }

    // TODO: 16.11.2022
    private void move(int amountFrames) {

    }

    public void makeBallMoveFaster() {
        this.speedX += 1;
    }

    public void makeBallMoveSlower() {
        this.speedX -= 1;
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

    public void setRandomStartingPointLeftSide() {
        int x = (int) (Math.random() * 310 + 40);
        int y = (int) (Math.random() * 650 + 20);
        this.moveTo(x, y);
    }

    public void setRandomStartingPointRightSide() {
        int x = (int) (Math.random() * 320 + 360);
        int y = (int) (Math.random() * 670 + 20);
        this.moveTo(x, y);
    }

    public void setBallPosition() {
        if (this.form.getX() < 20) {
            this.position = BallPosition.OUTSIDE_LEFT;
        } else if (this.form.getX() > 780) {
            this.position = BallPosition.OUTSIDE_RIGHT;
        } else {
            this.position = BallPosition.WITHIN_FIELD;
        }
    }

    public BallPosition getPosition() {
        return this.position;
    }

    public Rectangle getForm() {
        return this.form;
    }
}