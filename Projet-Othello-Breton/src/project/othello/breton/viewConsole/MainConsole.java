package project.othello.breton.viewConsole;

import projet.othello.breton.model.OthelloImpl;

/**
 * This class is use for it main function to launch the Othello game.
 *
 * @author Gabriel Breton - 43397
 */
public class MainConsole {

    /**
     * Launch a party of Othello.
     * @param args 
     */
    public static void main(String[] args) {
        OthelloImpl game;
        int height, widht;

        View.displayStartMsg();
        System.out.print("Height --> ");
        height = Commands.verifyIntInput(26, 0);
        System.out.print("Width --> ");
        widht = Commands.verifyIntInput(26, 0);

        game = new OthelloImpl(height, widht);
        Play.play(game);
    }
}
