package project.othello.breton.model;

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
    private PlayerColor currentColor;

    /**
     * Creates a new othello game.
     *
     * @param rows the number of lines.
     * @param columns the number of columns.
     */
    public OthelloImpl(int rows, int columns) {
        board = new Board(rows, columns);
        playerB = new Players(PlayerColor.BLACK);
        playerW = new Players(PlayerColor.WHITE);
        currentColor = PlayerColor.BLACK;
    }

    @Override
    public boolean isOver() {
        return board.isFull() || !stillValidMoves() ;
    }

    @Override
    public List<Players> getPlayers() {
        List<Players> listPlayers = new ArrayList<>();

        listPlayers.add(playerB);
        listPlayers.add(playerW);

        return listPlayers;
    }

    @Override
    public PlayerColor getCurrentColor() {
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
    public PlayerColor getColor(int x, int y) {
        return board.getColor(x, y);
    }
    
    @Override
    public void cleanLastPlayerPossibilities() {
        for(int i = 0; i < getHeight(); i++) {
            for(int j = 0; j < getWidht(); j++) {
                if(board.getColor(i, j) == PlayerColor.GREY) {
                    board.setColor(i, j, null);
                }
            }
        }
    }
    
    @Override
    public void setPossiblePositions() {
        List<Positions> listPossiblePositions = new ArrayList<>();
        int x, y;
        
        listPossiblePositions = getValidMoves();
        
        for(Positions aPosition : listPossiblePositions) {
            x = aPosition.getROW();
            y = aPosition.getCOLUMN();
            board.setColor(x, y, PlayerColor.GREY);
        }
        
        if (listPossiblePositions.isEmpty()) {
            changeCurrentPlayer();
        }
    }
    
    @Override
    public void play(int x, int y) throws ArrayIndexOutOfBoundsException {
        List<Positions> pawnsToFlip = new ArrayList<>();
        boolean validMove;

        validMove = false;

        if (isValidPosition(x, y)) {
            pawnsToFlip = getListPositionsToFlip(x, y);
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
    private void placeFlipAndSetScore(List<Positions> pawnsToFlip, 
                                      int x, int y) {
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
        if (currentColor == PlayerColor.BLACK) {
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
     * @return a list of positions to flip. This list can be returned empty.
     */
    private List<Positions> getListPositionsToFlip(int x, int y) {
        List<Positions> pawnsToFlip = new ArrayList<>();
        
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
    private void moveForwardLine(int xActual, int yActual, 
                                 Directions direction) {
        PlayerColor otherColor = getOtherPlayerColor();

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
        PlayerColor otherColor = getOtherPlayerColor();

        return board.isOnBoard(xActual, yActual)
               && (board.getColor(xActual, yActual) == otherColor);
    }

    /**
     * Get the color of the non current player.
     *
     * @return the color of the non current player.
     */
    private PlayerColor getOtherPlayerColor() {
        PlayerColor otherColor;
        if (currentColor == PlayerColor.BLACK) {
            otherColor = PlayerColor.WHITE;
        } else {
            otherColor = PlayerColor.BLACK;
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
        int x, y;

        for (Positions aPosition : pawnsToFlip) {
            x = aPosition.getROW();
            y = aPosition.getCOLUMN();

            board.setColor(x, y, currentColor);
        }
    }

    /**
     * Search for all the possibles pawns placement on the board.
     * 
     * @return a list with all the valids moves.
     */
    private List<Positions> getValidMoves() {
        List<Positions> emptyPositions = new ArrayList<>();
        List<Positions> listValidMoves = new ArrayList<>();
        int x, y;

        emptyPositions = getListEmptyPositions();

        for (Positions aPositionToTest : emptyPositions) {
            x = aPositionToTest.getROW();
            y = aPositionToTest.getCOLUMN();

            if (getListPositionsToFlip(x, y).size() > 0) {
                listValidMoves.add(aPositionToTest);
            }
        }
       
        return listValidMoves;
    }

    /**
     * Search for all the empty cells of the board.
     * 
     * @return a list with all the empty positions.
     */
    private List<Positions> getListEmptyPositions() {
        List<Positions> emptyPositions = new ArrayList<>();
        PlayerColor aColor;

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                
                aColor = board.getColor(i, j);
                
                if (aColor == null || aColor == PlayerColor.GREY) {
                    emptyPositions.add(new Positions(i, j));
                }
            }
        }

        return emptyPositions;
    }

    /**
     * Changes the current player.
     */
    private void changeCurrentPlayer() {
            if (currentColor == PlayerColor.BLACK) {
                currentColor = PlayerColor.WHITE;
            } else {
                currentColor = PlayerColor.BLACK;
            }
    }
    
    /**
     * Verifies if there is still valid moves for both players.
     * 
     * @return true if there is still valids moves for both players on the 
     * board, or else false.
     */
    private boolean stillValidMoves() {
        boolean stillMoves;
        
        stillMoves = true;
        if (getValidMoves().isEmpty()) {
            changeCurrentPlayer();
            if (getValidMoves().isEmpty()) {
                stillMoves = false;
            }
            changeCurrentPlayer();
        }
        
        return stillMoves;
    }
    
    /**
     * Add a wall on the board, on an empty cell.
     * 
     * @param x the number of the line.
     * @param y the number of the column.
     */
    public void wall(int x, int y) {
        if(board.isFree(x, y)) {
            board.setColor(x, y, PlayerColor.RED);
            changeCurrentPlayer();
        }
    }
}
