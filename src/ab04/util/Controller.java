package ab04.util;

import ab04.gamelogic.CollisionDetection;
import ab04.ui.Ball;
import ab04.ui.Gamefield;
import ab04.ui.Player;

public class Controller {
    private CollisionDetection detection;
    private Gamefield field;
    private Player playerLeft, playerRight;
    private Ball ball;
    private Interaktionsbrett ib;
    private final int FPMS = 17;

    // TODO: 16.11.2022
    public Controller() {
        this.ib = new Interaktionsbrett();
        this.field = new Gamefield();
    }

    // TODO: 16.11.2022
    private void gameLoop() {
    }

    // TODO: 16.11.2022
    public void initialisePositions() {
        this.field.draw(this.ib);
        this.playerLeft = new Player(this.field, field.getLeftX() + 3, field.getDIM().height / 2);
        this.playerRight = new Player(this.field, field.getRightX()- this.playerLeft.getPaddle().getWidth() - 3, field.getDIM().height / 2);

        this.ib.neuesRechteck(this.playerLeft, "Player_Left", playerLeft.getPaddle().getX(), playerLeft.getPaddle().getY(), playerLeft.getPaddle().getWidth(), playerLeft.getPaddle().getHeight());
        this.ib.neuesRechteck(this.playerRight, "Player_Right", playerRight.getPaddle().getX(), playerRight.getPaddle().getY(), playerRight.getPaddle().getWidth(), playerRight.getPaddle().getHeight());

    }


}
