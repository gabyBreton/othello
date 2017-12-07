package project.othello.breton.viewFx;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import project.othello.breton.model.OthelloImpl;
import project.othello.breton.model.GameColor;

/**
 * This classes manages the scores view.
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

    private GridPane paneBlack;
    private GridPane paneWhite;

    /**
     * The creates the pane with all the elements of the score.
     *
     * @param game the current session of Othello.
     */
    ScoresInfos(OthelloImpl game) {
        super();
        this.currentColor = game.getCurrentColor();
        setSpacing(60);

        createElements(game);
        getChildren().addAll(paneBlack, paneWhite);
        setIDLabels();
        addElementsToPane();
        setBackgroundColors();
        getStylesheets().addAll(this.getClass().getResource("style.css")
                .toExternalForm());
    }

    /**
     * Add the elements of the score.
     *
     * @param game the current session of Othello.
     */
    private void createElements(OthelloImpl game) {
        paneBlack = new GridPane();
        paneBlack.setPadding(new Insets(20, 20, 20, 20));
        paneBlack.setHgap(20);
        paneBlack.setId("paneScore");

        paneWhite = new GridPane();
        paneWhite.setPadding(new Insets(20, 20, 20, 20));
        paneWhite.setHgap(20);
        paneWhite.setId("paneScore");

        boxPawnScoreBlack = new HBox();
        boxPawnScoreWhite = new HBox();

        pseudoB = new Label();
        pseudoW = new Label();

        pawnB = new Pawn(GameColor.BLACK);
        pawnW = new Pawn(GameColor.WHITE);

        scoreB = new Label();
        scoreB.setText(String.valueOf(game.getScoreBlack()));

        scoreW = new Label();
        scoreW.setText(String.valueOf(game.getScoreWhite()));
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

    private void addElementsToPane() {
        boxPawnScoreBlack.setSpacing(30);
        boxPawnScoreWhite.setSpacing(30);

        boxPawnScoreBlack.getChildren().addAll(pawnB, scoreB);
        boxPawnScoreWhite.getChildren().addAll(pawnW, scoreW);

        paneBlack.add(pseudoB, 0, 0);
        paneBlack.add(boxPawnScoreBlack, 1, 0);

        paneWhite.add(pseudoW, 0, 0);
        paneWhite.add(boxPawnScoreWhite, 1, 0);
    }

    void refresh(int scoreBlack, int scoreWhite, GameColor currentColor) {
        scoreB.setText(String.valueOf(scoreBlack));
        scoreW.setText(String.valueOf(scoreWhite));
        this.currentColor = currentColor;
        setBackgroundColors();
    }

    private void setBackgroundColors() {
        if (this.currentColor == GameColor.BLACK) {
            paneBlack.setStyle("-fx-background-color: green; -fx-opacity: 0.5");
            paneWhite.setStyle("-fx-background-color: transparent;");
        } else {
            paneBlack.setStyle("-fx-background-color: transparent;");
            paneWhite.setStyle("-fx-background-color: green; -fx-opacity: 0.5");
        }
    }

    void setPseudos(String pseudoBlack, String pseudoWhite) {
        pseudoB.setText(pseudoBlack);
        pseudoW.setText(pseudoWhite);
    }
}
