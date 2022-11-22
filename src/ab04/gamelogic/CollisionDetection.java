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
        if(field.getField().crosses(ball.getForm())) {
            ball.revertVerticalMovement();
        }
    }

    public void checkCollisionWithBat(Ball ball) {
        if (playerLeft.getPaddle().crosses(ball.getForm())) {
            ball.revertHorizontalMovement();
        }
        if (playerRight.getPaddle().crosses(ball.getForm())) {
            ball.revertHorizontalMovement();
        }
    }
}
