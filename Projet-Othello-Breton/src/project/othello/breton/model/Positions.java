package project.othello.breton.model;

/**
 * This class provides methods to create positions.
 *
 * @author Gabriel Breton - 43397
 */
class Positions {

    private final int row;
    private final int col;

    /**
     * Creates a new position.
     *
     * @param row the number of the row.
     * @param col the number of the col.
     */
    Positions(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Gives the number of the row.
     *
     * @return the number of the row.
     */
    int getRow() {
        return row;
    }

    /**
     * Gives the number of the column.
     *
     * @return the number of the column.
     */
    int getCol() {
        return col;
    }
}
