package project.othello.breton.model;


/**
 * This class implements the computer behavior for playing a pawn.
 * 
 * @author Gabriel Breton - 43397
 */
public class ComputerBehavior implements GameStrategy {
    
    private final OthelloImpl game;

    public ComputerBehavior(OthelloImpl game) {
        this.game = game;
    }

    /**
     * Place a pawn randomly on a valid position.
     * 
     * @param row the line where to place the pawn.
     * @param col the column where to place the pawn.
     */
    @Override
    public void play(int row, int col) {
        Positions randomPosition;
        randomPosition = game.getRandomValidPosition();
        game.play(randomPosition.getRow(), randomPosition.getCol());
    }  

    /**
     * Place a wall randomly on an empty position.
     * 
     * @param row the line where to place the wall.
     * @param col the column where to place the wall.
     */    
    @Override
    public void wall(int row, int col) {
        Positions randomPosition;
        randomPosition = game.getRandomEmptyPositions();
        game.wall(randomPosition.getRow(), randomPosition.getCol());
    }
}
