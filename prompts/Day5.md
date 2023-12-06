# Day 5

## Part 1

### GitHub Copilot

Absolutely! Please share the details of the new puzzle and I'll do my best to assist you.

### perty

The almanac (your puzzle input) lists all of the seeds that need to be planted. It also lists what type of soil to use with each kind of seed, what type of fertilizer to use with each kind of soil, what type of water to use with each kind of fertilizer, and so on. Every type of seed, soil, fertilizer and so on is identified with a number, but numbers are reused by each category - that is, soil 123 and fertilizer 123 aren't necessarily related to each other.

For example:

seeds: 79 14 55 13

seed-to-soil map: 50 98 2 52 50 48

soil-to-fertilizer map: 0 15 37 37 52 2 39 0 15

fertilizer-to-water map: 49 53 8 0 11 42 42 0 7 57 7 4

water-to-light map: 88 18 7 18 25 70

light-to-temperature map: 45 77 23 81 45 19 68 64 13

temperature-to-humidity map: 0 69 1 1 0 69

humidity-to-location map: 60 56 37 56 93 4 

The almanac starts by listing which seeds need to be planted: seeds 79, 14, 55, and 13.

The rest of the almanac contains a list of maps which describe how to convert numbers from a source category into numbers in a destination category. That is, the section that starts with seed-to-soil map: describes how to convert a seed number (the source) to a soil number (the destination). This lets the gardener and his team know which soil to use with which seeds, which water to use with which fertilizer, and so on.

Rather than list every source number and its corresponding destination number one by one, the maps describe entire ranges of numbers that can be converted. Each line within a map contains three numbers: the destination range start, the source range start, and the range length.

Consider again the example seed-to-soil map:

50 98 2 52 50 48 The first line has a destination range start of 50, a source range start of 98, and a range length of 2. This line means that the source range starts at 98 and contains two values: 98 and 99. The destination range is the same length, but it starts at 50, so its two values are 50 and 51. With this information, you know that seed number 98 corresponds to soil number 50 and that seed number 99 corresponds to soil number 51.

The second line means that the source range starts at 50 and contains 48 values: 50, 51, ..., 96, 97. This corresponds to a destination range starting at 52 and also containing 48 values: 52, 53, ..., 98, 99. So, seed number 53 corresponds to soil number 55.

Any source numbers that aren't mapped correspond to the same destination number. So, seed number 10 corresponds to soil number 10.

So, the entire list of seed numbers and their corresponding soil numbers looks like this:

seed soil 0 0 1 1 ... ... 48 48 49 49 50 52 51 53 ... ... 96 98 97 99 98 50 99 51 With this map, you can look up the soil number required for each initial seed number:

Seed number 79 corresponds to soil number 81. Seed number 14 corresponds to soil number 14. Seed number 55 corresponds to soil number 57. Seed number 13 corresponds to soil number 13. The gardener and his team want to get started as soon as possible, so they'd like to know the closest location that needs a seed. Using these maps, find the lowest location number that corresponds to any of the initial seeds. To do this, you'll need to convert each seed number through other categories until you can find its corresponding location number. In this example, the corresponding types are:

Seed 79, soil 81, fertilizer 81, water 81, light 74, temperature 78, humidity 78, location 82. Seed 14, soil 14, fertilizer 53, water 49, light 42, temperature 42, humidity 43, location 43. Seed 55, soil 57, fertilizer 57, water 53, light 46, temperature 82, humidity 82, location 86. Seed 13, soil 13, fertilizer 52, water 41, light 34, temperature 34, humidity 35, location 35. So, the lowest location number in this example is 35.

What is the lowest location number that corresponds to any of the initial seed numbers?

### GitHub Copilot

This problem involves mapping values from one category to another, following a sequence of mappings. Here's a step-by-step plan:

1. Read the input file line by line. Each line represents a mapping or a list of seeds.
2. Store the seeds in a list.
3. Store the mappings in a list of dictionaries. Each dictionary represents a mapping from one category to another. The keys are the source range starts and the values are tuples of the destination range start and the range length.

4. For each seed, follow the sequence of mappings to find the corresponding location. Start with the seed value and for each mapping, find the corresponding value in the next category. If the seed value is in the source range of a mapping, calculate the corresponding value in the destination range. If the seed value is not in the source range of any mapping, the corresponding value is the same as the seed value.
5. Keep track of the minimum location found.

Now, let's implement this in code:

```python
def map_value(value, mapping):
    for source_start, (dest_start, length) in mapping.items():
        if source_start <= value < source_start + length:
            return dest_start + (value - source_start)
    return value

def solve(seeds, mappings):
    min_location = float('inf')
    for seed in seeds:
        value = seed
        for mapping in mappings:
            value = map_value(value, mapping)
        min_location = min(min_location, value)
    return min_location

seeds = [79, 14, 55, 13]

mappings = [
    {98: (50, 2), 50: (52, 48)},
    {15: (0, 37), 52: (37, 2), 0: (39, 15)},
    {53: (49, 8), 11: (0, 42), 0: (42, 7), 7: (57, 4)},
    {18: (88, 7), 25: (18, 70)},
    {77: (45, 23), 45: (81, 19), 64: (68, 13)},
    {69: (0, 1), 0: (1, 69)},
    {56: (60, 37), 93: (56, 4)}
]

print(solve(seeds, mappings))  # Output: 35
```
### GitHub Copilot

In this Python code, the map_value function takes a value and a mapping, and returns the corresponding value in the destination range. The solve function takes the list of seeds and the list of mappings, and returns the minimum location that corresponds to any of the seeds. The seeds and mappings are hard-coded for the example, but in a real solution, they would be read from the input file.

### perty
Neat, please do this in Java.

### GitHub Copilot
Sure, here's how you can implement this in Java:
    
```java
import java.util.*;

public class App5_1 {
    public static void main(String[] args) {
        List<Integer> seeds = Arrays.asList(79, 14, 55, 13);

        List<Map<Integer, int[]>> mappings = new ArrayList<>();
        mappings.add(Map.of(98, new int[]{50, 2}, 50, new int[]{52, 48}));
        mappings.add(Map.of(15, new int[]{0, 37}, 52, new int[]{37, 2}, 0, new int[]{39, 15}));
        mappings.add(Map.of(53, new int[]{49, 8}, 11, new int[]{0, 42}, 0, new int[]{42, 7}, 7, new int[]{57, 4}));
        mappings.add(Map.of(18, new int[]{88, 7}, 25, new int[]{18, 70}));
        mappings.add(Map.of(77, new int[]{45, 23}, 45, new int[]{81, 19}, 64, new int[]{68, 13}));
        mappings.add(Map.of(69, new int[]{0, 1}, 0, new int[]{1, 69}));
        mappings.add(Map.of(56, new int[]{60, 37}, 93, new int[]{56, 4}));

        System.out.println(solve(seeds, mappings));  // Output: 35
    }

    private static int mapValue(int value, Map<Integer, int[]> mapping) {
        for (Map.Entry<Integer, int[]> entry : mapping.entrySet()) {
            int sourceStart = entry.getKey();
            int destStart = entry.getValue()[0];
            int length = entry.getValue()[1];
            if (sourceStart <= value && value < sourceStart + length) {
                return destStart + (value - sourceStart);
            }
        }
        return value;
    }

    private static int solve(List<Integer> seeds, List<Map<Integer, int[]>> mappings) {
        int minLocation = Integer.MAX_VALUE;
        for (int seed : seeds) {
            int value = seed;
            for (Map<Integer, int[]> mapping : mappings) {
                value = mapValue(value, mapping);
            }
            minLocation = Math.min(minLocation, value);
        }
        return minLocation;
    }
}
```
In this Java code, the mapValue method takes a value and a mapping, and returns the corresponding value in the destination range. The solve method takes the list of seeds and the list of mappings, and returns the minimum location that corresponds to any of the seeds. The seeds and mappings are hard-coded for the example, but in a real solution, they would be read from the input file.
### perty
Great, but as you said, we need to read the mappings from a file.
### GitHub Copilot
Sure, here's how you can modify the Java code to read the mappings from a file. We'll use a BufferedReader to read the file line by line. Each line will be split into an array of strings, which will then be converted to integers and added to the mappings list.
```java
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class App5_1 {
    public static void main(String[] args) throws IOException {
        List<Integer> seeds = Arrays.asList(79, 14, 55, 13);

        List<Map<Integer, int[]>> mappings = new ArrayList<>();
        Path path = Paths.get("mappings.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int sourceStart = Integer.parseInt(parts[0]);
                int destStart = Integer.parseInt(parts[1]);
                int length = Integer.parseInt(parts[2]);
                mappings.add(Map.of(sourceStart, new int[]{destStart, length}));
            }
        }

        System.out.println(solve(seeds, mappings));  // Output: 35
    }

    private static int mapValue(int value, Map<Integer, int[]> mapping) {
        for (Map.Entry<Integer, int[]> entry : mapping.entrySet()) {
            int sourceStart = entry.getKey();
            int destStart = entry.getValue()[0];
            int length = entry.getValue()[1];
            if (sourceStart <= value && value < sourceStart + length) {
                return destStart + (value - sourceStart);
            }
        }
        return value;
    }

    private static int solve(List<Integer> seeds, List<Map<Integer, int[]>> mappings) {
        int minLocation = Integer.MAX_VALUE;
        for (int seed : seeds) {
            int value = seed;
            for (Map<Integer, int[]> mapping : mappings) {
                value = mapValue(value, mapping);
            }
            minLocation = Math.min(minLocation, value);
        }
        return minLocation;
    }
}
```
This code assumes that the mappings are stored in a file named "mappings.txt" in the same directory as the Java file, and that each line of the file contains three integers separated by spaces, representing the source range start, the destination range start, and the range length. You'll need to replace "mappings.txt" with the actual path to your file.
### perty
We need to parse the file better. It starts with a list of seeds, then there are mappings. Each mapping starts with a name and the following rows are the actual mappings. An empty line ends the mappings.
### GitHub Copilot
Sure, we can modify the Java code to handle this more complex file format. We'll read the file line by line, and based on the content of the line, we'll decide whether to start a new mapping, add to the current mapping, or add to the list of seeds.
```java
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class App5_1 {
    public static void main(String[] args) throws IOException {
        List<Integer> seeds = new ArrayList<>();
        List<Map<Integer, int[]>> mappings = new ArrayList<>();

        Path path = Paths.get("input5.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            Map<Integer, int[]> currentMapping = null;
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
                        seeds.add(Integer.parseInt(part));
                    }
                } else if (currentMapping != null) {
                    String[] parts = line.split(" ");
                    int sourceStart = Integer.parseInt(parts[0]);
                    int destStart = Integer.parseInt(parts[1]);
                    int length = Integer.parseInt(parts[2]);
                    currentMapping.put(sourceStart, new int[]{destStart, length});
                }
            }
            if (currentMapping != null) {
                mappings.add(currentMapping);
            }
        }

        System.out.println(solve(seeds, mappings));  // Output: 35
    }

    private static int mapValue(int value, Map<Integer, int[]> mapping) {
        for (Map.Entry<Integer, int[]> entry : mapping.entrySet()) {
            int sourceStart = entry.getKey();
            int destStart = entry.getValue()[0];
            int length = entry.getValue()[1];
            if (sourceStart <= value && value < sourceStart + length) {
                return destStart + (value - sourceStart);
            }
        }
        return value;
    }

    private static int solve(List<Integer> seeds, List<Map<Integer, int[]>> mappings) {
        int minLocation = Integer.MAX_VALUE;
        for (int seed : seeds) {
            int value = seed;
            for (Map<Integer, int[]> mapping : mappings) {
                value = mapValue(value, mapping);
            }
            minLocation = Math.min(minLocation, value);
        }
        return minLocation;
    }
}
``` 
This code assumes that the input is stored in a file named "input5.txt" in the same directory as the Java file. You'll need to replace "input5.txt" with the actual path to your file.
### perty
The result is 31, it should be 35.

### GitHub Copilot
The discrepancy in the result might be due to the order in which the mappings are applied. In the provided code, the mappings are applied in the order they are read from the file. However, the problem statement might require a specific order.

If the order of the mappings is important, you should adjust the code to apply the mappings in the correct order.

If the order of the mappings is not specified in the problem statement, you might need to try all possible orders and see which one gives the correct result. This can be done using a recursive function that generates all permutations of the mappings and applies each one to the seeds.

If you could provide more details about the problem, I could give a more specific solution.

### perty
I think the source and destination is the other way around. From the puzzle "the destination range start, the source range start, and the range length."

### GitHub Copilot
I see, the order of the source and destination in the mappings is reversed. Let's correct that. Here's the corrected code:

```java
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class App5_1 {
    public static void main(String[] args) throws IOException {
        List<Integer> seeds = new ArrayList<>();
        List<Map<Integer, int[]>> mappings = new ArrayList<>();

        Path path = Paths.get("data/input5.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            Map<Integer, int[]> currentMapping = null;
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
                        seeds.add(Integer.parseInt(part));
                    }
                } else if (currentMapping != null) {
                    String[] parts = line.split(" ");
                    int destStart = Integer.parseInt(parts[0]);
                    int sourceStart = Integer.parseInt(parts[1]);
                    int length = Integer.parseInt(parts[2]);
                    currentMapping.put(sourceStart, new int[]{destStart, length});
                }
            }
            if (currentMapping != null) {
                mappings.add(currentMapping);
            }
        }

        System.out.println(solve(seeds, mappings));  // Output: 35
    }

    private static int mapValue(int value, Map<Integer, int[]> mapping) {
        for (Map.Entry<Integer, int[]> entry : mapping.entrySet()) {
            int sourceStart = entry.getKey();
            int destStart = entry.getValue()[0];
            int length = entry.getValue()[1];
            if (sourceStart <= value && value < sourceStart + length) {
                return destStart + (value - sourceStart);
            }
        }
        return value;
    }

    private static int solve(List<Integer> seeds, List<Map<Integer, int[]>> mappings) {
        int minLocation = Integer.MAX_VALUE;
        for (int seed : seeds) {
            int value = seed;
            for (Map<Integer, int[]> mapping : mappings) {
                value = mapValue(value, mapping);
            }
            minLocation = Math.min(minLocation, value);
        }
        return minLocation;
    }
}
```
In this code, I've swapped the order of destStart and sourceStart when parsing the mappings from the file. Now, destStart is parsed from parts[0] and sourceStart is parsed from parts[1]. This should correct the issue and give the expected output.
### perty
I tried on the actual input. But got Exception in thread "main" java.lang.NumberFormatException: For input string: "2906248740"
### GitHub Copilot
The error message indicates that the number "2906248740" is too large to be stored in an int variable. In Java, the maximum value for an int is 2,147,483,647.

To fix this, you can change the type of the variables and arrays that store these large numbers to long, which can store much larger numbers. Here's how you can modify the code:

```java
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
```
In this code, I've replaced int with long for the variables and arrays that store the seeds and mappings. I've also replaced Integer.parseInt with Long.parseLong to parse the numbers from the file. This should fix the NumberFormatException and allow the code to handle larger numbers.
### perty
Great we did it!
