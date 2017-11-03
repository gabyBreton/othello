package project.othello.breton.viewFx;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import project.othello.breton.model.PlayerColor;
import javafx.scene.shape.Rectangle;
import project.othello.breton.model.OthelloImpl;

/**
 *
 * @author Gabriel Breton - 43397
 */
public class BoardPane {

    private final GridPane board;
    private final int width = 8;
    private final int height = 8;
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private Label rowNumber;
    private Label columnLetter;

    BoardPane(OthelloImpl game) {
        board = new GridPane();
        board.setPrefSize(675, 675);

        for (int i = 0; i < width + 1; i++) {
            for (int j = 0; j < height + 1; j++) {
                Tile tile = new Tile(i, j);
                tile.setTranslateX(j * 75);
                tile.setTranslateY(i * 75);
                initSideNumbering(i, j, tile);
                board.getChildren().add(tile);
            }
        }
        board.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

    }

//    private PlayerColor getColorPawn(int i, int j, OthelloImpl game) {
//        PlayerColor pawnColor;
//        if ((i >= width) || (j >= height)) {
//            pawnColor = null;
//        } else {
//            pawnColor = game.getColor(j, i);
//        }
//        return pawnColor;
//    }
    private void initSideNumbering(int i, int j, Tile tile) {
        if ((i != 0) && (j == 0)) {
            rowNumber = new Label();
            rowNumber.setText(String.valueOf(i));
            rowNumber.setId("rowNumber");
            tile.getChildren().add(rowNumber);
        } else if ((i == 0) && (j != 0)) {
            columnLetter = new Label();
            columnLetter.setText("" + alphabet.charAt(j - 1));
            columnLetter.setId("columnLetter");
            tile.getChildren().add(columnLetter);
        }
    }

    GridPane getBoard() {
        return board;
    }
}
