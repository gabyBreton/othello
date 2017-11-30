package project.othello.breton.model;

/**
 * This class provides methods to create and manipulate players and their infos.
 *
 * @author Gabriel Breton - 43397
 */
public class Players {

    //private String pseudo;
    private final PlayerColor color;
    private int score;

    /**
     * Creates a new player.
     *
     * @param color the color of the player.
     */
    Players(PlayerColor color) {
        this.color = color;
        this.score = 2;
    }

    /**
     * Gives the color of the player.
     *
     * @return the color of the player.
     */
    public PlayerColor getColor() {
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
    
//    /**
//     * Sets the pseudo of the player.
//     * 
//     * @param pseudoPlayer the pseudo to set.
//     */
//    public void setPseudo(String pseudoPlayer) {
//        this.pseudo = pseudoPlayer; //DOES NOT WORK
//    }
//
//    public String getPseudo() {
//        return pseudo;
//    }
}
