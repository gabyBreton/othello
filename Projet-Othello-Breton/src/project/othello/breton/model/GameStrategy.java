package project.othello.breton.model;

/**
 * This interface is used to implement the strategy pattern to be able to play
 * with human vs human or human vs computer by implementing differents 
 * behaviors for differents kind of players.
 * 
 * @author Gabriel Breton - 43397
 */
public interface GameStrategy {
    
    void play(int row, int col);
    
    void wall(int row, int col);
}
