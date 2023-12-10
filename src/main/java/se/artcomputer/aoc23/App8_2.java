package se.artcomputer.aoc23;

import java.io.IOException;
import java.util.*;
import java.nio.file.*;

public class App8_2 {
    private static class Node {
        String left;
        String right;

        Node(String left, String right) {
            this.left = left;
            this.right = right;
        }
    }

    private record Input(Map<String, Node> nodes, String instructions) {
    }

    public static void main(String[] args) throws Exception {
        Input input = readInput();

        List<Long> cycleLengths = new ArrayList<>();
        for (String nodeName : input.nodes().keySet()) {
            if (!nodeName.endsWith("A")) {
                continue;
            }
            Set<String> visitedStates = new HashSet<>();
            long stepCount = 0;
            int instructionIndex = 0;
            String currentNode = nodeName;
            while (true) {
                String state = currentNode + "-" + instructionIndex;  // Include the instruction index in the state
                if (currentNode.endsWith("Z")) {
                    break;
                }
                if (visitedStates.contains(state)) {
                    break;
                }
                visitedStates.add(state);
                Node node = input.nodes().get(currentNode);
                currentNode = input.instructions().charAt(instructionIndex) == 'L' ? node.left : node.right;
                stepCount++;
                instructionIndex = (instructionIndex + 1) % input.instructions().length();
            }
            if (currentNode.endsWith("Z")) {
                cycleLengths.add(stepCount);
            }
        }

        long lcm = cycleLengths.get(0);
        for (int i = 1; i < cycleLengths.size(); i++) {
            lcm = lcm(lcm, cycleLengths.get(i));
        }

        System.out.println(lcm);
    }

    private static Input readInput() throws IOException {
        Map<String, Node> nodes = new HashMap<>();
        String instructions = "";
        List<String> lines = Files.readAllLines(Paths.get("data/input8.txt"));

        for (String line : lines) {
            if (line.contains("=")) {
                String[] parts = line.split(" = ");
                String nodeName = parts[0];
                String[] nodeParts = parts[1].substring(1, parts[1].length() - 1).split(", ");
                nodes.put(nodeName, new Node(nodeParts[0], nodeParts[1]));
            } else {
                if (!line.isEmpty()) {
                    instructions = line;
                }
            }
        }
        return new Input(nodes, instructions);
    }

    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    private static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }
}
