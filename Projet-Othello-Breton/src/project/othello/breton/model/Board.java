package project.othello.breton.model;

/**
 * This class provides methods to create and manipulate a game board.
 *
 * @author Gabriel Breton - 43397
 */
class Board {

    private final GameColor[][] board;
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
    Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        board = new GameColor[rows][columns];
        placeFirstFourPawns();
        counterPawnsOnBoard = 4;
        counterWallsOnBoard = 0;
    }

    /**
     * Places the first four pawns of the game. These fours pawns are two black
     * and two white. They are positioned around the center of the board.
     */
    private void placeFirstFourPawns() {
        board[(rows / 2) - 1][(columns / 2) - 1] = GameColor.WHITE;
        board[(rows / 2) - 1][columns / 2] = GameColor.BLACK;
        board[rows / 2][(columns / 2) - 1] = GameColor.BLACK;
        board[rows / 2][columns / 2] = GameColor.WHITE;
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
     * @param col the number of the column.
     * @return true if the cell is free, or else false.
     */
    boolean isFree(int row, int col) {
        return (board[row][col] == null)
                || (board[row][col] == GameColor.GREY);
    }

    /**
     * Gives the color of a cell on the board.
     *
     * @param row the number of the row.
     * @param col the number of the column.
     * @return the color of the cell.
     */
    GameColor getColor(int row, int col) {
        GameColor color = board[row][col];
        return color;
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
     * @param row the number of the row.
     * @param col the number of the column.
     * @param color the color of the pawn to place.
     */
    void setColor(int row, int col, GameColor color) {
        board[row][col] = color;
    }

    /**
     * Verifies if a position is on the game board.
     *
     * @param row the number of the row.
     * @param col the number of the column.
     * @return true if the position is on the board, or else false.
     */
    boolean isOnBoard(int row, int col) {
        return ((row >= 0) && (row <= rows - 1) 
                && (col >= 0) && (col <= columns - 1));
    }

    /**
     * Increments the counter of pawns on the board.
     */
    void incCounterPawnsOnBoard() {
        counterPawnsOnBoard += 1;
    }

    /**
     * Increments the counter of walls on the board.
     */
    void incCounterWallsOnBoard() {
        counterWallsOnBoard += 1;
    }    
    
    /**
     * Gives the number of pawns on the board.
     * 
     * @return the number of pawns on the board.
     */
    int getCounterPawnsOnBoard() {
        return counterPawnsOnBoard;
    }

    /**
     * Gives the number of walls on the board.
     *
     * @return the number of walls on the board.
     */
    int getCounterWallsOnBoard() {
        return counterWallsOnBoard;
    }
}
