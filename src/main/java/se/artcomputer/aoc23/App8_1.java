package se.artcomputer.aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App8_1 {
    private static class Node {
        String left;
        String right;

        Node(String left, String right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
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
        String currentNode = "AAA";
        int stepCount = 0;
        int instructionIndex = 0;
        while (!currentNode.equals("ZZZ")) {
            Node node = nodes.get(currentNode);
            if (instructions.charAt(instructionIndex) == 'L') {
                currentNode = node.left;
            } else {
                currentNode = node.right;
            }
            stepCount++;
            instructionIndex = (instructionIndex + 1) % instructions.length();
        }
        System.out.println(stepCount);
    }
}
