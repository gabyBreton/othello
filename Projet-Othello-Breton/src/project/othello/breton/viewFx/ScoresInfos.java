package project.othello.breton.viewFx;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import project.othello.breton.model.OthelloImpl;
import project.othello.breton.model.GameColor;

/**
 * This classes provides methods to create and manages the scores information 
 * boxes.
 *
 * @author Gabriel Breton - 43397
 */
public class ScoresInfos extends HBox {

    private GameColor currentColor;

    private Label scoreB;
    private Label scoreW;

    private Label pseudoB;
    private Label pseudoW;

    private Pawn pawnB;
    private Pawn pawnW;

    private HBox boxPawnScoreBlack;
    private HBox boxPawnScoreWhite;

    private VBox boxPseudoB;
    private VBox boxPseudoW;

    private GridPane paneBlack;
    private GridPane paneWhite;

    /**
     * Creates the box with all the elements of the score for each player.
     *
     * @param game the current session of Othello.
     */
    ScoresInfos(OthelloImpl game) {
        super();
        this.currentColor = game.getCurrentColor();
        setSpacing(40);

        createElements(game);
        setIDLabels();
        setBackgroundColors();

        getStylesheets().addAll(this.getClass()
                                    .getResource("style.css")
                                    .toExternalForm());
        getChildren().addAll(paneBlack, paneWhite);
    }

    /**
     * Creates the elements of the score information boxes, such as pseudo, 
     * color pawn and score.
     *
     * @param game the current session of Othello.
     */
    private void createElements(OthelloImpl game) {
        makeBlackScoreZone(game);
        makeWhiteScoreZone(game);
    }

    /**
     * Makes the zone for the informations of the black player.
     * 
     * @param game the current session of Othello.
     */
    private void makeBlackScoreZone(OthelloImpl game) {
        paneBlack = makeScoreGrid(20, 280, "paneScore", 
                                  new Insets(10, 10, 10, 10));
        pseudoB = new Label();
        boxPseudoB = makeBoxPseudo(pseudoB, 120);
        makeBoxPawnScoreBlack(game);

        paneBlack.add(boxPseudoB, 0, 0);
        paneBlack.add(boxPawnScoreBlack, 1, 0);
    }

    /**
     * Creates and sets a gridpane. Here used for the score gridpane of a 
     * specific player. This gridpane contains the pseudo, a colored pawn 
     * corresponding with the color of the player and the score.
     * 
     * @return the new set gridpane.
     */
    private GridPane makeScoreGrid(int hGap, int minWidth, String id, 
                                   Insets insets) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(insets);
        gridPane.setHgap(hGap);
        gridPane.setMinWidth(minWidth);
        gridPane.setId(id);

        return gridPane;
    }

    /**
     * Creates and sets a box. Here used for the pseudo box where is displayed 
     * the pseudo of the player corresponding.
     * 
     * @param pseudo the pseudo of the player.
     * @param minWidth the minimal width of the box.
     * @return the new set box.
     */
    private VBox makeBoxPseudo(Label pseudo, int minWidth) {
        VBox boxPseudo = new VBox();
        boxPseudo.getChildren().add(pseudo);
        boxPseudo.setMinWidth(minWidth);

        return boxPseudo;
    }

    /**
     * Makes the box that will contains the pawn and the score of a specific
     * player (here black).
     * 
     * @param game the current session of Othello.
     */
    private void makeBoxPawnScoreBlack(OthelloImpl game) {
        pawnB = new Pawn(GameColor.BLACK);
        scoreB = new Label();
        scoreB.setText(String.valueOf(game.getScoreBlack()));
        boxPawnScoreBlack = makeBoxPawnScore(pawnB, scoreB, 30);
    }

    /**
     * Creates, set a box that will contains a pawn and a score. Add a pawn and
     * a score in this box.
     * 
     * @param pawn the pawn to place in the box.
     * @param score the score to place in the box.
     * @param spacing the spacing between the pawn and the score.
     * @return the new box.
     */
    private HBox makeBoxPawnScore(Pawn pawn, Label score, int spacing) {
        HBox boxPawnScore = new HBox();
        boxPawnScore.setSpacing(spacing);
        boxPawnScore.getChildren().addAll(pawn, score);
        
        return boxPawnScore;
    }

    /**
     * Makes the zone for the informations of the white player.
     * 
     * @param game the current session of Othello.
     */    
    private void makeWhiteScoreZone(OthelloImpl game) {
        paneWhite = makeScoreGrid(20, 280, "paneScore", 
                                  new Insets(10, 10, 10, 10));
        pseudoW = new Label();
        boxPseudoW = makeBoxPseudo(pseudoW, 120);
        makeBoxPawnScoreWhite(game);

        paneWhite.add(boxPseudoW, 0, 0);
        paneWhite.add(boxPawnScoreWhite, 1, 0);
    }

    /**
     * Makes the box that will contains the pawn and the score of a specific
     * player (here white).
     * 
     * @param game the current session of Othello.
     */    
    private void makeBoxPawnScoreWhite(OthelloImpl game) {
        pawnW = new Pawn(GameColor.WHITE);
        scoreW = new Label();
        scoreW.setText(String.valueOf(game.getScoreWhite()));
        
        boxPawnScoreWhite = makeBoxPawnScore(pawnW, scoreW, 30);
    }

    /**
     * Set the ID's of the score's and pseudo's labels.
     */
    private void setIDLabels() {
        scoreB.setId("score");
        scoreW.setId("score");
        pseudoB.setId("pseudo");
        pseudoW.setId("pseudo");
    }

    /**
     * Sets the background colors of the two informations boxes.
     * The colors changes when the current player change. It allows the players
     * to determine who is the current player.
     */
    private void setBackgroundColors() {
        if (this.currentColor == GameColor.BLACK) {
            paneBlack.setStyle("-fx-background-color: green; -fx-opacity: 0.7");
            paneWhite.setStyle("-fx-background-color: grey; -fx-opacity: 0.7;");
        } else {
            paneBlack.setStyle("-fx-background-color: grey; -fx-opacity: 0.7;");
            paneWhite.setStyle("-fx-background-color: green; -fx-opacity: 0.7");
        }
    }

    /**
     * Sets the pseudo of the black and white players.
     * 
     * @param pseudoBlack the pseudo of the black player.
     * @param pseudoWhite the pseudo of the white player.
     */
    void setPseudos(String pseudoBlack, String pseudoWhite) {
        pseudoB.setText(pseudoBlack);
        pseudoW.setText(pseudoWhite);
    }

    /**
     * Refresh the scores and the current player.
     * 
     * @param scoreBlack the new score of the black player.
     * @param scoreWhite the new score of the white player.
     * @param currentColor the color of the new current player. 
     */
    void refresh(int scoreBlack, int scoreWhite, GameColor currentColor) {
        scoreB.setText(String.valueOf(scoreBlack));
        scoreW.setText(String.valueOf(scoreWhite));
        this.currentColor = currentColor;
        setBackgroundColors();
    }    
}
