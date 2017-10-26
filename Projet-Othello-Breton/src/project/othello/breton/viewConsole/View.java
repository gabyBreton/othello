package project.othello.breton.viewConsole;

import projet.othello.breton.model.Color;
import projet.othello.breton.model.OthelloImpl;

/**
 * This class provides methods related to the console view of the game.
 *
 * @author Gabriel Breton - 43397
 */
public class View {

    public static void showGameBoard(OthelloImpl game) {
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

            if ((i + 1) / 10 == 0) {
                System.out.print("  " + (i + 1) + " ");
            } else if ((i+1) / 10 >= 1) {
                System.out.print(" " + (i + 1) + " ");
            } 

            for (int j = 0; j < game.getHeight(); j++) {
                colorCell = game.getColor(i, j);
                if (colorCell == null) {
                    System.out.print(" . ");
                } else if (colorCell == Color.BLACK) {
                    System.out.print(" B ");
                } else {
                    System.out.print(" W ");
                }
            }
            System.out.println("");
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

    public static void showFinalScoresAndWinner(OthelloImpl game) {
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

    public static void showScores(OthelloImpl game) {
        System.out.println("Black: " + game.getPlayers().get(0).getScore());
        System.out.println("White: " + game.getPlayers().get(1).getScore());

    }
}
