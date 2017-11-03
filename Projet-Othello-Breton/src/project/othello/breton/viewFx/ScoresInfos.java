package project.othello.breton.viewFx;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import project.othello.breton.model.OthelloImpl;

/**
 *
 * @author Gabriel Breton - 43397
 */
public class ScoresInfos {

    private GridPane sidePane;
    private Label scoreBlack;
    private Label scoreWhite;

    ScoresInfos(OthelloImpl game) {
        initAndSet();
        addScore(game);
    }

    private void initAndSet() {
        sidePane = new GridPane();
        sidePane.setPadding(new Insets(200));
        sidePane.setHgap(10);
        sidePane.setVgap(15);
    }

    private void addScore(OthelloImpl game) {
        Label scores = new Label("Scores");
        scores.setUnderline(true);
        Label nameB = new Label("Black:");
        Label nameW = new Label("White:");

        scoreBlack = new Label();
        scoreBlack.setText(String.valueOf(game.getPlayers().get(0).getScore()));
        scoreWhite = new Label();
        scoreWhite.setText(String.valueOf(game.getPlayers().get(1).getScore()));
        setStylesLabels(scores, nameB, nameW);
        addScoresToPane(scores, nameB, nameW);

    }

    private void setStylesLabels(Label scores, Label nameB, Label nameW) {
        String style = "-fx-font-size: 18px;\n"
                       + "-fx-font-weight: bold;\n";
        scores.setStyle(style);
        nameB.setStyle(style);
        nameW.setStyle(style);
        scoreBlack.setStyle(style);
        scoreWhite.setStyle(style);
    }

    private void addScoresToPane(Label scores, Label nameB, Label nameW) {
        sidePane.add(scores, 0, 0);
        sidePane.add(nameB, 0, 1);
        sidePane.add(scoreBlack, 1, 1);
        sidePane.add(nameW, 0, 2);
        sidePane.add(scoreWhite, 1, 2);
    }
    
    GridPane getSidePane() {
        return sidePane;
    }
}
