package project.othello.breton.viewFx;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import project.othello.breton.model.OthelloImpl;

/**
 * This class provides methods to create and use the final layout for when the
 * party is over.
 *
 * @author Gabriel Breton - 43397
 */
class FinalLayout extends BorderPane {

    private Label winner;
    private Label gameOver;
    private GridPane buttonsZone;

    FinalLayout(OthelloImpl game, String whoIsWinner) {
        super();
        winner = new Label(whoIsWinner);
        winner.setId("winner");
        gameOver = new Label("GAME OVER !");
        gameOver.setId("gameOver");
        setMinSize(1000, 1000);
        setTop(gameOver);
        setCenter(winner);
        setId("finalLayout");
    }

}


