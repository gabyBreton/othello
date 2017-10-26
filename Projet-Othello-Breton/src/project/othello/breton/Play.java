package project.othello.breton;

import project.othello.breton.viewConsole.View;
import projet.othello.breton.model.OthelloImpl;

/**
 *
 * @author Gabriel Breton - 43397
 */
public class Play {

    static void play(OthelloImpl othello) {
        boolean gameOver;
        
        do {
            gameOver = false;
            //View.wherePlacePawn();
            
            while (!gameOver) {
                View.showGameBoard(othello);
                
                //gameOver = othello.isOver();
                gameOver = true;
            }
        } while (!gameOver);

    }
    
    public static void main(String[] args) {
        OthelloImpl game;
        game = new OthelloImpl(8, 8);
        play(game);
    }
}
