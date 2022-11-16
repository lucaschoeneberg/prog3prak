package ab04.ui;

public class Player implements Movable {
    private Gamefield gamefield;
    private Rectangle bat;
    private int score;

    // TODO: 16.11.2022
    public Player(Gamefield gamefield, int batX, int batY) {

    }

    @Override
    public void move(int dx, int dy) {
        this.bat.move(dx, dy);
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
