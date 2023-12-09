package se.artcomputer.aoc23;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class App7_2Test {

    @ParameterizedTest
    @CsvSource({
        "JJ222, 7", // Five of a kind, with jokers.
        "22222, 7", // Five of a kind, with no jokers.
        "JJ2K2, 6", // Four of a kind, with jokers.
        "222K2, 6", // Four of a kind, with no jokers.
        "222KK, 5", // Full house with no jokers.
        "J22KK, 5", // Full house with jokers.
        "JJ234, 4", // Three of a kind, with 2 jokers.
        "J2234, 4", // Three of a kind, with 1 joker.
        "22234, 4", // Three of a kind, with no joker.
        "J2345, 2", // One pair with a joker.
        "22334, 3", // Two pairs.
        "23456, 1"  // High card.
    })
    void testHandStrength(String cards, int expectedStrength) {
        App7_2.Hand hand = new App7_2.Hand(cards, 1);
        int actualStrength = hand.handStrength();
        assertEquals(expectedStrength, actualStrength, "Test failed for hand " + cards);
    }
}
