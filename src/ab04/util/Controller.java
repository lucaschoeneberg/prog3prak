package ab04.util;

import ab04.gamelogic.CollisionDetection;
import ab04.ui.Ball;
import ab04.ui.Gamefield;
import ab04.ui.Player;

import java.awt.event.KeyEvent;

public class Controller {
    private EinUndAusgabe io = new EinUndAusgabe();
    private final int MARGIN_PADDLE = 10;
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
        ib.willTasteninfo(this);
    }

    // TODO: 16.11.2022
    public void gameLoop() {
        while (true) {
            tasteGedrueckt(io.leseString());
            System.out.println(io.leseString());
        }
    }

    // TODO: 16.11.2022
    public void initialisePositions() {
        this.field.draw(this.ib);
        this.playerLeft = new Player(this.field, field.getLeftX() + MARGIN_PADDLE, field.getDIM().height / 2);
        this.playerRight = new Player(this.field, field.getRightX() - this.playerLeft.getPaddle().getWidth() - MARGIN_PADDLE, field.getDIM().height / 2);
        this.ib.neuesRechteck(this.playerLeft, "Player_Left", playerLeft.getPaddle().getX(), playerLeft.getPaddle().getY(), playerLeft.getPaddle().getWidth(), playerLeft.getPaddle().getHeight());
        this.ib.neuesRechteck(this.playerRight, "Player_Right", playerRight.getPaddle().getX(), playerRight.getPaddle().getY(), playerRight.getPaddle().getWidth(), playerRight.getPaddle().getHeight());
        this.playerLeft.getPaddle().drawFilling(this.ib);
        this.playerRight.getPaddle().drawFilling(this.ib);

    }

    private void tasteGedrueckt(String s) {
        switch (s) {
            case "A":
                playerLeft.moveUp();
                break;
            case "y":
                playerLeft.moveDown();
                break;
            case "Oben":
                playerRight.moveUp();
                break;
            case "Unten":
                playerRight.moveDown();
                break;
        }
    }
}