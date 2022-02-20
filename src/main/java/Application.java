import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Application {

    public static final String OPEN_TOKENS = "([{<";
    public static final String CLOSE_TOKENS = ")]}>";
    public static final boolean PART_ONE = true;
    public static final boolean PART_TWO = false;

    public static void main(String args[]) {
        System.out.println("Hello world!");
    }

    /**
     * Calculate the score for all broken sequences in a list of chunks
     * @param linesOfChunks
     * @return score for the given list
     */
    int scoreForBrokenLinesOfChunks(List<String> linesOfChunks) {
        int totalScore = 0;
        for (String lineOfChunks: linesOfChunks) {
            totalScore += scoreForLineOfChunks(lineOfChunks, PART_ONE);
        }
        return totalScore;
    }

    /**
     * Calculate the score for all incomplete sequences in a list of chunks
     * @param linesOfChunks
     * @return score for the given list
     */
    Long scoreForIncompleteLinesOfChunks(List<String> linesOfChunks) {
        List<Long> totalScore = new ArrayList<>();
        for (String lineOfChunks: linesOfChunks) {
            if (scoreForLineOfChunks(lineOfChunks, PART_TWO) != 0) {
                totalScore.add(scoreForLineOfChunks(lineOfChunks, PART_TWO));
            }
        }
        Collections.sort(totalScore);
        return totalScore.get(totalScore.size()/2);
    }

    /**
     * Calculate the score for a line of chunks.
     * @param lineOfChunks line of chunks to calculate score for
     * @param partOne boolean to toggle calculation of incomplete or broken sequences
     * @return score for the given list and partOne
     */
    // TODO: Refactoring this to get rid of the boolean flag, it's error prone.
    long scoreForLineOfChunks(String lineOfChunks, boolean partOne) {

        Stack<String> stack = new Stack<>();
        boolean brokenSequence = false;
        long score = 0;
        long autoCompleteScore = 0;
        for (int i = 0; i < lineOfChunks.length() && !brokenSequence; i++) {
            String token = String.valueOf(lineOfChunks.charAt(i));
            if (OPEN_TOKENS.contains(token)) {
                stack.push(token);
            } else {
                int closeTokenIndex = CLOSE_TOKENS.indexOf(token);
                String lastOpenToken = stack.pop();
                if (!lastOpenToken.equals(String.valueOf(OPEN_TOKENS.charAt(closeTokenIndex)))) {
                    brokenSequence = true;
                    score = scoreForInvalidClosingToken(token.charAt(0));
                }
            }
        }

        if (stack.size() != 0 && !brokenSequence) {
            int stackSize = stack.size();
            for (int i = 0; i < stackSize; i++) {
                int openTokenIndex = OPEN_TOKENS.indexOf(stack.pop());
                String closeToken = String.valueOf(CLOSE_TOKENS.charAt(openTokenIndex));
                autoCompleteScore *= 5;
                autoCompleteScore += scoreForMissingClosingToken(closeToken.charAt(0));
            }
        }
        if (!partOne) {
            score = autoCompleteScore;
        }
        return score;
    }

    int scoreForInvalidClosingToken(char token) {
        int score = 0;
        switch (token) {
            case ')': {
                score = 3;
                break;
            }
            case ']': {
                score = 57;
                break;
            }
            case '}': {
                score = 1197;
                break;
            }
            case '>': {
                score = 25137;
            }
        }

        return score;
    }

    int scoreForMissingClosingToken(char token) {
        int score = 0;
        switch (token) {
            case ')': {
                score = 1;
                break;
            }
            case ']': {
                score = 2;
                break;
            }
            case '}': {
                score = 3;
                break;
            }
            case '>': {
                score = 4;
            }
        }

        return score;
    }
}
