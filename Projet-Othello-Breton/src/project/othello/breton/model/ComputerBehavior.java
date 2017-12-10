package project.othello.breton.model;


/**
 * This class implements the computer behavior for playing a pawn.
 * 
 * @author Gabriel Breton - 43397
 */
public class ComputerBehavior implements PlayStrategy {
    
    private final OthelloImpl game;

    public ComputerBehavior(OthelloImpl game) {
        this.game = game;
    }

    @Override
    public void play(int row, int col) {
        Positions randomPosition;
        randomPosition = game.getRandomValidPosition();
        game.play(randomPosition.getRow(), randomPosition.getCol());
    }  
}
