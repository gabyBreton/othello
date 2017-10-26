package projet.othello.breton.model;

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
    
    private final int xAxisMov;
    private final int yAxisMov;

    private Directions(int xAxisMov, int yAxisMov) {
        this.xAxisMov = xAxisMov;
        this.yAxisMov = yAxisMov;
    }

    /**
     * Gives the movement value on the x axis.
     * 
     * @return the movement value on the x axis.
     */
    public int getxAxisMov() {
        return xAxisMov;
    }

    /**
     * Gives the movement value on the y axis.
     * 
     * @return the movement value on the y axis.
     */
    public int getyAxisMov() {
        return yAxisMov;
    }
}
