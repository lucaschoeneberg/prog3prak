package ab04.ui;

import ab04.util.Interaktionsbrett;
import java.awt.*;

public class Gamefield {
    private final Dimension DIM = new Dimension(800,700);
    private final int MARGIN = 10;
    private Rectangle field;

    public Gamefield() {
        this.field = new Rectangle(MARGIN, MARGIN, DIM.width - MARGIN*4, DIM.height - MARGIN*8);
    }

    public void draw(Interaktionsbrett ib){
        ib.neuesRechteck(field.getX(), field.getY(), field.getWidth(), field.getHeight());
        ib.neueLinie(field.midX(), field.midY(), field.midX(), field.midY()+ field.getHeight());
    }
}