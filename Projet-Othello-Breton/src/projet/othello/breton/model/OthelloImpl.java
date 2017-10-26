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

    public OthelloImpl(int rows, int columns) {
        board = new Board(rows, columns);
        playerB = new Players(Color.BLACK);
        playerW = new Players(Color.WHITE);
        currentPlayer = playerB;
    }

    @Override
    public boolean isOver() {
        return board.isFull();
    }

    @Override
    public List<Players> getPlayers() {
        List<Players> listPlayers = new ArrayList<>();

        listPlayers.add(playerB);
        listPlayers.add(playerW);

        return listPlayers;
    }

    @Override
    public Players getCurrentPlayer() {
        return currentPlayer;
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
        boolean validMove;
        validMove = false;
        
        if (isValidPosition(x, y)) {
            Color otherColor;
            List<Positions> pawnsToFlip = new ArrayList<>();

            board.setColor(x, y, currentPlayer.getColor()); //WHY DO WE NEED THIS ?
            otherColor = getOtherPlayerColor();
            pawnsToFlip = getListOfValidPositions(x, y, otherColor, pawnsToFlip);
            board.setColor(x, y, null);

            validMove = pawnsToFlip.size() > 0;
        }
        
        if (validMove) {
            returnPawns(pawnsToFlip);
        }
    }
//    @Override
//    public void play(int x, int y) {
//
//        if (isValidPosition(x, y)) return false;
//
//        board.setColor(x, y, currentColor);
//
//        if (currentColor = Color.BLACK) {
//            otherColor = Color.WHITE;
//        } else {
//            otherColor = Color.BLACK;
//        }
//
//        List<Positions> pawnsToFlip = new ArrayList<>();
//
//        //[1, 0], [1, -1], [0, -1], [-1, -1], [-1, 0], [-1, 1]];
//        //int[][] directions={{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};
//        for (Directions direction : Directions.values()) {
//            int xActual = x;
//            int yActual = y;
//
//            xActual += direction.getxAxisMov();
//            yActual += direction.getyAxisMov();
//
//            if (board.isOnBoard(xActual, yActual)
//                    && board.getColor(xActual, yActual) == otherColor) {
//
//                xActual += direction.getxAxisMov();
//                yActual += direction.getyAxisMov();
//
//                if (!board.isOnBoard(xActual, yActual)) {
//                    continue;
//                }
//
//                while (board.getColor(xActual, yActual) == otherColor) {
//                    xActual += direction.getxAxisMov();
//                    yActual += direction.getyAxisMov();
//                    
//                    if (!board.isOnBoard(xActual, yActual)) {
//                        break;
//                    }
//                }
//                
//                if (!board.isOnBoard(xActual, yActual)) {
//                    continue;
//                }
//                
//                if (board.getColor(xActual, yActual) == currentColor) {
//                    while (true) {
//                        xActual -= direction.getxAxisMov();
//                        yActual -= direction.getyAxisMov();
//                        
//                        if ((xActual == x) && (yActual == y)) {
//                            break;
//                        }
//                        pawnsToFlip.add(new Positions(xActual, yActual));
//                    }
//                }
//            }
//        }
//        
//        board.setColor(x, y, null);
//        
//        if (pawnsToFlip.size() == 0) {
//            return false;
//        }
//        
//        return pawnsToFlip;
//    }

    private List<Positions> getListOfValidPositions(int x, int y, Color otherColor, List<Positions> pawnsToFlip) {
        for (Directions direction : Directions.values()) {
            int xActual = x;
            int yActual = y;

            actualizeCurrentPositions(xActual, yActual, direction);

            if (isOnBoardAndOppositeColor(xActual, yActual, otherColor)) {
                actualizeCurrentPositions(xActual, yActual, direction);

                if (!board.isOnBoard(xActual, yActual)) {
                    continue;
                }

                continueThroughLine(xActual, yActual, otherColor, direction);

                if (!board.isOnBoard(xActual, yActual)) {
                    continue;
                }

                if (board.getColor(xActual, yActual) == currentPlayer.getColor()) {
                    while (true) {
                        goBackStartPosition(xActual, yActual, direction);
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

    private void continueThroughLine(int xActual, int yActual, Color otherColor, Directions direction) {
        while (board.getColor(xActual, yActual) == otherColor) {
            actualizeCurrentPositions(xActual, yActual, direction);
            if (!board.isOnBoard(xActual, yActual)) {
                break;
            }
        }
    }

    private boolean isOnBoardAndOppositeColor(int xActual, int yActual, Color otherColor) {
        return board.isOnBoard(xActual, yActual)
                && board.getColor(xActual, yActual) == otherColor;
    }

    private Color getOtherPlayerColor() {
        Color otherColor;
        if (currentPlayer.getColor() == Color.BLACK) {
            otherColor = Color.WHITE;
        } else {
            otherColor = Color.BLACK;
        }
        return otherColor;
    }

    private boolean isValidPosition(int x, int y) {
        return (board.isFree(x, y)) && (board.isOnBoard(x, y));
    }

    private void actualizeCurrentPositions(int x, int y, Directions direction) {
        x += direction.getxAxisMov();
        y += direction.getyAxisMov();
    }

    private void goBackStartPosition(int x, int y, Directions direction) {
        x -= direction.getxAxisMov();
        y -= direction.getyAxisMov();
    }
}
