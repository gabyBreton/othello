package projet.othello.breton.model;

import java.util.List;

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
     * Gives a list with all the players of the game.
     * 
     * @return a list with all the players of the game.
     */
    List<Players> getPlayers();
    
    /**
     * Gives the current player color.
     * 
     * @return the current player color.
     */
    Color getCurrentColor();
    
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
    int getWidht();
    
    /**
     * Gives the color of cell at a position.
     * 
     * @param x the position on the x axis.
     * @param y the position on the y axis.
     * @return the color of the cell, or null if the cell is free.
     */
    Color getColor(int x, int y);
    
    /**
     * Place a pawn on a position on the game board.
     * 
     * @param x the position on the x axis.
     * @param y the position on the y axis.
     */
    void play(int x, int y);
}
