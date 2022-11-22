package ab04.ui;

import ab04.util.Interaktionsbrett;

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
        ib.neuesRechteck(this.x, this.y, this.width, this.height);
    }

    public void drawFilling(Interaktionsbrett ib) {
        for (int i = 0; i < this.width; i++) {
            ib.neueLinie(this.x + i, this.y, this.x + i, this.y + this.height);
        }
    }

    public int up() {
        return this.y;
    }

    public int down() {
        return this.y + this.height;
    }

    public int left() {
        return this.x;
    }

    public int right() {
        return this.x + this.width;
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public int midY() {
        return this.y;
    }

    public int midX() {
        return this.x + this.width / 2;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * src: java.awt.Rectangle
     */
    public boolean crosses(Rectangle other) {
        int tw = this.width;
        int th = this.height;
        int rw = other.width;
        int rh = other.height;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        int tx = this.x;
        int ty = this.y;
        int rx = other.x;
        int ry = other.y;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }
}