package se.artcomputer.aoc23;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class App1_2Test {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testProcessLine(String input, String expectedOutput) {
        String actualOutput = App1_2.processLine(input);
        assertEquals(expectedOutput, actualOutput);
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of("two1nine", "29"),
                Arguments.of("eightwothree", "83"),
                Arguments.of("abcone2threexyz", "13"),
                Arguments.of("xtwone3four", "24"),
                Arguments.of("4nineeightseven2", "42"),
                Arguments.of("zoneight234", "14"),
                Arguments.of("eightwo", "82"),
                Arguments.of("7pqrstsixteen", "76")
        // Add more test cases here
        );
    }
}
