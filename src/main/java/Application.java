import java.util.List;
import java.util.Stack;

public class Application {

    public static final String OPEN_TOKENS = "([{<";
    public static final String CLOSE_TOKENS = ")]}>";

    public static void main(String args[]) {
        System.out.println("Hello world!");
    }

    int countCorruptScore(String lineOfChunks) {

        Stack stack = new Stack();
        for (int i = 0; i < lineOfChunks.length(); i++) {

        }
        return 0;
    }

    int countTokenScore(char token) {
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
