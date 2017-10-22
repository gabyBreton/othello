package projet.othello.breton.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods to create and manipulate a game board.
 *
 * @author Gabriel Breton - 43397
 */
class Board {

    private final Color[][] board;
    private final int rows;
    private final int columns;
    private final int cptPawnsOnBoard;

    /**
     * Creates a new game board.
     *
     * @param rows the number of rows of the board.
     * @param columns the number of columns of the board.
     */
    Board(int rows, int columns) { // ROWS AND COLUMS SHOULD BE ODD NUMBERS, 
        this.rows = rows;          // WHERE VERIFY IT ?
        this.columns = columns;
        board = new Color[rows][columns];
        initBoardCells();
        placeFirstFourPawns();
        cptPawnsOnBoard = 4;
    }

    /**
     * Initialize all the values of the board cells to null.
     */
    private void initBoardCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = null;
            }
        }
    }

    /**
     * Places the first four pawns of the game. These fours pawns are two black
     * and two white. They are positioned around the center of the board.
     */
    private void placeFirstFourPawns() {
        board[(rows / 2) - 1][(columns / 2) - 1] = Color.BLACK;
        board[(rows / 2) - 1][columns / 2] = Color.WHITE;
        board[rows / 2][(columns / 2) - 1] = Color.WHITE;
        board[rows / 2][columns / 2] = Color.BLACK;
    }

    /**
     * Evaluates if the board is full or not.
     *
     * @return true if the board is full of pawns, or else false.
     */
    boolean isFull() {
        return cptPawnsOnBoard == rows * columns;
    }

    /**
     * Evaluates if a board cell is free or not.
     *
     * @param pos the position to verify.
     * @return true if the cell is free, or else false.
     */
    boolean isFree(Positions pos) {
        return board[pos.getROW()][pos.getCOLUMN()] == null;
    }

    /**
     * Places a pawn on a cell of the board.
     *
     * @param x the position on the x axis.
     * @param y the position on the y axis.
     * @param color the color of the pawn.
     */
    void placePawn(int x, int y, Color color) {
        board[x][y] = color;
    }

    /**
     * Gives a list with all the positions of the adjacents cells of a given
     * position.
     *
     * @param position the position to find the adjacents.
     * @return the positions of the adjacents cells of a position.
     */
    List<Positions> getAdjacents(Positions position) {
        List<Positions> listAdjacents = new ArrayList<>();
        Positions aPos;

        if (position.getCOLUMN() > 0) {
            aPos = new Positions(position.getROW(), position.getCOLUMN() - 1);
            listAdjacents.add(aPos);
        }

        if (position.getROW() > 0) {
            aPos = new Positions(position.getROW() - 1, position.getCOLUMN());
            listAdjacents.add(aPos);
        }

        if (position.getCOLUMN() < board.length) {
            aPos = new Positions(position.getROW(), position.getCOLUMN() + 1);
            listAdjacents.add(aPos);
        }

        if (position.getROW() < board.length) {
            aPos = new Positions(position.getROW() + 1, position.getCOLUMN());
            listAdjacents.add(aPos);
        }

        return listAdjacents;
    }

    /**
     * Gives the color of a cell on the board.
     *
     * @param pos the position of the cell to get the color.
     * @return the color of the cell.
     */
    Color getColor(int x, int y) {
        return board[x][y];
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

//    public static void main(String[] args) {
//        Board board = new Board(10, 10);
//        Color color;
//
//        for (int i = 0; i < board.getRows(); i++) {
//
//            for (int j = 0; j < board.getColumns(); j++) {
//
//                color = board.getColor(i, j);
//                if (null == color) {
//                    System.out.print(" . ");
//                } else {
//                    switch (color) {
//                        case BLACK:
//                            System.out.print(" B ");
//                            break;
//                        default:
//                            System.out.print(" w ");
//                            break;
//                    }
//                }
//            }
//
//            System.out.println("");
//        }
//    }
}
