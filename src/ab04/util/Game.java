package ab04.util;

import ab04.gamelogic.CollisionDetection;
import ab04.ui.Ball;
import ab04.ui.Gamefield;
import ab04.ui.Player;

import java.util.Objects;

public class Game implements Update {
    private Update update;
    private EinUndAusgabe io = new EinUndAusgabe();
    private final int MARGIN_PADDLE = 10;
    private CollisionDetection detection;
    private Gamefield field;
    private Player playerLeft, playerRight;
    private Ball ball;
    private Interaktionsbrett ib;
    private final int FPMS = 17;

    // TODO: 16.11.2022
    public Game() {
        this.ib = new Interaktionsbrett();
        this.field = new Gamefield();
        ib.willTasteninfo(this);
        this.field.setUpdate(this);
    }

    // TODO: 16.11.2022
    public void gameLoop() throws InterruptedException {
        long difference;
        while (true) {
            long before = System.currentTimeMillis();
            ib.abwischen();
            field.update(this.field, this.ib);
            playerLeft.getPaddle().drawFilling(ib);
            playerRight.getPaddle().drawFilling(ib);
            long after = System.currentTimeMillis();
            difference = after - before;
            Thread.sleep(FPMS - difference);
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
        // Ball position must be set
        this.ball = new Ball();
        this.ball.draw(this.ib);

    }

    public void tasteGedrueckt(String s) throws InterruptedException {
        if (s.equals("s")) {
            System.out.println("Spiel startet");
            gameLoop();
        }
        if (s.equals("a")) {
            System.out.println("Player Movement");
            playerLeft.moveUp();
        }
        if (s.equals("y")) {
            System.out.println("Player Movement");
            playerLeft.moveDown();
        }
        if (s.equals("Oben")) {
            System.out.println("Player Movement");
            playerRight.moveUp();
        }
        if (s.equals("Unten")) {
            System.out.println("Player Movement");
            playerRight.moveDown();
        }
        if (s.equals("e")) {
            System.exit(1);
        }
    }

    @Override
    public void update(Gamefield field) {
        this.field = field;
    }
}