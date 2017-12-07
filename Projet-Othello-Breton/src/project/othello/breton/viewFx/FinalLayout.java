package project.othello.breton.viewFx;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import project.othello.breton.model.OthelloImpl;

/**
 * This class provides methods to create and use the final layout for when the
 * party is over.
 *
 * @author Gabriel Breton - 43397
 */
class FinalLayout extends VBox {

    private Label winner;
    //private ScoresInfos scoresInfos;
    private GridPane buttonsZone;

    FinalLayout(OthelloImpl game) {
        super();
        //this.scoresInfos = scoresInfos;
        winner = makeWinner(game);
        getChildren().add(winner);
    }

    private Label makeWinner(OthelloImpl game) {
        winner = new Label("Winner unknown !");
      //  String winnerColor = game.getWinner();
        
        return winner;
    }
}


