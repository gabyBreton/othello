package project.othello.breton.viewConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides methods to manage the command-line interactions with the
 * user.
 *
 * @author Gabriel Breton - 43397
 */
class Commands {

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
    private static boolean isCmdMatching(List<Pattern> patterns, String command){
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
    static int getCommandId(String[] cmdSplitted)
            throws IllegalArgumentException {
        int cmdId;

        if (cmdSplitted[0].matches("show")) {
            cmdId = 1;
        } else if (cmdSplitted[0].matches("score")) {
            cmdId = 2;
        } else if (cmdSplitted[0].matches("help")) {
            cmdId = 3;
        } else if (cmdSplitted[0].matches("play")) {
            cmdId = 4;
        } else {
            throw new IllegalArgumentException("Unrecognized command. Should be"
                                    + " 'show', 'score', 'help' or 'play x y'");
        }
        return cmdId;
    }
    

}
