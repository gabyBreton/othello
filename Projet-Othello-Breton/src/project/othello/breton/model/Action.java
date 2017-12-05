package project.othello.breton.model;

/**
 * This class is used to represent an action in the game the use it in the
 * history table view.
 *
 * @author Gabriel Breton - 43397
 */
public class Action {

    private final int id;
    private final String color;
    private final String action;
    private final String position;
    private final int taking;

    /**
     * Creates a new action
     * 
     * @param id a number to identify the action.
     * @param color the color of the player who made the action.
     * @param action a description of the action.
     * @param position the position on the board of the action.
     * @param taking the number of pawn taken by the action.
     */
    public Action(int id, String color, String action, String position, 
                  int taking) {
        this.id = id;
        this.color = color;
        this.action = action;
        this.position = position;
        this.taking = taking;
    }

    /**
     * Gives the id of the action.
     * 
     * @return the id of the action.
    */
    public int getId() {
        return id;
    }

    /**
     * Gives the color of the player who made the action.
     * 
     * @return the color of the player who made the action.
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Gives the description of the action.
     * 
     * @return the description of the action.
     */
    public String getAction() {
        return action;
    }

    /**
     * Gives the position on the board of the action.
     * 
     * @return the position on the board of the action.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Gives the number of pawn taken by the action.
     * 
     * @return the number of pawn taken by the action.
     */
    public int getTaking() {
        return taking;
    }
}
