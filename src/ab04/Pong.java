package ab04;

import ab04.ui.Gamefield;
import ab04.ui.Player;
import ab04.util.Controller;
import ab04.util.Interaktionsbrett;

public class Pong {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.initialisePositions();
        controller.gameLoop();
    }
}
