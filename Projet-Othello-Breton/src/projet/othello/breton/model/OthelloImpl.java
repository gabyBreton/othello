package projet.othello.breton.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the facade of Othello.
 *
 * @author Gabriel Breton - 43397
 */
public class OthelloImpl implements Othello {

    private final Board board;
    private final Players playerB;
    private final Players playerW;
    private final Players currentPlayer;
    private Color currentColor;

    /**
     * Creates a new othello game.
     *
     * @param rows the number of lines.
     * @param columns the number of columns.
     */
    public OthelloImpl(int rows, int columns) {
        board = new Board(rows, columns);
        playerB = new Players(Color.BLACK);
        playerW = new Players(Color.WHITE);
        currentPlayer = playerB;
        currentColor = Color.BLACK;
    }

    @Override
    public boolean isOver() {
        return board.isFull();
        //return board.isFull() && stillValidMoves() ;
    }

    @Override
    public List<Players> getPlayers() {
        List<Players> listPlayers = new ArrayList<>();

        listPlayers.add(playerB);
        listPlayers.add(playerW);

        return listPlayers;
    }

    @Override
    public Color getCurrentColor() {
        return currentColor;
    }

    @Override
    public int getHeight() {
        return board.getRows();
    }

    @Override
    public int getWidht() {
        return board.getColumns();
    }

    @Override
    public Color getColor(int x, int y) {
        return board.getColor(x, y);
    }

    @Override
    public void play(int x, int y) {
        List<Positions> pawnsToFlip = new ArrayList<>();
        boolean validMove;

        validMove = false;

        if (isValidPosition(x, y)) {
            pawnsToFlip = getListPositionsToFlip(x, y, pawnsToFlip);
            validMove = pawnsToFlip.size() > 0;
        }

        if (validMove) {
            placeFlipAndSetScore(pawnsToFlip, x, y);
            changeCurrentPlayer();
        }
    }

    /**
     * Flip the pawns and set the new score of the players.
     *
     * @param pawnsToFlip the list of positions of the pawns to flip.
     * @param x the position where to place the new pawn on the x axis.
     * @param y the position where to place the new pawn on the y axis.
     */
    private void placeFlipAndSetScore(List<Positions> pawnsToFlip, int x, int y) {
        int nbFlippedPawns;

        flipPawns(pawnsToFlip);
        board.setColor(x, y, currentColor);
        board.incCounterPawnsOnBoard();

        nbFlippedPawns = pawnsToFlip.size();
        setPlayersScores(nbFlippedPawns);
    }

    /**
     * Actualize the new score of the players.
     *
     * @param nbFlippedPawns the number of pawns that have been flipped.
     */
    private void setPlayersScores(int nbFlippedPawns) {
        if (currentColor == Color.BLACK) {
            playerB.addPointsToScore(nbFlippedPawns + 1);
            playerW.addPointsToScore(-nbFlippedPawns);
        } else {
            playerW.addPointsToScore(nbFlippedPawns + 1);
            playerB.addPointsToScore(-nbFlippedPawns);
        }
    }

    /**
     * Get a list of all the positions where flip the color.
     *
     * @param x the position from where starting to evaluate, on the x axis.
     * @param y the position from where starting to evaluate, on the y axis.
     * @param pawnsToFlip the list of positions where flip the pawns.
     * @return a list of positions to flip. This list can be returned empty.
     */
    private List<Positions> getListPositionsToFlip(int x, int y,
            List<Positions> pawnsToFlip) {
        for (Directions direction : Directions.values()) {
            int xActual = x;
            int yActual = y;

            xActual += direction.getxAxisMov();
            yActual += direction.getyAxisMov();

            if (isOnBoardAndOppositeColor(xActual, yActual)) {
                xActual += direction.getxAxisMov();
                yActual += direction.getyAxisMov();

                if (!board.isOnBoard(xActual, yActual)) {
                    continue;
                }

                moveForwardLine(xActual, yActual, direction);

                if (!board.isOnBoard(xActual, yActual)) {
                    continue;
                }

                if (board.getColor(xActual, yActual) == currentColor) {
                    while (true) {
                        xActual -= direction.getxAxisMov();
                        yActual -= direction.getyAxisMov();
                        if ((xActual == x) && (yActual == y)) {
                            break;
                        }
                        pawnsToFlip.add(new Positions(xActual, yActual));
                    }
                }
            }
        }
        return pawnsToFlip;
    }

    /**
     * Move forward a line until while the new position is the color of the
     * other player.
     *
     * @param xActual the current positions being verified, on x axis.
     * @param yActual the current positions being verified, on y axis.
     * @param direction the direction where to move.
     */
    private void moveForwardLine(int xActual, int yActual, Directions direction) {
        Color otherColor = getOtherPlayerColor();

        while (board.getColor(xActual, yActual) == otherColor) {
            xActual += direction.getxAxisMov();
            yActual += direction.getyAxisMov();
            if (!board.isOnBoard(xActual, yActual)) {
                break;
            }
        }
    }

    /**
     * Verifies if a position is on the board and the cell at this position
     * contains a pawns of the opposite player.
     *
     * @param xActual the current positions being verified, on x axis.
     * @param yActual the current positions being verified, on y axis.
     * @return true if the position is on the board and the cell at this
     * position contains a pawns of the opposite player, or else false.
     */
    private boolean isOnBoardAndOppositeColor(int xActual, int yActual) {
        Color otherColor = getOtherPlayerColor();

        return board.isOnBoard(xActual, yActual)
                && (board.getColor(xActual, yActual) == otherColor);
    }

    /**
     * Get the color of the non current player.
     *
     * @return the color of the non current player.
     */
    private Color getOtherPlayerColor() {
        Color otherColor;
        if (currentColor == Color.BLACK) {
            otherColor = Color.WHITE;
        } else {
            otherColor = Color.BLACK;
        }
        return otherColor;
    }

    /**
     * Verifies if a position is valid as it on the game board.
     *
     * @param x the position on the x axis.
     * @param y the position on the y axis.
     * @return true if the position is on the board, or else false.
     */
    private boolean isValidPosition(int x, int y) {
        return (board.isFree(x, y)) && (board.isOnBoard(x, y));
    }

    /**
     * Flip the color of a list of pawns.
     *
     * @param pawnsToFlip the list of positions that represents the pawns to
     * flip.
     */
    private void flipPawns(List<Positions> pawnsToFlip) {
        int x;
        int y;

        for (Positions aPosition : pawnsToFlip) {
            x = aPosition.getROW();
            y = aPosition.getCOLUMN();

            board.setColor(x, y, currentColor);
        }
    }

    private boolean stillValidMoves() {
        List<Positions> positionsToTest = new ArrayList<>();
        boolean validMove;

        positionsToTest = getListEmptyPositions();
        for (Positions aPositionToTest : positionsToTest) {
            
            pawnsToFlip = getListPositionsToFlip(x, y, pawnsToFlip);
            validMove = pawnsToFlip.size() > 0;
        }

    }

    private List<Positions> getListEmptyPositions() {
        List<Positions> emptyPositionsToTest = new ArrayList<>();
        Color aColor;

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                aColor = board.getColor(i, j);
                if (aColor == null) {
                    emptyPositionsToTest.add(new Positions(i, j));
                }
            }
        }

        return emptyPositionsToTest;
    }

    private void changeCurrentPlayer() {
        if(!isOver()) {
            if (currentColor == Color.BLACK) {
                currentColor = Color.WHITE;
            } else {
                currentColor = Color.BLACK;
            }
        }
    }
    
//    
//    public static void main(String[] args) {
//        OthelloImpl game = new OthelloImpl(8, 8);
//        Color colorCell;
//
//        System.out.println("Score B: " + game.getPlayers().get(0).getScore());
//        System.out.println("Score W: " + game.getPlayers().get(1).getScore());
//
//        System.out.println("   0  1  2  3  4  5  6  7");
//        for (int i = 0; i < game.getWidht(); i++) {
//            System.out.print(i + " ");
//            for (int j = 0; j < game.getHeight(); j++) {
//                colorCell = game.getColor(i, j);
//                if (colorCell == null) {
//                    System.out.print(" - ");
//                } else if (colorCell == Color.BLACK) {
//                    System.out.print(" B ");
//                } else {
//                    System.out.print(" W ");
//                }
//            }
//            System.out.println("");
//        }
//
//        game.play(3, 2);
//
//        System.out.println("--------------------------");
//        System.out.println("");
//
//        System.out.println("Score B: " + game.getPlayers().get(0).getScore());
//        System.out.println("Score W: " + game.getPlayers().get(1).getScore());
//
//        System.out.println("   0  1  2  3  4  5  6  7");
//        for (int i = 0; i < game.getWidht(); i++) {
//            System.out.print(i + " ");
//            for (int j = 0; j < game.getHeight(); j++) {
//                colorCell = game.getColor(i, j);
//                if (colorCell == null) {
//                    System.out.print(" - ");
//                } else if (colorCell == Color.BLACK) {
//                    System.out.print(" B ");
//                } else {
//                    System.out.print(" W ");
//                }
//            }
//            System.out.println("");
//        }
//
//        game.play(4, 5);
//        System.out.println("--------------------------");
//        System.out.println("");
//
//        System.out.println("Score B: " + game.getPlayers().get(0).getScore());
//        System.out.println("Score W: " + game.getPlayers().get(1).getScore());
//
//        System.out.println("   0  1  2  3  4  5  6  7");
//        for (int i = 0; i < game.getWidht(); i++) {
//            System.out.print(i + " ");
//            for (int j = 0; j < game.getHeight(); j++) {
//                colorCell = game.getColor(i, j);
//                if (colorCell == null) {
//                    System.out.print(" - ");
//                } else if (colorCell == Color.BLACK) {
//                    System.out.print(" B ");
//                } else {
//                    System.out.print(" W ");
//                }
//            }
//            System.out.println("");
//        }
//    }

}
