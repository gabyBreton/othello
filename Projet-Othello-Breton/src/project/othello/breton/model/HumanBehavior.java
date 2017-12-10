package project.othello.breton.model;

/**
 * This class implements the human behavior for playing a pawn.
 * 
 * @author Gabriel Breton - 43397
 */
public class HumanBehavior implements PlayStrategy {

    private OthelloImpl game;
    
    /**
     * Creates a new human behavior for playing a pawn.
     * 
     * @param game the current session of Othello.
     */
    public HumanBehavior(OthelloImpl game) {
        this.game = game;
    }
    
    @Override
    public void play(int row, int col) {
        game.play(row, col);
    }
}
