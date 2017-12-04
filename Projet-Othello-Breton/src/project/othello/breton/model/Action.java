package project.othello.breton.model;

/**
 * This class represents an action on the game.
 *
 * @author Gabriel Breton - 43397
 */
public class Action {

    private final int id;
    private final String color;
    private final String action;
    private final String position;
    private final int taking;

    public Action(int id, String pseudo, String action, String position, int taking) {
        this.id = id;
        this.color = pseudo;
        this.action = action;
        this.position = position;
        this.taking = taking;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public String getAction() {
        return action;
    }

    public String getPosition() {
        return position;
    }

    public int getTaking() {
        return taking;
    }
    
    
}
