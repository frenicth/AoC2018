import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest {

    private static Application classUnderTest;

    @BeforeAll
    static void init() {
        classUnderTest = new Application();
    }

    @Test
    void testChunk1() {
        int actual = classUnderTest.countCorruptScore(")");
        assertThat(actual).isEqualTo(3);
    }

    @Test
    void testChunk2() {
        int actual = classUnderTest.countCorruptScore("]");
        assertThat(actual).isEqualTo(57);
    }

    @Test
    void testChunk3() {
        int actual = classUnderTest.countCorruptScore("}");
        assertThat(actual).isEqualTo(1197);
    }

    @Test
    void testChunk4() {
        int actual = classUnderTest.countCorruptScore(">");
        assertThat(actual).isEqualTo(25137);
    }

}