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

    public void moveUp() {
        if (this.paddle.getY() > this.gamefield.getTopY())
            this.move(-1, -1);
    }

    public void moveDown() {
        if (this.paddle.getY()+this.paddle.getHeight() < this.gamefield.getBottomY())
        this.move(1, 1);
    }

    public void resetScore() {
        this.score = 0;
    }

    public void score() {
        this.score++;
    }

    public int getScore(){
        return this.score;
    }
}