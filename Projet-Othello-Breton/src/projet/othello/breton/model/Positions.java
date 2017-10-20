package projet.othello.breton.model;

/**
 * This class provides methods to create positions.
 *
 * @author Gabriel Breton - 43397
 */
class Positions {

    private final int ROW;
    private final int COLUMN;

    /**
     * Creates a new position.
     *
     * @param row the position on the row axis.
     * @param column the position on the column axis.
     */
    public Positions(int row, int column) {
        this.ROW = row;
        this.COLUMN = column;
    }

    /**
     * Gives the position on the row axis.
     *
     * @return the position on the row axis.
     */
    public int getROW() {
        return ROW;
    }

    /**
     * Gives the position on the row axis.
     *
     * @return the position on the row axis.
     */
    public int getCOLUMN() {
        return COLUMN;
    }
}
