package project.othello.breton;

import project.othello.breton.viewConsole.Commands;
import project.othello.breton.viewConsole.View;
import projet.othello.breton.model.OthelloImpl;

/**
 *
 * @author Gabriel Breton - 43397
 */
public class Play {

    static void play(OthelloImpl othello) {
        boolean gameOver;
        boolean beginNewPart;
        String aCommand;
        String[] cmdSplitted;
        int cmdId;
        int xMove;
        int yMove;
                
        do {
            gameOver = false;
            View.showGameBoard(othello);

            while (!gameOver) {
                System.out.println("*-*-*-*-*-*-*-*-*-*");
                System.out.println("Enter a command:");
                aCommand = Commands.getACommand();
                System.out.println("*-*-*-*-*-*-*-*-*-*");
                if (Commands.verifyCommand(aCommand)) {
                    cmdSplitted = aCommand.split(" ");
                    cmdId = Commands.getCommandId(cmdSplitted);
                    switch (cmdId) {
                        case 1:
                            View.showGameBoard(othello);
                            break;
                        case 2:
                            View.showScores(othello);
                            break;
                        case 3:
                            //display help
                            break;
                        case 4:
                            xMove = Integer.parseInt(cmdSplitted[1]);
                            yMove = Integer.parseInt(cmdSplitted[2]);
                            othello.play(xMove - 1, yMove - 1);
                            
                            break;
                        default:
                            throw new IllegalArgumentException("command type is not valid. "
                                    + "Wrong implementation.");
                    }
                }
                gameOver = othello.isOver();
            }

            beginNewPart = false;
            System.out.println("Game is over !");
            View.showFinalScoresAndWinner(othello);
        } while (beginNewPart);

    }

    public static void main(String[] args) {
        OthelloImpl game;
        game = new OthelloImpl(10, 10);
        play(game);
    }
}
