package ab02.ui;

import ab02.util.Interaktionsbrett;

public class Quadrat {

    private int x;
    private int y;
    private int length;

    public Quadrat(int x, int y, int length) {
        this.setX(x);
        this.setY(y);
        this.setLength(length);
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

    private void setLength(int length) {
        this.length = length;
    }

    public void drawFrame(Interaktionsbrett ib) {
        ib.neuesRechteck(this.x, this.y, this.length, this.length);
    }

    public void drawFilling(Interaktionsbrett ib) {
        this.drawFrame(ib);
        int i = 0;
        while (i < this.length){
            ib.neueLinie(this.x + i, this.y, this.x + i, this.y + this.length);
            i++;
        }
    }
}