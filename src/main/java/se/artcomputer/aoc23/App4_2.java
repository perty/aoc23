package se.artcomputer.aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App4_2 {
    public static void main(String[] args) {
        try {
            List<Set<Integer>> winningNumbersList = new ArrayList<>();
            List<Set<Integer>> yourNumbersList = new ArrayList<>();
            for (String line : Files.readAllLines(Paths.get("data/input4.txt"))) {
                String[] cardParts = line.split(": ");
                String[] parts = cardParts[1].split(" \\| ");
                Set<Integer> winningNumbers = new HashSet<>();
                for (String number : parts[0].split(" ")) {
                    if (!number.isEmpty()) {
                        winningNumbers.add(Integer.parseInt(number));
                    }
                }
                winningNumbersList.add(winningNumbers);
                Set<Integer> yourNumbers = new HashSet<>();
                for (String number : parts[1].split(" ")) {
                    if (!number.isEmpty()) {
                        yourNumbers.add(Integer.parseInt(number));
                    }
                }   
                yourNumbersList.add(yourNumbers);
            }
            int[] copies = new int[winningNumbersList.size()];
            for (int i = 0; i < copies.length; i++) {
                copies[i] = 1;
            }
            for (int i = 0; i < copies.length; i++) {
                int matches = 0;
                for (int number : yourNumbersList.get(i)) {
                    if (winningNumbersList.get(i).contains(number)) {
                        matches++;
                    }
                }
                for (int j = i + 1; j < i + 1 + matches && j < copies.length; j++) {
                    copies[j] += copies[i];
                }
            }
            int totalCards = 0;
            for (int copy : copies) {
                totalCards += copy;
            }
            System.out.println(totalCards);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}