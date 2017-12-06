package project.othello.breton.model;

import javafx.collections.ObservableList;

/**
 * Interface of the facade of Othello.
 *
 * @author Gabriel Breton - 43397
 */
interface Othello {

    /**
     * Verifies if the game is over.
     *
     * @return true if the game is over, or else false.
     */
    boolean isOver();

    /**
     * Gives the score of the player black.
     *
     * @return the score of the player black.
     */
    int getScoreBlack();

    /**
     * Gives the score of the player white.
     *
     * @return the score of the player white.
     */
    int getScoreWhite();

    /**
     * Gives the current player color.
     *
     * @return the current player color.
     */
    GameColor getCurrentColor();

    /**
     * Gives the height of the board.
     *
     * @return the height of the board.
     */
    int getHeight();

    /**
     * Gives the width of the board.
     *
     * @return the width of the board.
     */
    int getWidth();

    /**
     * Gives the color of cell at a position.
     *
     * @param row the number of the row.
     * @param col the number of the col.
     * @return the color of the cell, or null if the cell is free.
     */
    GameColor getColor(int row, int col);

    /**
     * Gives the history list of the actions made in the game.
     * 
     * @return the history list of the actions made in the game.
     */
    ObservableList<Action> getActionsHistory();
    
    /**
     * Place a pawn on a position on the game board.
     *
     * @param row the number of the row.
     * @param col the number of the column.
     */
    void play(int row, int col);

    /**
     * Set to 'null' all the cells that have been set as possible positions for 
     * a pawn placement.
     */
    void cleanLastPlayerPossibilities();

    /**
     * Sets all the cell possible for a pawn placement to 'GREY' to be 
     * recognized.
     */
    void setPossiblePositions();
    
    /**
     * Add a wall on the board, on an empty cell.
     *
     * @param row the number of the row.
     * @param col the number of the column.
     */
    void wall(int row, int col);
    
    /**
     * Gives the number of wall on the board.
     *
     * @return the number of wall on the board.
     */    
    int getCounterWallsOnBoard();
    
    /**
     * Gives the number of pawns on the board.
     *
     * @return the number of pawns on the board.
     */    
    int getCounterPawnsOnBoard();
    
    /**
     * To pass a turn.
     */
    void pass();
}
