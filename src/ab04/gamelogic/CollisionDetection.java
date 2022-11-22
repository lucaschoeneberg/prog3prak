package ab04.gamelogic;

import ab04.ui.Ball;
import ab04.ui.Gamefield;
import ab04.ui.Player;

public class CollisionDetection {
    private Gamefield field;
    private Player playerLeft, playerRight;

    public CollisionDetection(Gamefield field, Player playerLeft, Player playerRight) {
        this.field = field;
        this.playerLeft = playerLeft;
        this.playerRight = playerRight;
    }

    public void checkCollisionWithField(Ball ball) {
        if (ball.getForm().up() <= this.field.getTopY() || ball.getForm().down() >= this.field.getBottomY()) {
            ball.revertVerticalMovement();
        }
    }

    public void checkCollisionWithPaddle(Ball ball) {
        if (playerLeft.getPaddle().crosses(ball.getForm())) {
            ball.revertHorizontalMovement();
            ball.revertVerticalMovement();
        }
        if (playerRight.getPaddle().crosses(ball.getForm())) {
            ball.revertHorizontalMovement();
            ball.revertVerticalMovement();
        }
    }
}