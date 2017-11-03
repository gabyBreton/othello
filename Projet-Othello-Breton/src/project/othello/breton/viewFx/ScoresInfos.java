package project.othello.breton.viewFx;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import project.othello.breton.model.OthelloImpl;
import project.othello.breton.model.PlayerColor;

/**
 *
 * @author Gabriel Breton - 43397
 */
public class ScoresInfos {

    private GridPane sidePane;
    private Label scoreB;
    private Label scoreW;
    private Label scores;
    private Pawn black;
    private Pawn white;
    
    ScoresInfos(OthelloImpl game) {
        initAndSet();
        addScore(game);
        sidePane.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
    }

    private void initAndSet() {
        sidePane = new GridPane();
        sidePane.setPadding(new Insets(75, 50, 0, 50));
        sidePane.setHgap(10);
        sidePane.setVgap(15);
    }

    private void addScore(OthelloImpl game) {
        scores = new Label("Scores");
        scores.setUnderline(true);
        black = new Pawn(PlayerColor.BLACK);
        white = new Pawn(PlayerColor.WHITE);
        scoreB = new Label();
        scoreB.setText(String.valueOf(game.getPlayers().get(0).getScore()));
        scoreW = new Label();
        scoreW.setText(String.valueOf(game.getPlayers().get(1).getScore()));
        setIDLabels();
        addScoresToPane();

    }

    private void setIDLabels() {
        scores.setId("scores");
        scoreB.setId("scoreB");
        scoreW.setId("scoreW");
    }

    private void addScoresToPane() {
        sidePane.add(scores, 0, 0);
        sidePane.add(black, 0, 1);
        sidePane.add(scoreB, 1, 1);
        sidePane.add(white, 0, 2);
        sidePane.add(scoreW, 1, 2);
    }
    
    GridPane getSidePane() {
        return sidePane;
    }
}
