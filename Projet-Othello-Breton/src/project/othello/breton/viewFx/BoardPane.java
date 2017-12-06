package project.othello.breton.viewFx;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import project.othello.breton.model.GameColor;
import project.othello.breton.model.OthelloImpl;

/**
 * This class creates a board with the four start pawns.
 *
 * @author Gabriel Breton - 43397
 */
public class BoardPane extends GridPane {

    private final int rows = 8;
    private final int columns = 8;
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Label rowNumber;
    private Label columnLetter;
    private int cptWallOnBoard; 
    private final Tile[][] tilesArray; //Used to place a pawn by identifying
                                       //the specific tile where to place it.

    /**
     * Creates a new Othello board, with the first four pawns and the sides
     * numbering.
     *
     * @param game the current game of Othello.
     */
    BoardPane(OthelloImpl game) {
        super();
        setStyle();
        tilesArray = new Tile[rows + 1][columns + 1];
        cptWallOnBoard = game.getCounterWallsOnBoard();
        addTiles(game);
    }

    private void setStyle() {
        getStyleSheets();
        setPrefSize(675, 675);
    }

    private void getStyleSheets() {
        getStylesheets().addAll(
                this.getClass().getResource("style.css").toExternalForm());
    }

    /**
     * Creates and add all the tiles of the board.
     */
    private void addTiles(OthelloImpl game) {
        GameColor cellColor;
        for (int row = 0; row < rows + 1; row++) {

            for (int col = 0; col < columns + 1; col++) {
                cellColor = getCellColor(game, row, col);
                Tile tile = makeTile(col, row, cellColor, game);

                createSideNumbering(col, row, tile);

                getChildren().add(tile);
                verifyIfHaveToPlacePawn(game, row, col);
            }
        }
    }

    private Tile makeTile(int col, int row, GameColor cellColor, OthelloImpl game) {
        Tile tile = new Tile(col, row, cellColor, game);
        tilesArray[row][col] = tile;
        tile.setTranslateX(row * 75);
        tile.setTranslateY(col * 75);
        return tile;
    }

    /**
     * Verifies if a pawn have to be placed on the board while creating the
     * board.
     *
     * @param game the current session of Othello.
     * @param row the number of the row.
     * @param col the number of the column.
     */
    private void verifyIfHaveToPlacePawn(OthelloImpl game, int row, int col) {
        if ((col < columns - 1) && (col < rows - 1)
                && (row < columns - 1) && (row < rows - 1)) {

            if ((col > 0) && (row > 0)) {

                if ((game.getColor(row - 1, col - 1) == GameColor.BLACK)
                    || (game.getColor(row - 1, col - 1) == GameColor.WHITE)) {

                    placeAPawn(game.getColor(row - 1, col - 1), row, col);
                }
            }
        }
    }

    private GameColor getCellColor(OthelloImpl game, int row, int col) {
        GameColor cellColor;
        cellColor = null;

        if ((col < columns - 1) && (col < rows - 1)
                && (row < columns - 1) && (row < rows - 1)) {

            if ((col > 0) && (row > 0)) {
                cellColor = game.getColor(row - 1, col - 1);
            }
        }
        return cellColor;
    }

    /**
     * Permits to place a pawn on the board.
     *
     * @param color the color of the pawn.
     * @param row the number of the row.
     * @param col the number of the column.
     */
    void placeAPawn(GameColor color, int row, int col) {
        Tile tile;
        tile = tilesArray[row][col];
        tile.getChildren().add(new Pawn(color));
    }

    /**
     * Creates the sides numbering, with letters and numbers.
     *
     * @param col the number of the line.
     * @param row the number of the column.
     * @param tile the tile where place the numbering.
     */
    private void createSideNumbering(int row, int col, Tile tile) {
        if ((col != 0) && (row == 0)) {
            makeColumnsNumerotation(col);
            tile.getChildren().add(columnLetter);
        } else if ((col == 0) && (row != 0)) {
            makeRowsNumerotation(row);
            tile.getChildren().add(rowNumber);
        }
    }

    private void makeColumnsNumerotation(int col) {
        columnLetter = new Label();
        columnLetter.setText("" + alphabet.charAt(col - 1));
        columnLetter.setId("numerotation");
    }

    private void makeRowsNumerotation(int row) {
        rowNumber = new Label();
        rowNumber.setText(String.valueOf(row));
        rowNumber.setId("numerotation");
    }

    int getCounterWallsOnBoard() {
        return cptWallOnBoard;
    }

    void refresh(OthelloImpl game) {
        for (Tile[] tiles : tilesArray) {
            for (Tile tile : tiles) {
                tile.getChildren().clear();
            }
        }
        addTiles(game);
        cptWallOnBoard = game.getCounterWallsOnBoard();
    }
}
