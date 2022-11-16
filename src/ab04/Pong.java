package ab04;

import ab04.ui.Gamefield;
import ab04.util.Interaktionsbrett;

public class Pong {
    public static void main(String[] args) {
        Interaktionsbrett ib = new Interaktionsbrett();
        Gamefield spielfeld = new Gamefield();
        spielfeld.draw(ib);
    }
}
