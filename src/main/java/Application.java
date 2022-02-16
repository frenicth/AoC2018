import java.util.List;
import java.util.Stack;

public class Application {

    public static final String OPEN_TOKENS = "([{<";
    public static final String CLOSE_TOKENS = ")]}>";

    public static void main(String args[]) {
        System.out.println("Hello world!");
    }

    int scoreForLinesOfChunks(List<String> linesOfChunks) {
        int totalScore = 0;
        for (String lineOfChunks: linesOfChunks) {
            totalScore += scoreForLineOfChunks(lineOfChunks);
        }
        return totalScore;
    }

    int scoreForLineOfChunks(String lineOfChunks) {

        Stack<String> stack = new Stack<>();
        boolean brokenSequence = false;
        int score = 0;
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
}
