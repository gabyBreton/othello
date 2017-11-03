package project.othello.breton.viewFx;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import project.othello.breton.model.PlayerColor;
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
    private Tile[][] tilesArray;
    
    BoardPane(OthelloImpl game) {
        tilesArray = new Tile[width + 1][height + 1];
        board = new GridPane();

        board.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        board.setPrefSize(675, 675);
        addTiles();
        placeFirstFourPawns();
    }

    private void addTiles() {
        for (int i = 0; i < width + 1; i++) {
            for (int j = 0; j < height + 1; j++) {
                Tile tile = new Tile(i, j);
                tilesArray[j][i] = tile;
                tile.setTranslateX(j * 75);
                tile.setTranslateY(i * 75);
                initSideNumbering(i, j, tile);
                board.getChildren().add(tile);
            }
        }
    }

    private void placeFirstFourPawns() {
        placeAPawn(PlayerColor.WHITE, 4, 4);
        placeAPawn(PlayerColor.BLACK, 4, 5);
        placeAPawn(PlayerColor.BLACK, 5, 4);
        placeAPawn(PlayerColor.WHITE, 5, 5);
    }

    void placeAPawn(PlayerColor color, int x, int y) {
        Tile tile;
        tile = tilesArray[x][y];
        tile.getChildren().add(new Pawn(color));
    }

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
