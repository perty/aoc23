package se.artcomputer.aoc23;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class App1_1 {
    public static void main(String[] args) {
        int[] sum = {0};
        Pattern pattern = Pattern.compile("\\d");

        try {
            Files.lines(Paths.get("data/input1.txt")).forEach(line -> {
                Matcher matcher = pattern.matcher(line);
                String firstDigit = null;
                String lastDigit = null;
                while (matcher.find()) {
                    if (firstDigit == null) {
                        firstDigit = matcher.group();
                    }
                    lastDigit = matcher.group();
                }
                if (firstDigit != null) {
                    if (lastDigit == null) {
                        lastDigit = firstDigit; // If there is only one digit, duplicate it
                    }
                    String calibrationValue = firstDigit + lastDigit;
                    sum[0] += Integer.parseInt(calibrationValue);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(sum[0]);
    }
}
