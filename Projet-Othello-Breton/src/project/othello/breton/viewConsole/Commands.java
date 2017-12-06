package project.othello.breton.viewConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import project.othello.breton.model.OthelloImpl;

/**
 * This class provides methods to manage the command-line interactions with the
 * user.
 *
 * @author Gabriel Breton - 43397
 */
class Commands {

    /**
     * Takes an input from the user.
     *
     * @return the input of the user.
     */
    static String getACommand() {
        Scanner keybd = new Scanner(System.in);
        return keybd.nextLine();
    }

    /**
     * Verify the validity of a given command.
     *
     * @param command the command that the user have entered.
     * @return true if the command is valid, else false. A command is valid if
     * we can use it to show the board, show the score, play a move or display
     * the help.
     */
    static boolean verifyCommand(String command) {
        boolean findMatch;

        List<Pattern> patterns = creatPatternList();
        findMatch = isCmdMatching(patterns, command);

        return findMatch;
    }

    /**
     * Create a list with all the regex patterns for the commands matching
     * verification.
     *
     * @return a list with all the patterns.
     */
    private static List<Pattern> creatPatternList() {

        List<Pattern> patterns = new ArrayList<>();
        patterns.add(Pattern.compile("show[\\s]*"));
        patterns.add(Pattern.compile("score[\\s]*"));
        patterns.add(Pattern.compile("^play[\\s][\\d]+[\\s][a-z][\\s]*$"));
        patterns.add(Pattern.compile("^wall[\\s][\\d]+[\\s][a-z][\\s]*$"));
        patterns.add(Pattern.compile("help[\\s]*"));

        return patterns;
    }

    /**
     * Verify if a given command matches with an expected command value.
     *
     * @param patterns the differents patterns of regex to verify with the
     * command.
     * @param command the command to verify the matching.
     * @return true if the command matches with one of the patterns, or else
     * false.
     */
    private static boolean isCmdMatching(List<Pattern> patterns,
            String command) {
        boolean findMatch;
        findMatch = false;

        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                findMatch = true;
                break;
            }
        }
        return findMatch;
    }

    /**
     * Assign an ID for each type of command. As types of command, we have:
     * 'show', 'help', 'score' and 'play'.
     *
     * @param cmdSplitted An array containing all the words (non-whitespace) of
     * the command.
     * @return an ID that correspond to a command.
     * @throws IllegalArgumentException if the command is not recognized, such
     * as it is a bad wrong shape or wrong command type.
     */
    static int compareAndAssignID(String[] cmdSplitted)
            throws IllegalArgumentException {
        int cmdId;

        switch (cmdSplitted[0]) {
            case "show":
                cmdId = 1;
                break;
            case "score":
                cmdId = 2;
                break;
            case "help":
                cmdId = 3;
                break;
            case "play":
                cmdId = 4;
                break;
            case "wall":
                cmdId = 5;
                break;
            default:
                throw new IllegalArgumentException("Unrecognized command. "
                        + "Should be 'show', 'score', 'help' or 'play x y'");
        }
        return cmdId;
    }

    /**
     * Verifies if the value of the x axis of play is not too long. Display an
     * error message if the value is too long.
     *
     * @param command the command to verify.
     * @param game the current game session.
     * @return true if the value is not too long, or else false and display an
     * error message.
     */
    static boolean areCmdValuesCorrect(String command, OthelloImpl game) {
        boolean correctValue;
        String[] cmdSplitted;

        cmdSplitted = command.split(" ");
        correctValue = true;
        if (cmdSplitted[0].equals("play")
                || cmdSplitted[0].equals("wall")) {
            correctValue = verifyValuePlayWall(game, command);
        }

        return correctValue;
    }

    /**
     * Verifies if the value of X in the play command is valid.
     *
     * @param game the current session of Othello.
     * @param command the command to verify.
     * @return true if the value of X is correct, or else false.
     *
     * @throws NumberFormatException
     */
    private static boolean verifyValuePlayWall(OthelloImpl game, String command)
            throws NumberFormatException {
        String[] cmdSplitted;
        boolean correctValue;
        int xMove, yMove;
        correctValue = true;
        cmdSplitted = command.split(" ");

        xMove = Integer.parseInt(cmdSplitted[1]);
        yMove = convertLetterToNumber(command);
        if ((xMove - 1) >= game.getHeight()
                || yMove >= game.getWidth()) {
            View.displayTooLargeValue(command);
            correctValue = false;
        }

        return correctValue;
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

    /**
     * Finds the ID of a command.
     *
     * @param command the command to check.
     * @param game the current game session.
     * @return the ID of the given command.
     */
    static int findCommandID(String command, OthelloImpl game) {
        String[] cmdSplitted;
        int cmdId;

        cmdSplitted = command.split(" ");
        cmdId = Commands.compareAndAssignID(cmdSplitted);
        return cmdId;
    }

    /**
     * Ask the user to enter an odd integer.
     *
     * @param max the maximal limit of the integer asked.
     * @param min the minimal limit of the integer asked.
     * @return the accepted odd integer.
     */
    static int verifyIntInput(int max, int min) {
        Scanner keybd = new Scanner(System.in);
        int theInput;
        theInput = -1;

        while (theInput > max || theInput < min || theInput % 2 != 0) {
            System.out.print("Enter odd integer between " + min + " and "
                    + max + ": ");
            while (!keybd.hasNextInt()) {
                System.out.print("That's not an integer. Retry : ");
                keybd.next();
            }
            theInput = keybd.nextInt();
        }

        return theInput;
    }
}
