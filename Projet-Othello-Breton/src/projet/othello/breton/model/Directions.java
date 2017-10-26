package projet.othello.breton.model;

/**
 * This class provides directions and their movements values on the board.
 * @author Gabriel Breton - 43397
 */
public enum Directions {
    NO (-1, 0),
    NE (-1, 1),
    EA (0, 1),
    SE (1, 1), 
    SO (1, 0),
    SW (1, -1),
    WE (0, -1),
    NW (-1, -1);
    
    private int xAxisMov;
    private int yAxisMov;

    private Directions(int xAxisMov, int yAxisMov) {
        this.xAxisMov = xAxisMov;
        this.yAxisMov = yAxisMov;
    }

    public int getxAxisMov() {
        return xAxisMov;
    }

    public int getyAxisMov() {
        return yAxisMov;
    }
}
