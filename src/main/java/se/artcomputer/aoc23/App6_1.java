package se.artcomputer.aoc23;

import java.util.*;
import java.nio.file.*;
import java.io.*;

public class App6_1 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("data/input6.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String timeLine = reader.readLine();
            String distanceLine = reader.readLine();

            String[] timeParts = timeLine.split("\\s+");
            String[] distanceParts = distanceLine.split("\\s+");

            long totalWays = 1;
            for (int i = 1; i < Math.min(timeParts.length, distanceParts.length); i++) {
                if (timeParts[i].isEmpty() || distanceParts[i].isEmpty()) {
                    continue;
                }
                int time = Integer.parseInt(timeParts[i]);
                int record = Integer.parseInt(distanceParts[i]);
                int ways = 0;
                for (int holdTime = 0; holdTime <= time; holdTime++) {
                    int distance = holdTime * (time - holdTime);
                    if (distance > record) {
                        ways++;
                    }
                }
                totalWays *= ways;
            }
            System.out.println(totalWays);
        }
    }
}
