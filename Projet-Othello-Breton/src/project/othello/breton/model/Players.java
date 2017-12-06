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

    /**
     * Creates a new player.
     *
     * @param color the color of the player.
     */
    Players(GameColor color) {
        this.color = color;
        this.score = 2;
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
}
