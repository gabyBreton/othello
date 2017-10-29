package projet.othello.breton.model;

/**
 * This class provides methods to create and manipulate players and their infos.
 *
 * @author Gabriel Breton - 43397
 */
public class Players {

    private final Color color;
    private int score;

    /**
     * Creates a new player.
     *
     * @param color the color of the player.
     */
    Players(Color color) {
        this.color = color;
        this.score = 2;
    }

    /**
     * Gives the color of the player.
     *
     * @return the color of the player.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gives the score of the player.
     *
     * @return the score of the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Add points to the score of the player.
     * 
     * @param pointsToAdd the points to add to the score.
     */
    void addPointsToScore(int pointsToAdd) {
        score += pointsToAdd;
    }
}
