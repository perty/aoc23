package se.artcomputer.aoc23;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Map;

public class App1_2 {
    private static final Pattern PATTERN = Pattern.compile("\\d");
    private static final Map<String, String> DIGIT_MAP = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9");

    public static void main(String[] args) {
        long[] sum = { 0 };

        try {
            Files.lines(Paths.get("data/input1.txt")).forEach(line -> {
                String calibrationValue = processLine(line);
                sum[0] += Integer.parseInt(calibrationValue);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(sum[0]);
    }

    public static String processLine(String line) {
        String result = replaceDigits(line);
        return getFirstAndLastDigit(result);
    }

    private static String replaceDigits(String line) {
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            boolean replaced = false;
            for (Map.Entry<String, String> entry : DIGIT_MAP.entrySet()) {
                if (line.startsWith(entry.getKey(), i)) {
                    newStr.append(entry.getValue());
                    replaced = true;
                    break;
                }
            }
            if (!replaced) {
                newStr.append(line.charAt(i));
            }
        }
        return newStr.toString();
    }

    private static String getFirstAndLastDigit(String result) {
        Matcher matcher = PATTERN.matcher(result);
        String firstDigit = null;
        String lastDigit = null;
        while (matcher.find()) {
            if (firstDigit == null) {
                firstDigit = matcher.group();
            }
            lastDigit = matcher.group();
        }
        if (firstDigit != null) {
            return firstDigit + lastDigit;
        }
        return "0";
    }
}
