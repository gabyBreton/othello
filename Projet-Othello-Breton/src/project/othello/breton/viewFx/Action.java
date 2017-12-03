package project.othello.breton.viewFx;

/**
 * This class represents an action on the game.
 *
 * @author Gabriel Breton - 43397
 */
public class Action {

    private final int id;
    private final String pseudo;
    private final String action;
    private final String position;
    private final int taking;

    public Action(int id, String pseudo, String action, String position, int taking) {
        this.id = id;
        this.pseudo = pseudo;
        this.action = action;
        this.position = position;
        this.taking = taking;
    }

    public int getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
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
