package se.artcomputer.aoc23;

import java.util.*;
import java.nio.file.*;
import java.io.*;

public class App6_2 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("data/input6.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String timeLine = reader.readLine().replaceAll("\\s+|Time:", "");
            String distanceLine = reader.readLine().replaceAll("\\s+|Distance:", "");

            long time = Long.parseLong(timeLine);
            long record = Long.parseLong(distanceLine);

            long ways = 0;
            for (long holdTime = 0; holdTime <= time; holdTime++) {
                long distance = holdTime * (time - holdTime);
                if (distance > record) {
                    ways++;
                }
            }
            System.out.println(ways);
        }
    }
}