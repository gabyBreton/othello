package project.othello.breton.viewFx;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Gabriel Breton - 43397
 */
public class BoardPane {
    private final GridPane board;
    private final int width = 8;
    private final int height = 8;
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";;
    private Label rowNumber;
    private Label columnLetter;

    BoardPane() {
        board = new GridPane();
        board.setPrefSize(600, 600);
        
        for (int i = 0; i < width + 1; i++) {
            for (int j = 0; j < height + 1; j++) {
                Tile tile = new Tile(i, j);
                tile.setTranslateX(j * 75);
                tile.setTranslateY(i * 75);
                initSideNumbering(i, j, tile);
                board.getChildren().add(tile);                
            }
        }
    }

    private void initSideNumbering(int i, int j, Tile tile) {
        String style = "-fx-font-size: 18px;\n"
                        + "-fx-font-weight: bold;\n";
        
        if ((i != 0) && (j == 0)) {
            rowNumber = new Label();
            rowNumber.setText(String.valueOf(i));
            rowNumber.setStyle(style);
            tile.getChildren().add(rowNumber);
        } else if ((i == 0) && (j != 0)) {
            columnLetter = new Label();
            columnLetter.setText("" + alphabet.charAt(j - 1));
            columnLetter.setStyle(style);
            tile.getChildren().add(columnLetter);
        }
    }

    GridPane getBoard() {
        return board;
    }

    private class Tile extends StackPane {

        private Tile(int i, int j) {
            Rectangle border = new Rectangle(75, 75);

            if ((i == 0) || (j == 0)) {
                border.setFill(null);
                border.setStroke(null);
            } else {
                border.setFill(Color.GREEN);
                border.setStroke(Color.BLACK);
            }

            setAlignment(Pos.CENTER);
            getChildren().addAll(border);
        }
    }

}
