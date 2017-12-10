package project.othello.breton.model;

/**
 * This class provides methods to create and manipulate players and 
 * their informations.
 *
 * @author Gabriel Breton - 43397
 */
public class Players {

    private final GameStrategy gameStrategy;
    private int score;
    private String pseudo;
    
    /**
     * Creates a new player.
     *
     * @param color the color of the player.
     */
    Players(GameStrategy playStrategy, String pseudo) {
        this.gameStrategy = playStrategy;
        this.score = 2;
        this.pseudo = pseudo;
    }
    
//    /**
//     * Creates a new player.
//     *
//     * @param color the color of the player.
//     */
//    Players(String pseudo) {
//        
//        this.score = 2;
//        this.pseudo = pseudo;
//    }

    public void executePlayGameStrategy(int row, int col) {
        gameStrategy.play(row, col);
    }
    
    public void executeWallGameStrategy(int row, int col) {
        gameStrategy.wall(row, col);
    }
    
    GameStrategy getGameStrategy() {
        return gameStrategy;
    }
    /**
     * Gives the score of the player.
     *
     * @return the score of the player.
     */
    int getScore() {
        return score;
    }

    /**
     * Add points to the score of the player.
     * 
     * @param points the points to add to the score.
     */
    void addPointsToScore(int points) {
        score += points;
    }
    
    /**
     * Sets the pseudo of the player.
     * 
     * @param pseudo the pseudo of the player.
     */
    void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Gives the pseudo of the player.
     * 
     * @return the pseudo of the player.
     */
    public String getPseudo() {
        return pseudo;
    }
}
