package se.artcomputer.aoc23;

import java.util.*;
import java.util.regex.*;
import java.nio.file.*;

public class App2_1 {
    public static void main(String[] args) {
        try {
            String input = new String(Files.readAllBytes(Paths.get("data/input2.txt")));
            System.out.println(sumOfPossibleGameIDs(input));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int sumOfPossibleGameIDs(String input) {
        String[] games = input.split("\n");
        Pattern gamePattern = Pattern.compile("Game (\\d+): (.+)");
        Pattern setPattern = Pattern.compile("(\\d+) (red|green|blue)");
        int sum = 0;
        for (String game : games) {
            Matcher gameMatcher = gamePattern.matcher(game);
            if (gameMatcher.matches()) {
                int gameID = Integer.parseInt(gameMatcher.group(1));
                String[] sets = gameMatcher.group(2).split("; ");
                boolean possible = true;
                for (String set : sets) {
                    Matcher setMatcher = setPattern.matcher(set);
                    int red = 0, green = 0, blue = 0;
                    while (setMatcher.find()) {
                        int count = Integer.parseInt(setMatcher.group(1));
                        String color = setMatcher.group(2);
                        if (color.equals("red")) {
                            red = Math.max(red, count);
                        } else if (color.equals("green")) {
                            green = Math.max(green, count);
                        } else if (color.equals("blue")) {
                            blue = Math.max(blue, count);
                        }
                    }
                    if (red > 12 || green > 13 || blue > 14) {
                        possible = false;
                        break;
                    }
                }
                if (possible) {
                    sum += gameID;
                }
            }
        }
        return sum;
    }
}
