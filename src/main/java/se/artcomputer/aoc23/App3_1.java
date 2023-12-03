package se.artcomputer.aoc23;

import java.nio.file.*;
import java.io.IOException;
import java.util.regex.*;

public class App3_1 {
    public static void main(String[] args) {
        try {
            String input = new String(Files.readAllBytes(Paths.get("data/input3.txt")));
            System.out.println(sumOfPartNumbers(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int sumOfPartNumbers(String input) {
        String[] lines = input.split("\n");
        int sum = 0;
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                if (Character.isDigit(lines[i].charAt(j))) {
                    int numEnd = j;
                    while (numEnd < lines[i].length() && Character.isDigit(lines[i].charAt(numEnd))) {
                        numEnd++;
                    }
                    String number = lines[i].substring(j, numEnd);
                    for (int k = j; k < numEnd; k++) {
                        if (hasNeighboringSymbol(lines, i, k)) {
                            sum += Integer.parseInt(number);
                            break;
                        }
                    }
                    j = numEnd - 1;  // Skip the rest of this number
                }
            }
        }
        return sum;
    }

    public static boolean hasNeighboringSymbol(String[] lines, int i, int j) {
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                int ni = i + di, nj = j + dj;
                if (ni >= 0 && ni < lines.length && nj >= 0 && nj < lines[ni].length() && !Character.isDigit(lines[ni].charAt(nj)) && lines[ni].charAt(nj) != '.') {
                    return true;
                }
            }
        }
        return false;
    }
}
