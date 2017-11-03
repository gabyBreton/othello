package project.othello.breton.viewConsole;

import project.othello.breton.model.PlayerColor;
import project.othello.breton.model.OthelloImpl;
import project.othello.breton.model.Players;

/**
 * This class provides methods related to the console view of the game.
 *
 * @author Gabriel Breton - 43397
 */
class View {

    /**
     * Shows the Othello game board.
     * 
     * @param game a session of Othello.
     */
    static void showGameBoard(OthelloImpl game) {
        displayHeader(game.getWidht());
        displayContent(game);
        displayFooter(game.getWidht());
    }

    /**
     * Displays the header part of the game board.
     * 
     * @param width the width of the game board.
     */
    private static void displayHeader(int width) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        
        displaySeparationLine(width);
        displayAlphabetLine(width, alphabet);
    }

    /**
     * Displays the alphabet numerotation line when showing the game board.
     * 
     * @param width the width of the game board.
     * @param alphabet an array with all letters of the alphabet.
     */
    private static void displayAlphabetLine(int width, char[] alphabet) {
        System.out.print("    ");
        for (int i = 0; i < width; i++) {
            System.out.print(" " + alphabet[i] + " ");
        }
        System.out.println("");
    }

    /**
     * Displays the separation line when showing the game board.
     * 
     * @param width the width of the game board.
     */
    private static void displaySeparationLine(int width) {
        System.out.print("  ");
        for (int i = 0; i < (width * 3) + 4; i++) {
            System.out.print("=");
        }
        System.out.println("");
    }

    /**
     * Displays the content of the game board.
     * 
     * @param game a session of Othello.
     */
    private static void displayContent(OthelloImpl game) {
        for (int i = 0; i < game.getHeight(); i++) {

            displayLeftNumerotation(i); 

            for (int j = 0; j < game.getWidht(); j++) {
                printABoxContent(game, i, j);
            }

            displayRightNumerotation(i);
            System.out.println("");
        }
    }

    /**
     * Displays the contents of a box of the board.
     * 
     * @param game a session of the Othello.
     * @param x the position of the box on the x axis.
     * @param y the position of the box on the y axis.
     */
    private static void printABoxContent(OthelloImpl game, int x, int y) {
        String ansiCyan = "\u001B[42m";
        String ansiBlack = "\u001B[40m";
        String ansiWhite = "\u001B[47m";
        String ansiReset = "\u001B[0m"; 
        PlayerColor colorCell;
        
        colorCell = game.getColor(x, y);
        if (null == colorCell) {
            System.out.print(ansiCyan + "__|" + ansiReset);
        } else switch (colorCell) {
            case BLACK: 
                System.out.print(ansiBlack + "   " + ansiReset);
                break;
            case WHITE:
                System.out.print(ansiWhite + "   " + ansiReset);
                break;
            case GREY:
                System.out.print(ansiCyan + "_O|" + ansiReset);
                break;
        }
    }
    
    /**
     * Displays the left side numerotation.
     * 
     * @param i the number of the row.
     */
    private static void displayLeftNumerotation(int i) {
        if ((i + 1) / 10 == 0) {
            System.out.print("  " + (i + 1) + " ");
        } else {
            System.out.print(" " + (i + 1) + " ");
        }
    }
    
    /**
     * Displays the right side numerotation.
     * 
     * @param i the number of the row.
     */
    private static void displayRightNumerotation(int i) {
        System.out.print(" " + (i + 1));
    }

    /**
     * Displays the footer part of the game.
     * 
     * @param width the width of the game board.
     */
    private static void displayFooter(int width) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        displayAlphabetLine(width, alphabet);
        displaySeparationLine(width);
    }

    /**
     * Displays the final scores and the winner.
     * 
     * @param game the session of Othello.
     */
    static void showFinalScoresAndWinner(OthelloImpl game) {
        Players playerBlack, playerWhite;

        playerBlack = game.getPlayers().get(0);
        playerWhite = game.getPlayers().get(1);
        showScores(playerBlack.getScore(), playerWhite.getScore());
        
        if (playerBlack.getScore() > playerWhite.getScore()) {
            System.out.println("The winner is Black");
        } else if (playerWhite.getScore() > playerBlack.getScore()) {
            System.out.println("The winner is White");
        } else {
            System.out.println("The scores are equals, both wins !");
        }
    }

    /**
     * Displays the scores of the players.
     * 
     * @param game the session of Othello.
     */
    static void showScores(int scoreBlack, int scoreWhite) {
        System.out.println("Black: " + scoreBlack);
        System.out.println("White: " + scoreWhite);

    }
    
    /**
     * This methods is use to display to move possibilities and ask a new 
     * command.
     * 
     * @param game the session of Othello.
     * @return the command of the user.
     */
    static String newAction(OthelloImpl game) {
        String command;
        
        setNewPossibilites(game);
        System.out.println("*-*-*-*-*-*-*-*-*-*");
        askForACommand(game);
        command = Commands.getACommand();
        System.out.println("*-*-*-*-*-*-*-*-*-*");
        return command;
    }

    /**
     * Asks the player Black or White to enter a command.
     * 
     * @param game the session of Othello.
     */
    static void askForACommand(OthelloImpl game) {
        String currentPlayer;
        currentPlayer = game.getCurrentColor().toString();
        currentPlayer =  currentPlayer.charAt(0) 
                         + currentPlayer.substring(1).toLowerCase();
                
        System.out.println(currentPlayer + " enter a command:");
    }
    
    /**
     * Set on the board the possibles positions of moves.
     * 
     * @param game the session of Othello.
     */
    private static void setNewPossibilites(OthelloImpl game) {
        game.cleanLastPlayerPossibilities();
        game.setPossiblePositions();
    }
    
    /**
     * Displays an error message with the wrong command entered.
     * 
     * @param command the command entered.
     */
    static void displayWrongCmd(String command) {
        String ansiRed = "\u001B[31m";
        String ansiReset = "\u001B[0m";
        System.out.println(ansiRed + "You have entered a wrong command: " 
                          + ansiReset + command);
        System.out.println(ansiRed + "Enter 'help' for more informations" 
                           + ansiReset);
    }

    /**
     * Displays an error message because the value entered is to large.
     * 
     * @param command the command entered.
     */
    static void displayTooLargeValue(String command) {
        String ansiRed = "\u001B[31m";
        String ansiReset = "\u001B[0m";
        System.out.println(ansiRed + "You have entered a too large value: " 
                          + ansiReset + command);
    }
    
    /**
     * Displays the start messages for the beginning of the game.
     */
    static void displayStartMsg() {
        System.out.println("Welcome in Othello Game !");
        System.out.println("");
        System.out.println("Enter the dimensions of the game board.");
    }
}
