package ab04.ui;

import ab04.util.Interaktionsbrett;

public class Player implements Movable {
    private int MOVEMENT_SPEED, score;
    private Gamefield gamefield;
    private Rectangle paddle;

    public Player(Gamefield gamefield, int paddleX, int paddleY) {
        this.gamefield = gamefield;
        this.paddle = new Rectangle(paddleX, paddleY, gamefield.getDIM().width / 100, gamefield.getDIM().height / 10);
        this.MOVEMENT_SPEED = this.paddle.getHeight()/4;
    }

    public Rectangle getPaddle() {
        return paddle;
    }

    @Override
    public void move(int dx, int dy) {
        this.paddle.move(dx, dy);
    }

    @Override
    public void moveTo(int x, int y) {
        this.paddle.moveTo(x, y);
    }

    public void moveUp() {
        if (this.paddle.getY() > this.gamefield.getTopY())
            this.move(0, -MOVEMENT_SPEED);
        if (this.paddle.getY() < this.gamefield.getTopY())
            this.paddle.moveTo(this.paddle.getX(), this.gamefield.getTopY());
    }

    public void moveDown() {
        if (this.paddle.getY()+this.paddle.getHeight() < this.gamefield.getBottomY())
        this.move(0, MOVEMENT_SPEED);
        if(this.paddle.getY()+this.paddle.getHeight() > this.gamefield.getBottomY())
            this.paddle.moveTo(this.paddle.getX(), this.gamefield.getBottomY()-this.paddle.getHeight());
    }

    public void draw(Interaktionsbrett ib) {
        this.paddle.drawFilling(ib);
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