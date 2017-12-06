package project.othello.breton.model;

/**
 * Enumeration of colors that are needed to distinguish players and pawns.
 *
 * @author Gabriel Breton - 43397
 */
public enum GameColor {
    
    /**
     * The color of the cell for the player black.
     */
    BLACK, 
    
    /**
     * The color of the cell for the player white.
     */
    WHITE, 
    
    /**
     * The color of the cell when a player can place a pawn on it.
     */
    GREY,
    
    /**
     * The color of a cell that contains a wall.
     */
    RED;
}
