package project.othello.breton.viewFx;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import project.othello.breton.model.OthelloImpl;
import project.othello.breton.model.PlayerColor;

/**
 * This classes manages the scores view.
 *
 * @author Gabriel Breton - 43397
 */
public class ScoresInfos extends GridPane {

    private Label scoreB;
    private Label scoreW;
    private Label scores;
    private Label black;
    private Label white;
    private Pawn blackPawn;
    private Pawn whitePawn; //Two VBox in a HBox

    /**
     * The creates the pane with all the elements of the score.
     *
     * @param game the current session of Othello.
     */
    ScoresInfos(OthelloImpl game) {
        super();
        initAndSet();
        addScore(game);
        getStylesheets().addAll(this.getClass().getResource("style.css")
                .toExternalForm());
    }

    /**
     * Initialize and set the gridpane of the score.
     */
    private void initAndSet() {
        setHgap(10);
        setVgap(15);
    }

    /**
     * Add the elements of the score.
     *
     * @param game the current session of Othello.
     */
    private void addScore(OthelloImpl game) {
        scores = new Label("Scores");
        scores.setUnderline(true);
        
        black = new Label();
        black.setText(game.getPlayers().get(0).getPseudo());
        white = new Label();
        white.setText(game.getPseudoWhite());
        scoreB = new Label();
        scoreB.setText(String.valueOf(game.getScoreBlack())); 
        scoreW = new Label();
        scoreW.setText(String.valueOf(game.getScoreWhite())); 
        setIDLabels();
        addScoresToPane();

    }

    /**
     * Set the ID's of the score's labels.
     */
    private void setIDLabels() {
        scores.setId("scores");
        scoreB.setId("scoreB");
        scoreW.setId("scoreW");
        black.setId("tfdPseudoB");
        white.setId("tfdPseudoW");
    }

    /**
     * Adds the scores elements on the pane.
     */
    private void addScoresToPane() {
        add(scores, 0, 0);
        add(black, 0, 1);
        add(scoreB, 1, 1);
        add(white, 0, 2);
        add(scoreW, 1, 2);
    }
    
    void refresh(int scoreBlack, int scoreWhite) {        
        scoreB.setText(String.valueOf(scoreBlack));
        scoreW.setText(String.valueOf(scoreWhite));
    }

}
