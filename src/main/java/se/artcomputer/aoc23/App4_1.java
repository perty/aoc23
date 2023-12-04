package se.artcomputer.aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class App4_1 {
    public static void main(String[] args) {
        try {
            int totalPoints = 0;
            for (String line : Files.readAllLines(Paths.get("data/input4.txt"))) {
                String[] cardParts = line.split(": ");
                String[] parts = cardParts[1].split(" \\| ");
                Set<Integer> winningNumbers = new HashSet<>();
                for (String number : parts[0].split(" ")) {
                    if (!number.isEmpty()) {
                        winningNumbers.add(Integer.parseInt(number));
                    }
                }
                Set<Integer> yourNumbers = new HashSet<>();
                for (String number : parts[1].split(" ")) {
                    if (!number.isEmpty()) {
                        yourNumbers.add(Integer.parseInt(number));
                    }
                }
                int matches = 0;
                for (int number : yourNumbers) {
                    if (winningNumbers.contains(number)) {
                        matches++;
                    }
                }
                int points = matches > 0 ? 1 << (matches - 1) : 0;
                totalPoints += points;
            }
            System.out.println(totalPoints);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
