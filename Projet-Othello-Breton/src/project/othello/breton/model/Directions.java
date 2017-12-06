package project.othello.breton.model;

/**
 * This class provides directions and their movements values on the board.
 * 
 * @author Gabriel Breton - 43397
 */
public enum Directions {
    
    /**
     * North.
     */
    NO (-1, 0),
    
    /**
     * North East.
     */
    NE (-1, 1),
    
    /**
     * East.
     */
    EA (0, 1),
    
    /**
     * South East.
     */
    SE (1, 1), 
    
    /**
     * South.
     */
    SO (1, 0),
    
    /**
     * South West.
     */
    SW (1, -1),
    
    /**
     * West.
     */
    WE (0, -1),
    
    /**
     * North West.
     */
    NW (-1, -1);
    
    private final int movOutOfRow;
    private final int movOutOfCol;

    /**
     * Creates a new direction.
     * 
     * @param movOutOfRow the move on the row axis of this direction.
     * @param movOutOfCol the move on the column axis of this direction.
     */
    private Directions(int movOutOfRow, int movOutOfCol) {
        this.movOutOfRow = movOutOfRow;
        this.movOutOfCol = movOutOfCol;
    }

    /**
     * Gives the direction of the row to go.
     * 
     * @return the direction of the row to go.
     */
    int getMovOutOfRow() {
        return movOutOfRow;
    }

    /**
     * Gives the direction of the column to go.
     * 
     * @return the direction of the column to go.
     */
    int getMovOutOfCol() {
        return movOutOfCol;
    }
}
