package project.othello.breton.model;

import java.util.ArrayList;
import java.util.List;
import project.othello.breton.util.Observable;
import project.othello.breton.util.Observer;

/**
 * This class is the facade of Othello.
 *
 * @author Gabriel Breton - 43397
 */
public class OthelloImpl implements Othello, Observable {

    private final Board board;
    private final Players playerB;
    private final Players playerW;
    private PlayerColor currentColor;
    private final List<Observer> listObs;

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
        listObs = new ArrayList<>();
    }

    @Override
    public boolean isOver() {
        return board.isFull() || !stillValidMoves();
    }

    @Override
    public List<Players> getPlayers() {
        List<Players> listPlayers = new ArrayList<>();

        listPlayers.add(playerB);
        listPlayers.add(playerW);

        return listPlayers;
    }
    
    @Override
    public int getScoreBlack() {
        return playerB.getScore();
    }
    
    @Override
    public int getScoreWhite() {
        return playerW.getScore();
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
    public PlayerColor getColor(int row, int col) {
        return board.getColor(row, col);
    }

    @Override
    public void cleanLastPlayerPossibilities() {
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidht(); col++) {
                if (board.getColor(row, col) == PlayerColor.GREY) {
                    board.setColor(row, col, null);
                }
            }
        }
    }

    @Override
    public void setPossiblePositions() {
        List<Positions> listPossiblePositions = new ArrayList<>();
        int row, col;

        listPossiblePositions = getValidMoves();

        for (Positions aPosition : listPossiblePositions) {
            row = aPosition.getROW();
            col = aPosition.getCOLUMN();
            board.setColor(row, col, PlayerColor.GREY);
        }

        if (listPossiblePositions.isEmpty()) {
            changeCurrentPlayer();
        }
    }

    @Override
    public void play(int row, int col) throws ArrayIndexOutOfBoundsException {
        List<Positions> pawnsToFlip = new ArrayList<>();
        boolean validMove;

        validMove = false;

        if (isValidPosition(row, col)) {
            pawnsToFlip = getListPositionsToFlip(row, col);
            validMove = pawnsToFlip.size() > 0;
        }

        if (validMove) {
            placeFlipAndSetScore(pawnsToFlip, row, col);
            changeCurrentPlayer();
        }
        
        notifyObservers();
    }

    /**
     * Flip the pawns and set the new score of the players.
     *
     * @param pawnsToFlip the list of positions of the pawns to flip.
     * @param row the position where to place the new pawn on the x axis.
     * @param col the position where to place the new pawn on the y axis.
     */
    private void placeFlipAndSetScore(List<Positions> pawnsToFlip,
            int row, int col) {
        int nbFlippedPawns;

        flipPawns(pawnsToFlip);
        board.setColor(row, col, currentColor);
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
        notifyObservers();
        
    }

    /**
     * Get a list of all the positions where flip the color.
     *
     * @param row the position from where starting to evaluate, on the x axis.
     * @param col the position from where starting to evaluate, on the y axis.
     * @return a list of positions to flip. This list can be returned empty.
     */
    private List<Positions> getListPositionsToFlip(int row, int col) {
        List<Positions> pawnsToFlip = new ArrayList<>();

        for (Directions direction : Directions.values()) {
            int rowActual = row;
            int colActual = col;

            rowActual += direction.getxAxisMov();
            colActual += direction.getyAxisMov();

            if (isOnBoardAndOppositeColor(rowActual, colActual)) {
                rowActual += direction.getxAxisMov();
                colActual += direction.getyAxisMov();

                if (!board.isOnBoard(rowActual, colActual)) {
                    continue;
                }

                moveForwardLine(rowActual, colActual, direction);

                if (!board.isOnBoard(rowActual, colActual)) {
                    continue;
                }

                if (board.getColor(rowActual, colActual) == currentColor) {
                    while (true) {
                        rowActual -= direction.getxAxisMov();
                        colActual -= direction.getyAxisMov();
                        if ((rowActual == row) && (colActual == col)) {
                            break;
                        }
                        pawnsToFlip.add(new Positions(rowActual, colActual));
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
     * @param rowActual the current positions being verified, on x axis.
     * @param colActual the current positions being verified, on y axis.
     * @param direction the direction where to move.
     */
    private void moveForwardLine(int rowActual, int colActual,
            Directions direction) {
        PlayerColor otherColor = getOtherPlayerColor();

        while (board.getColor(rowActual, colActual) == otherColor) {
            rowActual += direction.getxAxisMov();
            colActual += direction.getyAxisMov();

            if (!board.isOnBoard(rowActual, colActual)) {
                break;
            }
        }
    }

    /**
     * Verifies if a position is on the board and the cell at this position
     * contains a pawns of the opposite player.
     *
     * @param rowActual the current positions being verified, on x axis.
     * @param colActual the current positions being verified, on y axis.
     * @return true if the position is on the board and the cell at this
     * position contains a pawns of the opposite player, or else false.
     */
    private boolean isOnBoardAndOppositeColor(int rowActual, int colActual) {
        PlayerColor otherColor = getOtherPlayerColor();

        return board.isOnBoard(rowActual, colActual)
                && (board.getColor(rowActual, colActual) == otherColor);
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
     * @param row the position on the x axis.
     * @param col the position on the y axis.
     * @return true if the position is on the board, or else false.
     */
    private boolean isValidPosition(int row, int col) {
        return (board.isFree(row, col)) && (board.isOnBoard(row, col));
    }

    /**
     * Flip the color of a list of pawns.
     *
     * @param pawnsToFlip the list of positions that represents the pawns to
     * flip.
     */
    private void flipPawns(List<Positions> pawnsToFlip) {
        int row, col;

        for (Positions aPosition : pawnsToFlip) {
            row = aPosition.getROW();
            col = aPosition.getCOLUMN();

            board.setColor(row, col, currentColor);
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
        int row, col;

        emptyPositions = getListEmptyPositions();

        for (Positions aPositionToTest : emptyPositions) {
            row = aPositionToTest.getROW();
            col = aPositionToTest.getCOLUMN();

            if (getListPositionsToFlip(row, col).size() > 0) {
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

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {

                aColor = board.getColor(row, col);

                if (aColor == null || aColor == PlayerColor.GREY) {
                    emptyPositions.add(new Positions(row, col));
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
     * @param row the number of the line.
     * @param col the number of the column.
     */
    public void wall(int row, int col) {
        if (board.isFree(row, col)) {
            board.setColor(row, col, PlayerColor.RED);
            changeCurrentPlayer();
            board.incCounterWallsOnBoard();
            notifyObservers();
        }
    }

    /**
     * Gives the number of wall on the board.
     *
     * @return the number of wall on the board.
     */
    public int getCounterWallsOnBoard() {
        return board.getCounterWallsOnBoard();
    }

    /**
     * Add an observer in the list of observers.
     * 
     * @param o the observer to add.
     */
    @Override
    public void addObserver(Observer o) {
        if (!listObs.contains(o)) {
            listObs.add(o);
        }
    }

    /**
     * Add a variable number of observers.
     * 
     * @param o the differents observers.
     */
    @Override
    public void addAllObserver(Observer... o) {
        for (Observer obs : o) {
            addObserver(obs);
        }
    }

    /**
     * Deletes an observer from the list of observers.
     * 
     * @param o the observer to delete.
     */
    @Override
    public void deleteObserver(Observer o) {
        listObs.remove(o);
    }

    /**
     * Notifies of an update the observers of the list.
     */
    private void notifyObservers() {
        listObs.forEach((obs) -> {
            obs.update();
        });
    }
}
