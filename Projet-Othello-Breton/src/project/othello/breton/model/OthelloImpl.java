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
    private Players playerB;
    private Players playerW;
    private GameColor currentColor;
    private final List<Observer> listObs;

    private Action action;
    private int actionId;

    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    /**
     * Creates a new Othello game.
     *
     * @param rows the number of rows.
     * @param columns the number of columns.
     */
    public OthelloImpl(int rows, int columns) {
        board = new Board(rows, columns);
        currentColor = GameColor.BLACK;
        listObs = new ArrayList<>();
        playerB = new Players(new HumanBehavior(this), null);
        playerW = new Players(new HumanBehavior(this), null);
    }

    /**
     * Changes and creates new players, based on the pseudo inputs and the 
     * choice of the computer or not as adversary.
     * 
     * @param gameStrategyWhite the behavior choose for the white player.
     * @param pseudoB the pseudo of the black player.
     * @param pseudoW the pseudo of the white player.
     */
    @Override
    public void makePlayers(GameStrategy gameStrategyWhite, String pseudoB, 
                            String pseudoW) {
        playerB = new Players(new HumanBehavior(this), pseudoB);
        playerW = new Players(gameStrategyWhite, pseudoW);
    }

    /**
     * Verifies if the game is over.
     *
     * @return true if the game is over, or else false.
     */
    @Override
    public boolean isOver() {
        return board.isFull() || !stillValidMoves();
    }

    /**
     * Gives the score of the player black.
     *
     * @return the score of the player black.
     */
    @Override
    public int getScoreBlack() {
        return playerB.getScore();
    }

    /**
     * Gives the score of the player white.
     *
     * @return the score of the player white.
     */
    @Override
    public int getScoreWhite() {
        return playerW.getScore();
    }

    /**
     * Gives the current player color.
     *
     * @return the current player color.
     */
    @Override
    public GameColor getCurrentColor() {
        return currentColor;
    }

    /**
     * Gives the height of the board.
     *
     * @return the height of the board.
     */
    @Override
    public int getHeight() {
        return board.getRows();
    }

    /**
     * Gives the width of the board.
     *
     * @return the width of the board.
     */
    @Override
    public int getWidth() {
        return board.getColumns();
    }

    /**
     * Gives the color of cell at a position.
     *
     * @param row the number of the row.
     * @param col the number of the col.
     * @return the color of the cell, or null if the cell is free.
     */
    @Override
    public GameColor getColor(int row, int col) {
        return board.getColor(row, col);
    }

    /**
     * Gives the last action of the game.
     *
     * @return the last action of the game.
     */
    @Override
    public Action getAction() {
        return action;
    }

    /**
     * Gives the pseudo of the current player.
     * 
     * @return the pseudo of the current player.
     */
    private String getPseudoCurrentPlayer() {
        String currentPseudo;

        if (currentColor == GameColor.BLACK) {
            currentPseudo = playerB.getPseudo();
        } else {
            currentPseudo = playerW.getPseudo();
        }

        return currentPseudo;
    }

    /**
     * Gives the current player.
     * 
     * @return the current player.
     */
    @Override
    public Players getCurrentPlayer() {
        if (currentColor == GameColor.BLACK) {
            return playerB;
        } else {
            return playerW;
        }
    }
    
    /**
     * Gives the pseudo of the winner of the game, and if there is equality,
     * it will gives the String "Equality !".
     * 
     * @return the pseudo of the winner, or "Equality !".
     */
    @Override
    public String getWinner() {
        String winner;
        
        if (playerB.getScore() > playerW.getScore()) {
            winner = playerB.getPseudo();
        } else if (playerB.getScore() < playerW.getScore()) {
            winner = playerW.getPseudo();
        } else {
            winner = "Equality !" ;
        }

        return winner;
    }

    /**
     * Verifies if there is still valid moves for both players.
     *
     * @return true if there is still valid moves for both players on the board,
     * or else false.
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
     * Search for all the possibles pawns placement on the board.
     *
     * @return a list with all the valid moves.
     */
    private List<Positions> getValidMoves() {
        List<Positions> listValidMoves = new ArrayList<>();
        int row, col;

        for (Positions aPositionToTest : getListEmptyPositions()) {
            row = aPositionToTest.getRow();
            col = aPositionToTest.getCol();

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
        GameColor aColor;

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {

                aColor = board.getColor(row, col);

                if (aColor == null || aColor == GameColor.GREY) {
                    emptyPositions.add(new Positions(row, col));
                }
            }
        }

        return emptyPositions;
    }

    /**
     * Get a list of all the positions where flip the color.
     *
     * @param row the row from where starting to evaluate.
     * @param col the column from where starting to evaluate.
     * @return a list of positions to flip. This list can be returned empty.
     */
    private List<Positions> getListPositionsToFlip(int row, int col) {
        List<Positions> pawnsToFlip = new ArrayList<>();
        GameColor otherColor;

        for (Directions direction : Directions.values()) {
            int rowActual = row;
            int colActual = col;

            rowActual += direction.getMovOutOfRow();
            colActual += direction.getMovOutOfCol();

            if (isOnBoardAndOppositeColor(rowActual, colActual)) {
                rowActual += direction.getMovOutOfRow();
                colActual += direction.getMovOutOfCol();

                if (!board.isOnBoard(rowActual, colActual)) {
                    continue;
                }

                otherColor = getOtherPlayerColor();

                while (board.getColor(rowActual, colActual) == otherColor) {
                    rowActual += direction.getMovOutOfRow();
                    colActual += direction.getMovOutOfCol();

                    if (!board.isOnBoard(rowActual, colActual)) {
                        break;
                    }
                }

                if (!board.isOnBoard(rowActual, colActual)) {
                    continue;
                }

                if (board.getColor(rowActual, colActual) == currentColor) {
                    while (true) {
                        rowActual -= direction.getMovOutOfRow();
                        colActual -= direction.getMovOutOfCol();
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
     * Get the color of the non current player.
     *
     * @return the color of the non current player.
     */
    private GameColor getOtherPlayerColor() {
        GameColor otherColor;

        if (currentColor == GameColor.BLACK) {
            otherColor = GameColor.WHITE;
        } else {
            otherColor = GameColor.BLACK;
        }

        return otherColor;
    }

    /**
     * Verifies if a position is on the board and the cell at this position
     * contains a pawns of the opposite player.
     *
     * @param rowActual the current row of the position being verified.
     * @param colActual the current column of the position being verified.
     * @return true if the position is on the board and the cell at this
     * position contains a pawns of the opposite player, or else false.
     */
    private boolean isOnBoardAndOppositeColor(int rowActual, int colActual) {
        GameColor otherColor = getOtherPlayerColor();

        return board.isOnBoard(rowActual, colActual)
                && (board.getColor(rowActual, colActual) == otherColor);
    }

    /**
     * Changes the current player.
     */
    // /!\ Set on public for the test class !
    public void changeCurrentPlayer() {
        if (currentColor == GameColor.BLACK) {
            currentColor = GameColor.WHITE;
        } else {
            currentColor = GameColor.BLACK;
        }
    }

    /**
     * Place a pawn on a position on the game board.
     *
     * @param row the number of the row.
     * @param col the number of the column.
     * @throws ArrayIndexOutOfBoundsException if the value of row and col are
     * out of the board.
     */
    @Override
    public void play(int row, int col) throws ArrayIndexOutOfBoundsException {
        List<Positions> pawnsToFlip = new ArrayList<>();

        if (isValidPosition(row, col)) {
            pawnsToFlip = getListPositionsToFlip(row, col);
        }

        if (pawnsToFlip.size() > 0) {
            placePawnAndSetScore(pawnsToFlip, row, col);    
            getCurrentPlayer().incTaking(pawnsToFlip.size());            
            actionId++;
            action = new Action(actionId, getPseudoCurrentPlayer(),
                    " Place a pawn",
                    "    " + alphabet.charAt(col) + " - " + (row + 1),
                    pawnsToFlip.size());
            notifyObservers();          
            changeCurrentPlayer();              
            makeComputerPlay();             
        }
    }

    public int getTakingBlack() {
        return playerB.getTotalPawnsTaked();
    }

    public int getTakingWhite() {
        return playerW.getTotalPawnsTaked();
    }
    
    public void makeComputerPlay() {
        if (getCurrentPlayer().getGameStrategy() instanceof ComputerBehavior) {
            // This if-else structure try  to force the computer to play
            // more often pawn than walls.
            if (actionId % 6 == 0) {
                if ((int) (Math.random() * 101) % 2 == 0) {
                    getCurrentPlayer().executePlayGameStrategy(0, 0);
                } else {
                    getCurrentPlayer().executeWallGameStrategy(0, 0);
                }
            } else {
                getCurrentPlayer().executePlayGameStrategy(0, 0);
            }
        }
    }

    /**
     * Verifies if a position is valid as it on the game board.
     *
     * @param row the position on the row.
     * @param col the position on the column.
     * @return true if the position is on the board, or else false.
     */
    private boolean isValidPosition(int row, int col) {
        return (board.isFree(row, col)) && (board.isOnBoard(row, col));
    }

    /**
     * Flip the pawns and set the new score of the players.
     *
     * @param pawnsToFlip the list of positions of the pawns to flip.
     * @param row the row where to place the new pawn.
     * @param col the column where to place the new pawn.
     */
    private void placePawnAndSetScore(List<Positions> pawnsToFlip,
            int row, int col) {
        int nbFlippedPawns;

        flipPawns(pawnsToFlip);
        board.setColor(row, col, currentColor);
        board.incCounterPawnsOnBoard();

        nbFlippedPawns = pawnsToFlip.size();
        setPlayersScores(nbFlippedPawns);
    }

    /**
     * Flip the color of a list of pawns.
     *
     * @param pawnsToFlip the list of positions of the pawns to flip.
     */
    private void flipPawns(List<Positions> pawnsToFlip) {
        for (Positions aPosition : pawnsToFlip) {
            board.setColor(aPosition.getRow(), aPosition.getCol(), currentColor);
        }
    }

    /**
     * Actualize the new score of the players.
     *
     * @param nbFlippedPawns the number of pawns that have been flipped.
     */
    private void setPlayersScores(int nbFlippedPawns) {
        if (currentColor == GameColor.BLACK) {
            playerB.addPointsToScore(nbFlippedPawns + 1);
            playerW.addPointsToScore(-nbFlippedPawns);
        } else {
            playerW.addPointsToScore(nbFlippedPawns + 1);
            playerB.addPointsToScore(-nbFlippedPawns);
        }
    }

    /**
     * Set to 'null' all the cells that have been set as possible positions for
     * a pawn placement.
     */
    @Override
    public void cleanLastPlayerPossibilities() {
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if (board.getColor(row, col) == GameColor.GREY) {
                    board.setColor(row, col, null);
                }
            }
        }
    }

    /**
     * Sets all the cell possible for a pawn placement to 'GREY' to be
     * recognized.
     */
    @Override
    public void setPossiblePositions() {
        List<Positions> listPossiblePositions = new ArrayList<>();
        listPossiblePositions = getValidMoves();

        for (Positions aPosition : getValidMoves()) {
            board.setColor(aPosition.getRow(), aPosition.getCol(),
                    GameColor.GREY);
        }

        if (listPossiblePositions.isEmpty()) {
            changeCurrentPlayer();
        }
    }

    /**
     * Add a wall on the board, on an empty cell.
     *
     * @param row the number of the row.
     * @param col the number of the column.
     * @throws ArrayIndexOutOfBoundsException when trying to place a wall out 
     * of the board
     */
    @Override
    public void wall(int row, int col) throws ArrayIndexOutOfBoundsException {
        if (thereIsAWall(row, col)) {
            deleteWall(row, col);
        } else if (board.isFree(row, col)) {
            board.setColor(row, col, GameColor.RED);
            board.incCounterWallsOnBoard();
            actionId++;
            action = new Action(actionId, getPseudoCurrentPlayer(),
                    "  Place a wall",
                    "    " + alphabet.charAt(col) + " - " + (row + 1),
                    0);
            notifyObservers();
            changeCurrentPlayer();            
            makeComputerPlay();                         
        }
    }

    private boolean thereIsAWall(int row, int col) {
        return board.getColor(row, col) == GameColor.RED;
    }
    
    private void deleteWall(int row, int col) {
        board.setColor(row, col, null);  
        board.decCounterWallsOnBoard();
        actionId++;
        action = new Action(actionId, getPseudoCurrentPlayer(),
                    " Delete a wall",
                    "    " + alphabet.charAt(col) + " - " + (row + 1),
                    0);
        notifyObservers();
        changeCurrentPlayer();
        makeComputerPlay();
    }
    
    /**
     * Gives the number of wall on the board.
     *
     * @return the number of wall on the board.
     */
    @Override
    public int getCounterWallsOnBoard() {
        return board.getCounterWallsOnBoard();
    }

    /**
     * Gives the number of pawns on the board.
     *
     * @return the number of pawns on the board.
     */
    @Override
    public int getCounterPawnsOnBoard() {
        return board.getCounterPawnsOnBoard();
    }

    /**
     * To pass a turn.
     */
    @Override
    public void pass() {
        actionId++;
        action = new Action(actionId, getPseudoCurrentPlayer(),
                            "        Pass", "  ", 0);        
        notifyObservers();
        changeCurrentPlayer();
        cleanLastPlayerPossibilities();
        setPossiblePositions();
        makeComputerPlay();
    }

    /**
     * Gives a random valid positions, where a pawn can be placed.
     * 
     * @return a random valid positions, where a pawn can be placed.
     */
    Positions getRandomValidPosition() {
        int choice;
        choice = (int) (Math.random() * getValidMoves().size());
        return getValidMoves().get(choice);
        //@Fix-Me:
        //IF THE LIST OF VALID POSITIONS IS EMPTY WE HAVE
        //INDEXOUTOFBONDS EXCEPTIONS /!\
    }
    
    /**
     * Gives a random empty position, where a wall can be placed.
     * 
     * @return a random empty position, where a wall can be placed.
     */
    Positions getRandomEmptyPositions() {
        int choice;
        choice = (int) (Math.random() * getListEmptyPositions().size());
        return getListEmptyPositions().get(choice);
        //@Fix-Me:
        //IF THE LIST OF EMPTY POSITIONS IS EMPTY WE HAVE
        //INDEXOUTOFBONDS EXCEPTION /!\        
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
