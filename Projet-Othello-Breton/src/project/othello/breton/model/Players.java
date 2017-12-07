package project.othello.breton.model;

/**
 * This class provides methods to create and manipulate players and 
 * their informations.
 *
 * @author Gabriel Breton - 43397
 */
public class Players {

    private final GameColor color;
    private int score;
    private String pseudo;
    
    /**
     * Creates a new player.
     *
     * @param color the color of the player.
     */
    Players(GameColor color, String pseudo) {
        this.color = color;
        this.score = 2;
        this.pseudo = pseudo;
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
