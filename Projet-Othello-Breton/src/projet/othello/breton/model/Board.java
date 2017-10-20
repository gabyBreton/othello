package projet.othello.breton.model;

/**
 * This class provides methods to create and manipulate a game board.
 *
 * @author Gabriel Breton - 43397
 */
public class Board {

    private Color[][] board;
    private int rows;
    private int columns;

    /**
     * Creates a new game board.
     *
     * @param rows the number of rows of the board.
     * @param columns the number of columns of the board.
     */
    Board(int rows, int columns) { // ROWS AND COLUMS SHOULD BE ODD NUMBERS, 
        this.rows = rows;          // WHERE VERIFY IT ?
        this.columns = columns;
        board = new Color[rows][columns];
        addFirstFourPawns();
    }

    /**
     * Add the first four pawns of the game. These fours pawns are two black
     * and two white. They are positioned around the center of the board.
     */
    private void addFirstFourPawns() {
        board[(columns / 2) - 1] [(rows / 2) - 1] = Color.BLACK;
        board[columns / 2] [(rows / 2) - 1] = Color.WHITE;
        board[(columns / 2) - 1] [rows / 2] = Color.BLACK;
        board[columns / 2] [rows / 2] = Color.WHITE;
    }
}
