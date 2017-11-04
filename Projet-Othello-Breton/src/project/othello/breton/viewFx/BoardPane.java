package project.othello.breton.viewFx;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import project.othello.breton.model.PlayerColor;
import project.othello.breton.model.OthelloImpl;

/**
 * This class creates a board with the four start pawns.
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
    private final Tile[][] tilesArray; //Used to place a pawn by identifying
                                       //the specific tile where to place it.

    /**
     * Creates a new Othello board, with the first four pawns and the sides
     * numbering.
     *
     * @param game the current game of Othello.
     */
    BoardPane(OthelloImpl game) {
        tilesArray = new Tile[width + 1][height + 1];
        board = new GridPane();

        board.getStylesheets().addAll(
                     this.getClass().getResource("style.css").toExternalForm());
        board.setPrefSize(675, 675);
        addTiles(game);
    }

    /**
     * Creates and add all the tiles of the board.
     */
    private void addTiles(OthelloImpl game) {
        for (int i = 0; i < width + 1; i++) {
            for (int j = 0; j < height + 1; j++) {
                Tile tile = new Tile(i, j);
                tilesArray[j][i] = tile;
                tile.setTranslateX(j * 75);
                tile.setTranslateY(i * 75);
                createSideNumbering(i, j, tile);
                board.getChildren().add(tile);
                verifyIfHaveToPlacePawn(game, j, i);
            }
        }
    }

    /**
     * Verifies if a pawn have to be placed on the board while creating the 
     * board.
     * 
     * @param game the current session of Othello.
     * @param j the number of the row.
     * @param i the number of the column.
     */
    private void verifyIfHaveToPlacePawn(OthelloImpl game, int j, int i) {
        if ((i < height - 1) && (i < width - 1)
             && (j < height - 1) && (j < width - 1)) {
            
            if ((i > 0) && (j > 0)) {
                
                if ((game.getColor(j - 1, i - 1) == PlayerColor.BLACK)
                     || (game.getColor(j - 1, i - 1) == PlayerColor.WHITE)) {
                    
                    placeAPawn(game.getColor(j - 1, i - 1), j, i);
                }
            }
        }
    }

    /**
     * Permits to place a pawn on the board.
     *
     * @param color the color of the pawn.
     * @param x the number of the row.
     * @param y the number of the column.
     */
    void placeAPawn(PlayerColor color, int x, int y) {
        Tile tile;
        tile = tilesArray[x][y];
        tile.getChildren().add(new Pawn(color));
    }

    /**
     * Creates the sides numbering, with letters and numbers.
     *
     * @param i the number of the line.
     * @param j the number of the column.
     * @param tile the tile where place the numbering.
     */
    private void createSideNumbering(int i, int j, Tile tile) {
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

    /**
     * Gives the game board.
     *
     * @return the game board.
     */
    GridPane getBoard() {
        return board;
    }
}
