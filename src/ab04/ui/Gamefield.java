package ab04.ui;

import ab04.util.Interaktionsbrett;
import java.awt.*;

public class Gamefield {
    private final int MARGIN = 10;
    private final Dimension IB_DIM = new Dimension(800, 700);
    private final Dimension FIELD_DIM = new Dimension(IB_DIM.width - MARGIN * 4, IB_DIM.height - MARGIN * 8);
    private final int leftX, topY, rightX, bottomY;

    private final Rectangle field;

    public Gamefield() {
        this.field = new Rectangle(MARGIN, MARGIN, FIELD_DIM.width, FIELD_DIM.height);
        this.leftX = MARGIN;
        this.topY = MARGIN;
        this.rightX = MARGIN + IB_DIM.width - MARGIN * 4;
        this.bottomY = MARGIN + IB_DIM.height - MARGIN * 8;
    }

    public void draw(Interaktionsbrett ib) {
        ib.neuesRechteck(field.getX(), field.getY(), field.getWidth(), field.getHeight());
        ib.neueLinie(field.midX(), field.midY(), field.midX(), field.midY() + field.getHeight());
    }

    public Dimension getDIM() {
        return FIELD_DIM;
    }

    public int getLeftX() {
        return leftX;
    }

    public int getTopY() {
        return topY;
    }

    public int getRightX() {
        return rightX;
    }

    public int getBottomY() {
        return bottomY;
    }
}