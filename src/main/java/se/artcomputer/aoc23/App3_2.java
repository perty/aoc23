package se.artcomputer.aoc23;

import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class App3_2 {
    public static void main(String[] args) {
        try {
            String input = new String(Files.readAllBytes(Paths.get("data/input3.txt")));
            System.out.println(sumOfGearRatios(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int sumOfGearRatios(String input) {
        String[] lines = input.split("\n");
        int sum = 0;
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                if (lines[i].charAt(j) == '*') {
                    List<Integer> neighboringNumbers = getNeighboringNumbers(lines, i, j);
                    System.out.println(neighboringNumbers);
                    if (neighboringNumbers.size() == 2) {
                        sum += neighboringNumbers.get(0) * neighboringNumbers.get(1);
                    }
                }
            }
        }
        return sum;
    }

    public static List<Integer> getNeighboringNumbers(String[] lines, int i, int j) {
        Set<Integer> numbers = new HashSet<>();
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } }; // Up, down, left, right, up-left, up-right, down-left, down-right
        for (int[] dir : directions) {
            int ni = i + dir[0], nj = j + dir[1];
            if (ni >= 0 && ni < lines.length && nj >= 0 && nj < lines[ni].length() && Character.isDigit(lines[ni].charAt(nj))) {
                // Move left until we hit a non-digit or the start of the line
                while (nj > 0 && Character.isDigit(lines[ni].charAt(nj - 1))) {
                    nj--;
                }
                // Read the number from left to right
                StringBuilder number = new StringBuilder();
                while (nj < lines[ni].length() && Character.isDigit(lines[ni].charAt(nj))) {
                    number.append(lines[ni].charAt(nj));
                    nj++;
                }
                numbers.add(Integer.parseInt(number.toString()));
            }
        }
        return new ArrayList<>(numbers);
    }
}
