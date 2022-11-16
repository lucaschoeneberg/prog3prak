package ab04.ui;

public class Player implements Movable {
    private Gamefield gamefield;
    private Rectangle paddle;
    private int score;

    public Player(Gamefield gamefield, int paddleX, int paddleY) {
        this.gamefield = gamefield;
        this.paddle = new Rectangle(paddleX, paddleY, gamefield.getDIM().width / 100, gamefield.getDIM().height / 10);
    }

    public Rectangle getPaddle() {
        return paddle;
    }

    @Override
    public void move(int dx, int dy) {
        this.paddle.move(dx, dy);
    }

    public void moveUP() {
        this.move(1, 1);
    }

    public void moveDown() {
        this.move(-1, -1);
    }

    public void resetScore() {
        this.score = 0;
    }

    public void score() {
        this.score++;
    }
}