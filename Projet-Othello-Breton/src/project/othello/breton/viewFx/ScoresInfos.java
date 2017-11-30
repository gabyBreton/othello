package project.othello.breton.viewFx;

import javafx.scene.control.Label;
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
public class ScoresInfos extends HBox {

    private Label scoreB;
    private Label scoreW;
    //private Label scores;
    private Label pseudoB;
    private Label pseudoW;
    private Pawn pawnB;
    private Pawn pawnW; //Two VBox in a HBox
    
    
    /**
     * The creates the pane with all the elements of the score.
     *
     * @param game the current session of Othello.
     */
    ScoresInfos(OthelloImpl game) {
        super();
  //      initAndSet();
        addElements(game);
        getStylesheets().addAll(this.getClass().getResource("style.css")
                .toExternalForm());
    }

//    /**
//     * Initialize and set the gridpane of the score.
//     */
//    private void initAndSet() {
//        setHgap(10);
//        setVgap(15);
//    }

    /**
     * Add the elements of the score.
     *
     * @param game the current session of Othello.
     */
    private void addElements(OthelloImpl game) {
     //   scores = new Label("Scores");
       // scores.setUnderline(true);
        pseudoB = new Label();
  //      pseudoB.setText(game.getPlayers().get(0).getPseudo());
        
        pseudoW = new Label();
    //    pseudoW.setText(game.getPseudoWhite());
        
        pawnB = new Pawn(PlayerColor.BLACK);
        pawnW = new Pawn(PlayerColor.WHITE);
        
        scoreB = new Label();
        scoreB.setText(String.valueOf(game.getScoreBlack())); 
        
        scoreW = new Label();
        scoreW.setText(String.valueOf(game.getScoreWhite())); 
        
        setIDLabels();
        addScoresToBox();
     //   addScoresToPane();
    }

    /**
     * Set the ID's of the score's labels.
     */
    private void setIDLabels() {
      //  scores.setId("scores");
        scoreB.setId("scoreB");
        scoreW.setId("scoreW");
        pseudoB.setId("tfdPseudoB");
        pseudoW.setId("tfdPseudoW");
    }

    private void addScoresToBox() {
        VBox boxScoreBlack = new VBox();
        VBox boxScoreWhite = new VBox();
        
        boxScoreBlack.setSpacing(20);
        boxScoreWhite.setSpacing(20);
        
        boxScoreBlack.getChildren().addAll(pseudoB, pawnB, scoreB);
        boxScoreWhite.getChildren().addAll(pseudoW, pawnW, scoreW);

        getChildren().addAll(boxScoreBlack, boxScoreWhite);
    }

//    /**
//     * Adds the scores elements on the pane.
//     */
//    private void addScoresToPane() {
//        //add(scores, 0, 0);
//        add(pseudoB, 0, );
//        add(scoreB, 1, 1);
//        add(pseudoW, 0, 2);
//        add(scoreW, 1, 2);
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
