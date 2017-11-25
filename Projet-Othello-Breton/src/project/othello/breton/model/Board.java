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
    private int counterWallsOnBoard;

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
        counterWallsOnBoard = 0;
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
     * @param row the number of the row.
     * @param col the number of the col.
     * @return true if the cell is free, or else false.
     */
    boolean isFree(int row, int col) {
        return (board[row][col] == null)
                || (board[row][col] == PlayerColor.GREY);
    }

    /**
     * Places a pawn on a cell of the board.
     *
     * @param row the number of the row.
     * @param col //TODO !!!!!!!!!!!!!!!
     * @param color the color of the pawn.
     */
    void placePawn(int row, int col, PlayerColor color) {
        board[row][col] = color;
    }

    /**
     * Gives the color of a cell on the board.
     *
     * //@TODO !!!!!!!!!!!!!
     *
     * @return the color of the cell.
     */
    PlayerColor getColor(int row, int col) {
        PlayerColor colorDebug = board[row][col];
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
     * @param row TODO
     * @param col TODO
     * @param color the color to set.
     */
    void setColor(int row, int col, PlayerColor color) {
        board[row][col] = color;
    }

    /**
     * Verifies if a position is on the game board.
     *
     * @param row the position on the x axis.
     * @param col the position on the y axis.
     * @return true if the position is on the board, or else false.
     */
    boolean isOnBoard(int row, int col) {
        boolean onBoard = ((row >= 0) && (row <= rows - 1)
                && (col >= 0) && (col <= columns - 1));
        return onBoard;
    }

    /**
     * Increments the counter of pawns on the board.
     */
    void incCounterPawnsOnBoard() {
        counterPawnsOnBoard += 1;
    }

    /**
     * Gives the number of wall on the board.
     *
     * @return the number of wall on the board.
     */
    int getCounterWallsOnBoard() {
        return counterWallsOnBoard;
    }

    /**
     * Increments the counter of walls on the board.
     */
    void incCounterWallsOnBoard() {
        counterWallsOnBoard += 1;
    }

}
