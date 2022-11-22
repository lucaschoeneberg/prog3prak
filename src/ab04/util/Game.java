package ab04.util;

import ab04.gamelogic.BallPosition;
import ab04.gamelogic.CollisionDetection;
import ab04.ui.Ball;
import ab04.ui.Gamefield;
import ab04.ui.Player;

public class Game {

    private final int MAX_SCORE = 5, MARGIN_PADDLE = 10, FPMS = 17;
    private boolean won = false, running = false;
    private CollisionDetection detection;
    private Gamefield field;
    private Player playerLeft, playerRight;
    private Ball ball;
    private Interaktionsbrett ib;

    public Game() {
        this.ib = new Interaktionsbrett();
        this.field = new Gamefield();
        this.ball = new Ball();
        this.playerLeft = new Player(this.field, field.getLeftX() + MARGIN_PADDLE, field.getDIM().height / 2);
        this.playerRight = new Player(this.field, field.getRightX() - this.playerLeft.getPaddle().getWidth() - MARGIN_PADDLE, field.getDIM().height / 2);
        this.detection = new CollisionDetection(this.field, this.playerLeft, this.playerRight);
        ib.willTasteninfo(this);
        this.initialisePositions();
    }

    private void drawScores() {
        this.ib.neuerText(this.field.getDIM().width / 2 - 10, 30, Integer.toString(playerLeft.getScore()));
        this.ib.neuerText(this.field.getDIM().width / 2 + 24, 30, Integer.toString(playerRight.getScore()));
    }

    public void initialisePositions() {
        this.playerLeft.getPaddle().moveTo(this.playerLeft.getPaddle().getX(), this.field.getDIM().height / 2 - this.playerLeft.getPaddle().getHeight() / 2);
        this.playerRight.getPaddle().moveTo(this.playerRight.getPaddle().getX(), this.field.getDIM().height / 2 - this.playerRight.getPaddle().getHeight() / 2);
        this.field.draw(this.ib);
        this.playerLeft.draw(this.ib);
        this.playerRight.draw(this.ib);
        this.ball.setRandomStartingPointLeftSide();
        this.ball.draw(this.ib);
        drawScores();
    }

    private void checkCollision() {
        this.detection.checkCollisionWithField(this.ball);
        this.detection.checkCollisionWithPaddle(this.ball);
        if (ball.getPosition() == BallPosition.OUTSIDE_LEFT) {
            playerRight.score();
            ball.setRandomStartingPointRightSide();
        }
        if (ball.getPosition() == BallPosition.OUTSIDE_RIGHT) {
            playerLeft.score();
            ball.setRandomStartingPointLeftSide();
        }
    }

    private void moveBall() {
        this.ball.move(this.ball.getSpeedX(), this.ball.getSpeedY());
    }

    private void updateScreen() {
        this.field.draw(this.ib);
        this.playerLeft.getPaddle().drawFilling(this.ib);
        this.playerRight.getPaddle().drawFilling(this.ib);
        this.ball.draw(this.ib);
        drawScores();
    }

    private void checkIfWon() {
        if (this.playerLeft.getScore() == MAX_SCORE || this.playerRight.getScore() == MAX_SCORE) {
            this.won = true;
            this.running = false;
        }
    }

    private void drawWinningMessage() {
        this.ib.neuerText(this.field.getDIM().width / 4 + 50, this.field.getDIM().height / 2, "Spieler " + (this.playerLeft.getScore() == MAX_SCORE ? "links" : "rechts") + " hat gewonnen!\n Drücke S zum Neustart");

    }

    public void gameLoop() {
        long difference;
        while (!won) {
            long before = System.currentTimeMillis();
            ib.abwischen();
            checkCollision();
            moveBall();
            updateScreen();
            checkIfWon();
            long after = System.currentTimeMillis();
            difference = after - before;
            try {
                Thread.sleep(FPMS - difference);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        drawWinningMessage();
    }

    public void tasteGedrueckt(String s) throws InterruptedException {
        if (s.equals("e")) {
            System.out.println("Spiel wird geschlossen!");
            System.exit(1);
        }
        if (!running && s.equals("s")) {
            this.won = false;
            running = true;
            System.out.println("Game started");
            new Thread(() -> {
                this.playerLeft.resetScore();
                this.playerRight.resetScore();
                gameLoop();
            }).start();
        }
        if (running) {
            switch (s) {
                case "a" -> playerLeft.moveUp();
                case "y" -> playerLeft.moveDown();
                case "Oben" -> playerRight.moveUp();
                case "Unten" -> playerRight.moveDown();
                case "Rechts" -> ball.makeBallMoveFaster();
                case "Links" -> ball.makeBallMoveSlower();
            }
        }
    }
}