package project.othello.breton.viewFx;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import project.othello.breton.model.OthelloImpl;
import project.othello.breton.model.PlayerColor;

/**
 * This classes manages the scores view.
 *
 * @author Gabriel Breton - 43397
 */
public class ScoresInfos extends VBox {

    //private PlayerColor currentColor;
  //  private Label arrowCurrent;
    private Label scoreB;
    private Label scoreW;
    private Label pseudoB;
    private Label pseudoW;
    private Pawn pawnB;
    private Pawn pawnW;
    private HBox boxPawnScoreBlack;
    private HBox boxPawnScoreWhite;
    
    /**
     * The creates the pane with all the elements of the score.
     *
     * @param game the current session of Othello.
     */
    ScoresInfos(OthelloImpl game) {
        super();
        addElements(game);
        getStylesheets().addAll(this.getClass().getResource("style.css")
                .toExternalForm());
        setSpacing(30);
    }

    /**
     * Add the elements of the score.
     *
     * @param game the current session of Othello.
     */
    private void addElements(OthelloImpl game) {
        pseudoB = new Label();
        pseudoW = new Label();

        pawnB = new Pawn(PlayerColor.BLACK);
        pawnW = new Pawn(PlayerColor.WHITE);

        scoreB = new Label();
        scoreB.setText(String.valueOf(game.getScoreBlack()));

        scoreW = new Label();
        scoreW.setText(String.valueOf(game.getScoreWhite()));

//        arrowCurrent = new Label();
//        arrowCurrent.setId("arrow");

        setIDLabels();
        addScoresToPane();
    }

    /**
     * Set the ID's of the score's labels.
     */
    private void setIDLabels() {
        scoreB.setId("score");
        scoreW.setId("score");
        pseudoB.setId("pseudo");
        pseudoW.setId("pseudo");
    }

    private void addScoresToPane() {
        boxPawnScoreBlack = new HBox();
        boxPawnScoreWhite = new HBox();

        boxPawnScoreBlack.setSpacing(30);
        boxPawnScoreWhite.setSpacing(30);

        boxPawnScoreBlack.getChildren().addAll(pseudoB, pawnB, scoreB);
        boxPawnScoreWhite.getChildren().addAll(pseudoW, pawnW, scoreW);

      //  setCurrentArrow();
        getChildren().addAll(boxPawnScoreBlack, boxPawnScoreWhite);
    }

//    void setCurrentArrow() {
//        if (currentColor == PlayerColor.BLACK) {
//            boxPawnScoreBlack.getChildren().add(0, arrowCurrent);
//            //  boxPawnScoreWhite.getChildren().add(0, null);
//        } else {
//            boxPawnScoreWhite.getChildren().add(0, arrowCurrent);
//            //boxPawnScoreBlack.getChildren().add(0, null);
//        }
//    }

//    void refresh(int scoreBlack, int scoreWhite, PlayerColor currentColor) {
//        scoreB.setText(String.valueOf(scoreBlack));
//        scoreW.setText(String.valueOf(scoreWhite));
//   //     this.currentColor = currentColor;
// //       setCurrentArrow();
//    }
    void refresh(int scoreBlack, int scoreWhite) {
        scoreB.setText(String.valueOf(scoreBlack));
        scoreW.setText(String.valueOf(scoreWhite));
    }

    void setPseudos(String pseudoBlack, String pseudoWhite) {
        pseudoB.setText(pseudoBlack);
        pseudoW.setText(pseudoWhite);
    }
}
