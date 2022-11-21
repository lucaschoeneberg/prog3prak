package ab04.ui;

import ab04.gamelogic.BallPosition;
import ab04.util.Interaktionsbrett;

public class Ball implements Movable {
    BallPosition position;
    private Rectangle form;
    private int speedX;
    private int speedY;

    // TODO: 16.11.2022
    public Ball() {
        this.position = BallPosition.WITHIN_FIELD;
        this.form = new Rectangle(0, 0, 5, 5);
        this.speedX = 0;
        this.speedY = 0;
    }

    @Override
    public void move(int dx, int dy) {
        this.form.move(dx, dy);
    }

    // TODO: 16.11.2022
    private void move(int amountFrames) {

    }

    // TODO: 16.11.2022
    private void makeBallMoveFaster() {
        this.speedX += 1;
        this.speedY += 1;
    }

    // TODO: 16.11.2022
    private void makeBallMoveSlower() {
        this.speedX -= 1;
        this.speedY -= 1;
    }

    private void revertHorizontalMovement() {
        this.speedX *= -1;
    }

    private void revertVerticalMovement() {
        this.speedY *= -1;
    }

    public void draw(Interaktionsbrett ib) {
        this.form.drawFilling(ib);
    }
}