package ab04.ui;

import ab02.util.Interaktionsbrett;

public class Rectangle {

    private int x;
    private int y;
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
    }

    private void setX(int x) {
        if (x < 0)
            throw new IllegalArgumentException("X-Position muss positiv sein");
        this.x = x;
    }

    private void setY(int y) {
        if (y < 0)
            throw new IllegalArgumentException("Y-Position muss negativ sein");
        this.y = y;
    }

    private void setWidth(int width) {
        this.width = width;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    public void drawFrame(Interaktionsbrett ib) {
        ib.neuesRechteck(this.x, this.y, this.width, this.width);
    }

    public void drawFilling(Interaktionsbrett ib) {
        for (int i = 0; i < this.width; i++) {
            ib.neueLinie(this.x + i, this.y, this.x + i, this.y + this.width);
        }
    }

    // TODO: 16.11.2022
    public int up() {
        return 0;
    }

    // TODO: 16.11.2022
    public int down() {
        return 0;
    }// TODO: 16.11.2022

    public int left() {
        return 0;
    }// TODO: 16.11.2022

    public int right() {
        return 0;
    }// TODO: 16.11.2022

    public int width() {
        return 0;
    }// TODO: 16.11.2022

    public int height() {
        return 0;
    }// TODO: 16.11.2022

    public int midY() {
        return 0;
    }// TODO: 16.11.2022

    public int midX() {
        return 0;
    }// TODO: 16.11.2022

    public void move(int dx, int dy) {

    }// TODO: 16.11.2022

    public void moveTo(int x, int y) {

    }// TODO: 16.11.2022

    public boolean crosses() {
        return true;
    }

}