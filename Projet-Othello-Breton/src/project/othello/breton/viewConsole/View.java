package project.othello.breton.viewConsole;

import projet.othello.breton.model.Color;
import projet.othello.breton.model.OthelloImpl;

/**
 * This class provides methods related to the console view of the game.
 *
 * @author Gabriel Breton - 43397
 */
public class View {

    static void showGameBoard(OthelloImpl game) {
        displayHeader(game.getWidht());
        displayContent(game);
        displayFooter(game.getWidht());
    }

    private static void displayHeader(int widht) {
        System.out.print("  ");
        for (int i = 0; i < (widht * 3) + 1.5; i++) {
            System.out.print("=");
        }

        System.out.println("");
        System.out.print("   ");
        for (int i = 0; i < widht; i++) {
            System.out.print(" " + (i + 1) +" ");
        }

        System.out.println("");
    }

    private static void displayContent(OthelloImpl game) {
        Color colorCell;

        for (int i = 0; i < game.getWidht(); i++) {

            printSideNumerotation(i); 
            for (int j = 0; j < game.getHeight(); j++) {
                printABoxContent(game, i, j);
            }
            System.out.println("");
        }
    }

    private static void printABoxContent(OthelloImpl game, int i, int j) {
        String ansiCyan = "\u001B[46m";
        String ansiMagenta = "\u001B[45m";
        String ansiReset = "\u001B[0m"; 
        Color colorCell;
        String charToPrint;
        
        colorCell = game.getColor(i, j);
        charToPrint = setPrintedColorPlayer(colorCell);
        
        if (null == colorCell) {
            System.out.print(ansiCyan + charToPrint + ansiReset);
        } else switch (colorCell) {
            case BLACK: case WHITE:
                System.out.print(ansiCyan + charToPrint + ansiReset);
                break;
            default:
                System.out.print(ansiMagenta + charToPrint + ansiReset);
                break;
        }
    }
    
    private static String setPrintedColorPlayer(Color colorCell) {
        String ansiBlack = "\u001B[30m";
        String ansiWhite = "\u001B[37m";
        String ansiReset = "\u001B[0m"; 
        String charToPrint;
        
        if (null == colorCell) {
            charToPrint = " . ";
        } else switch (colorCell) {
            case BLACK:
                charToPrint = ansiBlack + " B " + ansiReset;
                break;
            case WHITE:
                charToPrint= ansiWhite + " W " + ansiReset;
                break;
            default:
                charToPrint = " . ";
                break;
        }
        return charToPrint;
    }
    
    private static void printSideNumerotation(int i) {
        if ((i + 1) / 10 == 0) {
            System.out.print("  " + (i + 1) + " ");
        } else if ((i+1) / 10 >= 1) {
            System.out.print(" " + (i + 1) + " ");
        }
    }

    private static void displayFooter(int widht) {
        System.out.print("   ");
        for (int i = 0; i < widht; i++) {
            System.out.print(" " + (i + 1) + " ");
        }
        System.out.println("");

        System.out.print("  ");
        for (int i = 0; i < (widht * 3) + 2; i++) {
            System.out.print("=");
        }
        System.out.println("");

    }

    static void showFinalScoresAndWinner(OthelloImpl game) {
        int scoreBlack;
        int scoreWhite;

        showScores(game);
        scoreBlack = game.getPlayers().get(0).getScore();
        scoreWhite = game.getPlayers().get(1).getScore();

        if (scoreBlack > scoreWhite) {
            System.out.println("The winner is Black");
        } else if (scoreWhite > scoreBlack) {
            System.out.println("The winner is White");
        } else {
            System.out.println("The scores are equals, both wins !");
        }

    }

    static void showScores(OthelloImpl game) {
        System.out.println("Black: " + game.getPlayers().get(0).getScore());
        System.out.println("White: " + game.getPlayers().get(1).getScore());

    }
    
    static void askForACommand(OthelloImpl game) {
        String currentPlayer;
        currentPlayer = game.getCurrentColor().toString();
        currentPlayer =  currentPlayer.charAt(0) 
                         + currentPlayer.substring(1).toLowerCase();
                
        System.out.println(currentPlayer + " enter a command:");
    }
    
    static String newAction(OthelloImpl game) {
        String aCommand;
        
        setNewPossibilites(game);
        System.out.println("*-*-*-*-*-*-*-*-*-*");
        askForACommand(game);
        aCommand = Commands.getACommand();
        System.out.println("*-*-*-*-*-*-*-*-*-*");
        return aCommand;
    }

    private static void setNewPossibilites(OthelloImpl game) {
        game.cleanLastPlayerPossibilities();
        game.setPossiblePositions();
    }
}
