package project.othello.breton.model;

/**
 * This interface is used to implement the strategy pattern to be able to play
 * with human vs human or human vs computer by implementing differents 
 * behaviors for differents kind of players.
 * 
 * @author Gabriel Breton - 43397
 */
public interface GameStrategy {
    
    /**
     * Place a pawn on the given position if the behavior comes from the Human
     * else place a pawn randomly on a valid position if the behavior come from
     * the Computer.
     * 
     * @param row the line where to place the pawn.
     * @param col the column where to place the pawn.
     */
    void play(int row, int col);
    
    /**
     * Place a wall on the given position if the behavior comes from the Human
     * else place a wall randomly on an empty position if the behavior come from
     * the Computer.
     * 
     * @param row the line where to place the wall.
     * @param col the column where to place the wall.
     */
    void wall(int row, int col);
}
