package project.othello.breton.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods to create and manipulate a game board.
 *
 * @author Gabriel Breton - 43397
 */
class Board {

    private final PlayerColor[][] board;
    private final int rows;
    private final int columns;
    private int counterPawnsOnBoard;

    /**
     * Creates a new game board.
     *
     * @param rows the number of rows of the board.
     * @param columns the number of columns of the board.
     */
    Board(int rows, int columns) { // ROWS AND COLUMS SHOULD BE ODD NUMBERS, 
        this.rows = rows;          // WHERE VERIFY IT ?
        this.columns = columns;
        board = new PlayerColor[rows][columns];
        placeFirstFourPawns();
        counterPawnsOnBoard = 4;
    }

    /**
     * Places the first four pawns of the game. These fours pawns are two black
     * and two white. They are positioned around the center of the board.
     */
    private void placeFirstFourPawns() {
        board[(rows / 2) - 1][(columns / 2) - 1] = PlayerColor.WHITE;
        board[(rows / 2) - 1][columns / 2] = PlayerColor.BLACK;
        board[rows / 2][(columns / 2) - 1] = PlayerColor.BLACK;
        board[rows / 2][columns / 2] = PlayerColor.WHITE;
    }

    /**
     * Evaluates if the board is full or not.
     *
     * @return true if the board is full of pawns, or else false.
     */
    boolean isFull() {
        return counterPawnsOnBoard == rows * columns;
    }

    /**
     * Evaluates if a board cell is free or not.
     *
     * @param pos the position to verify.
     * @return true if the cell is free, or else false.
     */
    boolean isFree(int x, int y) {
        return (board[x][y] == null) || (board[x][y] == PlayerColor.GREY);
    }

    /**
     * Places a pawn on a cell of the board.
     *
     * @param x the position on the x axis.
     * @param y the position on the y axis.
     * @param color the color of the pawn.
     */
    void placePawn(int x, int y, PlayerColor color) {
        board[x][y] = color;
    }

    /**
     * Gives the color of a cell on the board.
     *
     * @param pos the position of the cell to get the color.
     * @return the color of the cell.
     */
    PlayerColor getColor(int x, int y) {
        PlayerColor colorDebug = board[x][y];
        return colorDebug;
    }

    /**
     * Gives the number of rows of the board.
     *
     * @return the number of rows of the board.
     */
    int getRows() {
        return rows;
    }

    /**
     * Gives the number of columns of the board.
     *
     * @return the number of columns of the board.
     */
    int getColumns() {
        return columns;
    }

    /**
     * Set the color of a position on the board.
     *
     * @param x the position on the x axis.
     * @param y the position on the y axis.
     * @param color the color to set.
     */
    void setColor(int x, int y, PlayerColor color) {
        board[x][y] = color;
    }

    /**
     * Verifies if a position is on the game board.
     *
     * @param x the position on the x axis.
     * @param y the position on the y axis.
     * @return true if the position is on the board, or else false.
     */
    boolean isOnBoard(int x, int y) {
        boolean onBoardDebug = ((x >= 0) && (x <= rows - 1)
                                && (y >= 0) && (y <= columns - 1));
        return onBoardDebug;
    }

    /**
     * Increments the counter of pawns on the board.
     */
    void incCounterPawnsOnBoard() {
        counterPawnsOnBoard += 1;
    }
}
