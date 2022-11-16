package ab04.util;

import ab04.gamelogic.CollisionDetection;
import ab04.ui.Ball;
import ab04.ui.Gamefield;
import ab04.ui.Player;

public class Controller {
    private CollisionDetection detection;
    private Gamefield field;
    private Player playerLeft,playerRight;
    private Ball ball;
    private Interaktionsbrett ib;
    private final int FPMS = 17;

    // TODO: 16.11.2022
    public Controller(){
        initialisePositions();
        gameLoop();
    }

    // TODO: 16.11.2022
    private void gameLoop() {
    }

    // TODO: 16.11.2022
    private void initialisePositions() {
    }


}
