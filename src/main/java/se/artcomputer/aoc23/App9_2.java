package se.artcomputer.aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App9_2 {
    public static void main(String[] args) throws IOException {
        List<List<Long>> histories = readInput("data/input9.txt");
        long sum = 0;
        for (List<Long> history : histories) {
            long extrapolateNextValue = extrapolateValue(history);
            System.out.println("Extrapolated value: " + extrapolateNextValue);
            sum += extrapolateNextValue;
        }
        System.out.println("Sum of extrapolated values: " + sum);
    }

    private static long extrapolateValue(List<Long> history) {
        List<List<Long>> differences = new ArrayList<>();
        differences.add(history);
        while (!differences.get(differences.size() - 1).stream().allMatch(i -> i == 0)) {
            differences.add(calculateDifferences(differences.get(differences.size() - 1)));
        }
        for (int i = differences.size() - 2; i >= 0; i--) {
            List<Long> current = differences.get(i);
            List<Long> next = differences.get(i + 1);
            current.add(0, current.get(0) - next.get(0));
            current.add(current.get(current.size() - 1) + next.get(next.size() - 1));
        }
        return differences.get(0).get(0);
    }

    private static List<Long> calculateDifferences(List<Long> list) {
        List<Long> differences = new ArrayList<>();
        for (int i = 0; i < list.size() - 1; i++) {
            differences.add(list.get(i + 1) - list.get(i));
        }
        return differences;
    }

    private static List<List<Long>> readInput(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName))
            .map(line -> line.split("\\s+"))
            .map(array -> Arrays.stream(array).map(Long::parseLong).collect(Collectors.toList()))
            .collect(Collectors.toList());
    }
}
