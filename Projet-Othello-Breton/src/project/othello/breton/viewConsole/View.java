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
        for(int i = 0; i < (widht * 3) + 2; i++) {
            System.out.print("=");
        }

        System.out.println("");
        System.out.print("    ");
        for(int i = 0; i < widht; i++) {
            System.out.print(" " + i + " ");
        }
        
        System.out.println("|");
    }

    private static void displayContent(OthelloImpl game) {
        Color colorCell;

        for (int i = 0; i < game.getWidht(); i++) {
            
            if (i / 10 == 0) {
                System.out.print("  " + i + " ");
            } else if (i / 10 >= 1 && i / 10 < 10){
                System.out.print(" " + i + " ");
            } else {
                System.out.print(i + " ");
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
            System.out.println("|");
        }
    }

    private static void displayFooter(int widht) {
        System.out.print("    ");
        for(int i = 0; i < widht; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println("|");

        System.out.print("  ");
        for(int i = 0; i < (widht * 3) + 2; i++) {
            System.out.print("=");
        }        
        System.out.println("");

    }
}
