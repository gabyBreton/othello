package project.othello.breton.model;

/**
 * This class implements the human behavior for playing a pawn.
 * 
 * @author Gabriel Breton - 43397
 */
public class HumanBehavior implements GameStrategy {

    private final OthelloImpl game;
    
    /**
     * Creates a new human behavior for playing a pawn.
     * 
     * @param game the current session of Othello.
     */
    public HumanBehavior(OthelloImpl game) {
        this.game = game;
    }
    
    /**
     * Place a pawn on the given position.
     * 
     * @param row the line where to place the pawn.
     * @param col the column where to place the pawn.
     */
    @Override
    public void play(int row, int col) {
        game.play(row, col);
    }

    /**
     * Place a wall on the given position.
     * 
     * @param row the line where to place the wall.
     * @param col the column where to place the wall.
     */
    @Override
    public void wall(int row, int col) {
        game.wall(row, col);
    }
}
