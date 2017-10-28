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
        
        do {
            gameOver = false;
            startTheGame(othello);
            while (!gameOver) {
                getAndManageCommand(othello);
                gameOver = othello.isOver();
            }
            beginNewPart = false;
            System.out.println("Game is over !");
            View.showFinalScoresAndWinner(othello);
        } while (beginNewPart);

    }

    private static void startTheGame(OthelloImpl othello) {
        View.displayStartMsg();
        othello.setPossiblePositions();
        View.showGameBoard(othello);
    }

    private static void getAndManageCommand(OthelloImpl othello) {
        String aCommand;
        aCommand = View.newAction(othello);

        if (Commands.verifyCommand(aCommand)) {
            if (Commands.isPlayValueCorrect(aCommand, othello)) {
                useTheCommand(aCommand, othello);
            }
        } else {
            View.displayWrongCmd(aCommand);
        }
    }

    private static void useTheCommand(String aCommand, OthelloImpl othello) {
        int cmdId;
        cmdId = Commands.findCommandID(aCommand, othello);
        useCommandId(cmdId, othello, aCommand);
    }

    private static void useCommandId(int cmdId, OthelloImpl othello, 
                                     String command) 
                                      {
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
                playAMove(command, othello);
                break;
        }
    }

    private static void playAMove(String command, OthelloImpl othello) {
        int xMove;
        int yMove;
        String[] cmdSplitted;
        cmdSplitted = command.split(" ");
        
        xMove = Integer.parseInt(cmdSplitted[1]);
        yMove = convertLetterToNumber(cmdSplitted[2]);
        othello.play(xMove - 1, yMove);
    }
    
    private static int convertLetterToNumber(String letterMove){
        String alphabet;

        alphabet = "abcdefghijklmnopqrstuvwxyz";
        return alphabet.indexOf(letterMove);
    }
    public static void main(String[] args) {
        OthelloImpl game;
        game = new OthelloImpl(4, 4);
        play(game);
    }
}
