package se.artcomputer.aoc23;

import java.util.*;
import java.nio.file.*;
import java.io.*;

public class App5_1 {
    public static void main(String[] args) throws IOException {
        List<Long> seeds = new ArrayList<>();
        List<Map<Long, long[]>> mappings = new ArrayList<>();

        Path path = Paths.get("data/input5.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            Map<Long, long[]> currentMapping = null;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    if (currentMapping != null) {
                        mappings.add(currentMapping);
                        currentMapping = null;
                    }
                } else if (line.endsWith("map:")) {
                    currentMapping = new HashMap<>();
                } else if (line.startsWith("seeds: ")) {
                    String[] parts = line.substring(7).split(" ");
                    for (String part : parts) {
                        seeds.add(Long.parseLong(part));
                    }
                } else if (currentMapping != null) {
                    String[] parts = line.split(" ");
                    long destStart = Long.parseLong(parts[0]);
                    long sourceStart = Long.parseLong(parts[1]);
                    long length = Long.parseLong(parts[2]);
                    currentMapping.put(sourceStart, new long[]{destStart, length});
                }
            }
            if (currentMapping != null) {
                mappings.add(currentMapping);
            }
        }

        System.out.println(solve(seeds, mappings));  // Output: 35
    }

    private static long mapValue(long value, Map<Long, long[]> mapping) {
        for (Map.Entry<Long, long[]> entry : mapping.entrySet()) {
            long sourceStart = entry.getKey();
            long destStart = entry.getValue()[0];
            long length = entry.getValue()[1];
            if (sourceStart <= value && value < sourceStart + length) {
                return destStart + (value - sourceStart);
            }
        }
        return value;
    }

    private static long solve(List<Long> seeds, List<Map<Long, long[]>> mappings) {
        long minLocation = Long.MAX_VALUE;
        for (long seed : seeds) {
            long value = seed;
            for (Map<Long, long[]> mapping : mappings) {
                value = mapValue(value, mapping);
            }
            minLocation = Math.min(minLocation, value);
        }
        return minLocation;
    }
}