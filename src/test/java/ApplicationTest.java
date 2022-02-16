import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {

    private static Application classUnderTest;

    @BeforeAll
    static void init() {
        classUnderTest = new Application();
    }

    @ParameterizedTest
    @MethodSource("invalidToken")
    void testCountTokenScore(char input, int expected) {
        int actual = classUnderTest.countTokenScore(input);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("tokenSequences")
    void testCountTokenScore(String input, int expected) {
        int actual = classUnderTest.countTokenSequenceScore(input);
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> invalidToken() {
        return Stream.of(
                Arguments.of(')', 3),
                Arguments.of(']', 57),
                Arguments.of('}', 1197),
                Arguments.of('>', 25137)

        );
    }

    private static Stream<Arguments> tokenSequences() {
        return Stream.of(
                Arguments.of("[[<[([]))<([[{}[[()]]]", 3),
                Arguments.of("[{[{({}]{}}([{[{{{}}([]", 57),
                Arguments.of("{([(<{}[<>[]}>{[]{[(<()>", 1197),
                Arguments.of("<{([([[(<>()){}]>(<<{{", 25137)

        );
    }
}