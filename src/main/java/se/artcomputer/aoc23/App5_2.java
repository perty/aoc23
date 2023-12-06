package se.artcomputer.aoc23;

import java.util.*;
import java.nio.file.*;
import java.io.*;

public class App5_2 {
    public static void main(String[] args) throws IOException {
        List<long[]> seedRanges = new ArrayList<>();
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
                    currentMapping = new TreeMap<>();  // Use TreeMap to keep entries sorted
                } else if (line.startsWith("seeds: ")) {
                    String[] parts = line.substring(7).split(" ");
                    for (int i = 0; i < parts.length; i += 2) {
                        long start = Long.parseLong(parts[i]);
                        long length = Long.parseLong(parts[i + 1]);
                        seedRanges.add(new long[]{start, length});
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

        System.out.println(solve(seedRanges, mappings));  // Output: 35
    }

    private static long mapValue(long value, Map<Long, long[]> mapping) {
        // Use binary search to find the mapping that applies to the value
        Map.Entry<Long, long[]> entry = ((TreeMap<Long, long[]>) mapping).floorEntry(value);
        if (entry != null) {
            long sourceStart = entry.getKey();
            long destStart = entry.getValue()[0];
            long length = entry.getValue()[1];
            if (sourceStart <= value && value < sourceStart + length) {
                return destStart + (value - sourceStart);
            }
        }
        return value;
    }

    private static long solve(List<long[]> seedRanges, List<Map<Long, long[]>> mappings) {
        long minLocation = Long.MAX_VALUE;
        for (long[] range : seedRanges) {
            for (long seed = range[0]; seed < range[0] + range[1]; seed++) {
                long value = seed;
                for (Map<Long, long[]> mapping : mappings) {
                    value = mapValue(value, mapping);
                }
                minLocation = Math.min(minLocation, value);
            }
        }
        return minLocation;
    }
}
