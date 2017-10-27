package project.othello.breton.viewConsole;

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
                
        do {
            gameOver = false;
            othello.setPossiblePositions();
            View.showGameBoard(othello);
            while (!gameOver) {
                aCommand = View.newAction(othello);
                if (Commands.verifyCommand(aCommand)) {
                    useCommand(aCommand, othello);
                }
                gameOver = othello.isOver();
            }
            beginNewPart = false;
            System.out.println("Game is over !");
            View.showFinalScoresAndWinner(othello);
        } while (beginNewPart);

    }

    private static void useCommand(String aCommand, OthelloImpl othello) 
                                   throws IllegalArgumentException {
        String[] cmdSplitted;
        int cmdId;
        cmdSplitted = aCommand.split(" ");
        cmdId = Commands.getCommandId(cmdSplitted);
        
        useCommandId(cmdId, othello, cmdSplitted);
    }

    private static void useCommandId(int cmdId, OthelloImpl othello, 
                                     String[] cmdSplitted) 
                                     throws IllegalArgumentException {
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
                playAMove(cmdSplitted, othello);
                break;
            default:
                throw new IllegalArgumentException("command type is not valid. "
                        + "Wrong implementation.");
        }
    }

    private static void playAMove(String[] cmdSplitted, OthelloImpl othello) 
                                  throws NumberFormatException {
        int xMove;
        int yMove;
        xMove = Integer.parseInt(cmdSplitted[1]);
        yMove = Integer.parseInt(cmdSplitted[2]);
        othello.play(xMove - 1, yMove - 1);
    }

    public static void main(String[] args) {
        OthelloImpl game;
        game = new OthelloImpl(8, 8);
        play(game);
    }
}
