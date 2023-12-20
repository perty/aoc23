package se.artcomputer.aoc23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class App10_1 {
    public enum Tile {
        VERTICAL('|'),
        HORIZONTAL('-'),
        BEND_NE('L'),
        BEND_NW('J'),
        BEND_SW('7'),
        BEND_SE('F'),
        GROUND('.'),
        START('S');

        private final char symbol;

        Tile(char symbol) {
            this.symbol = symbol;
        }

        public static Tile fromChar(char symbol) {
            for (Tile tile : values()) {
                if (tile.symbol == symbol) {
                    return tile;
                }
            }
            throw new IllegalArgumentException("Invalid symbol: " + symbol);
        }
    }

    public static void main(String[] args) throws IOException {
        Tile[][] maze = parseInput("data/input10.txt");
        int steps = traverseMaze(maze);
        System.out.println("Steps: " + steps + " " + steps / 2);
    }

    private static Tile[][] parseInput(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        Tile[][] maze = new Tile[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            char[] chars = lines.get(i).toCharArray();
            maze[i] = new Tile[chars.length];
            for (int j = 0; j < chars.length; j++) {
                maze[i][j] = Tile.fromChar(chars[j]);
            }
        }
        return maze;
    }

    private static int traverseMaze(Tile[][] maze) {
        // Find the starting position
        int row = 0, col = 0;
        outer: for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == Tile.START) {
                    row = i;
                    col = j;
                    break outer;
                }
            }
        }

        // Directions: east 0, south 1, west 2, north 3
        int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int maxSteps = 0; // Keep track of the maximum number of steps
        // Determine the initial direction based on the neighboring tiles
        for (int i = 0; i < dirs.length; i++) {
            int newRow = row + dirs[i][0];
            int newCol = col + dirs[i][1];
            if (newRow >= 0 && newRow < maze.length && newCol >= 0 && newCol < maze[newRow].length
                    && maze[newRow][newCol] != Tile.GROUND) {
                int backDir = getPossibleDirections(maze[newRow][newCol], i);
                if (newRow + dirs[backDir][0] == row && newCol + dirs[backDir][1] == col) {
                    maxSteps = Math.max(maxSteps, traverse(maze, newRow, newCol, dirs, i));
                }
                int backDir2 = getPossibleDirections(maze[newRow][newCol], backDir);
                if (newRow + dirs[backDir2][0] == row && newCol + dirs[backDir2][1] == col) {
                    maxSteps = Math.max(maxSteps, traverse(maze, newRow, newCol, dirs, i));
                }
            }
        }

        // Traverse the maze until we reach the starting position again
        return maxSteps;
    }

    private static int traverse(Tile[][] maze, int startRow, int startCol, int[][] dirs, int dir) {
        int prevDir = dir; // Keep track of the previous direction
        int steps = 1; // Keep track of the number of steps
        int row = startRow;
        int col = startCol;
        while (true) {

            // Get the possible directions based on the current tile
            dir = getPossibleDirections(maze[row][col], (prevDir + 2) % 4);

            // Move in the current direction
            row += dirs[dir][0];
            col += dirs[dir][1];
            if (row < 0 || col < 0 || row >= maze.length || col >= maze[row].length || maze[row][col] == Tile.GROUND) {
                System.out.println("Out of bounds");
                break;
            }
            steps++;

            // If we've reached the starting position again, stop
            if (maze[row][col] == Tile.START) {
                break;
            }

            // Update the previous direction
            prevDir = dir;
        }

        return steps;
    }

    private static int getPossibleDirections(Tile tile, int exclude) {
        return switch (tile) {
            case VERTICAL -> exclude == 1 ? 3 : 1;
            case HORIZONTAL -> exclude == 0 ? 2 : 0;
            case BEND_NE -> exclude == 3 ? 0 : 3;
            case BEND_NW -> exclude == 3 ? 2 : 3;
            case BEND_SW -> exclude == 2 ? 1 : 2;
            case BEND_SE -> exclude == 0 ? 1 : 0;
            default -> 1;
        };
    }

}
