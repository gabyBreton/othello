package project.othello.breton.model;

/**
 * This class provides methods to create positions.
 *
 * @author Gabriel Breton - 43397
 */
class Positions {

    private final int row;
    private final int column;

    /**
     * Creates a new position.
     *
     * @param row the position on the row axis.
     * @param column the position on the column axis.
     */
    Positions(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Gives the position on the row axis.
     *
     * @return the position on the row axis.
     */
    int getRow() {
        return row;
    }

    /**
     * Gives the position on the row axis.
     *
     * @return the position on the row axis.
     */
    int getColumn() {
        return column;
    }
}
