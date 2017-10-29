package project.othello.breton.viewConsole;

import projet.othello.breton.model.OthelloImpl;

/**
 * This class provides methods to connect the view with the model to play a 
 * party of the Othello.
 * 
 * @author Gabriel Breton - 43397
 */
class Play {

    /**
     * There is where all the methods that represent the course of the game.
     * @param othello a session of Othello.
     */
    static void play(OthelloImpl othello) {
        boolean gameOver;

        gameOver = false;
        startTheGame(othello);
        while (!gameOver) {
            getAndManageCommand(othello);
            gameOver = othello.isOver();
        }
        System.out.println("Game is over !");
        View.showFinalScoresAndWinner(othello);
    }

    /**
     * Launches the elements of the start part of the game.
     * 
     * @param othello a session of Othello.
     */
    private static void startTheGame(OthelloImpl othello) {
        othello.setPossiblePositions();
        View.showGameBoard(othello);
    }

    /**
     * Takes a command from the user and manages its content.
     * 
     * @param othello a session of Othello.
     */
    private static void getAndManageCommand(OthelloImpl othello) {
        String aCommand;
        
        aCommand = View.newAction(othello);
        if (Commands.verifyCommand(aCommand)) {
            if (Commands.isPlayValueCorrect(aCommand, othello)) {
                execTheCmd(aCommand, othello);
            }
        } else {
            View.displayWrongCmd(aCommand);
        }
    }

    /**
     * Executes the command entered by the user.
     * 
     * @param command the command entered.
     * @param othello a session of Othello.
     */
    private static void execTheCmd(String command, OthelloImpl othello) {
        int cmdId;
        cmdId = Commands.findCommandID(command, othello);
        useCommandId(cmdId, command, othello);
    }

    /**
     * This method is used by execTheCmd() to execute a command when its ID is
     * found.
     * 
     * @param cmdId the ID of the command.
     * @param command the command entered.
     * @param othello a session of Othello.
     */
    private static void useCommandId(int cmdId, String command, 
                                      OthelloImpl othello) {
        switch (cmdId) {
            case 1:
                View.showGameBoard(othello);
                break;
            case 2:
                int scoreBlack, scoreWhite;
                scoreBlack = othello.getPlayers().get(0).getScore();
                scoreWhite = othello.getPlayers().get(1).getScore();
                View.showScores(scoreBlack, scoreWhite);
                break;
            case 3:
                //display help
                break;
            case 4:
                playAMove(command, othello);
                break;
        }
    }

    /**
     * Takes the value from the command and play a move with it.
     * 
     * @param command the command entered.
     * @param othello a session of Othello.
     */
    private static void playAMove(String command, OthelloImpl othello) {
        int xMove;
        int yMove;
        String[] cmdSplitted;
        
        cmdSplitted = command.split(" ");
        xMove = Integer.parseInt(cmdSplitted[1]);
        yMove = convertLetterToNumber(cmdSplitted[2]);

        othello.play(xMove - 1, yMove);
    }

    /**
     * Converts a letter entered by the user to its corresponding index value.
     * 
     * @param letterMove the letter entered.
     * @return the corresponding value of the letter.
     */
    private static int convertLetterToNumber(String letterMove) {
        String alphabet;

        alphabet = "abcdefghijklmnopqrstuvwxyz";
        return alphabet.indexOf(letterMove);
    }
}
