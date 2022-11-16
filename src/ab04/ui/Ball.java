package ab04.ui;

import ab04.gamelogic.BallPosition;
import ab04.util.Interaktionsbrett;

public class Ball implements Movable {
    BallPosition position;
    private Rectangle form;
    private int speedX;
    private int speedY;

    // TODO: 16.11.2022
    public Ball(){

    }

    @Override
    public void move(int dx, int dy) {
    }

    // TODO: 16.11.2022
    private void move(int amountFrames) {

    }

    // TODO: 16.11.2022
    private void makeBallMoveFaster() {

    }

    // TODO: 16.11.2022
    private void makeBallMoveSlower() {

    }

    private void revertHorizontalMovement() {
        this.speedX *= -1;
    }

    private void revertVerticalMovement() {
        this.speedY *= -1;
    }

    void draw(Interaktionsbrett ib) {

    }
}