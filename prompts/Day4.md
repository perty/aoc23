# Day 4

## Part 1

**perty**

New day, new puzzle.

**GitHub Copilot**

Absolutely! Please share the details of the new puzzle and I'll do my best to assist you.

**perty**

The Elf leads you over to the pile of colorful cards. There, you discover dozens of scratchcards, all with their opaque covering already scratched off. Picking one up, it looks like each card has two lists of numbers separated by a vertical bar (|): a list of winning numbers and then a list of numbers you have. You organize the information into a table (your puzzle input).

As far as the Elf has been able to figure out, you have to figure out which of the numbers you have appear in the list of winning numbers. The first match makes the card worth one point and each match after the first doubles the point value of that card.

For example:

```
Card 1: 41 48 83 86 17 | 83 86 6 31 17 9 48 53 
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19 
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14 1 
Card 4: 41 92 73 84 69 | 59 84 76 51 58 5 54 83 
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36 
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
```

In the above example, card 1 has five winning numbers (41, 48, 83, 86, and 17) and eight numbers you have (83, 86, 6, 31, 17, 9, 48, and 53). Of the numbers you have, four of them (48, 83, 17, and 86) are winning numbers! That means card 1 is worth 8 points (1 for the first match, then doubled three times for each of the three matches after the first).

Card 2 has two winning numbers (32 and 61), so it is worth 2 points. Card 3 has two winning numbers (1 and 21), so it is worth 2 points. Card 4 has one winning number (84), so it is worth 1 point. Card 5 has no winning numbers, so it is worth no points. Card 6 has no winning numbers, so it is worth no points. So, in this example, the Elf's pile of scratchcards is worth 13 points.

Take a seat in the large pile of colorful cards. How many points are they worth in total?

**GitHub Copilot**

First, let's outline the steps we need to take to solve this problem:

1. Read the input file line by line. Each line represents a card.
2. Split each line at the vertical bar (|) to separate the winning numbers from the numbers you have.
3. Convert each list of numbers into a Set of integers for easy comparison.
4. For each card, count the number of numbers you have that are also winning numbers.
5. Calculate the point value of each card. The first match is worth 1 point, and each subsequent match doubles the point value.
6. Sum the point values of all cards to get the total points.

Now, let's implement this in code:

```
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class App4_1 {
    public static void main(String[] args) {
        try {
            int totalPoints = 0;
            for (String line : Files.readAllLines(Paths.get("data/input4.txt"))) {
                String[] parts = line.split(" \\| ");
                Set<Integer> winningNumbers = new HashSet<>();
                for (String number : parts[0].split(" ")) {
                    winningNumbers.add(Integer.parseInt(number));
                }
                Set<Integer> yourNumbers = new HashSet<>();
                for (String number : parts[1].split(" ")) {
                    yourNumbers.add(Integer.parseInt(number));
                }
                int matches = 0;
                for (int number : yourNumbers) {
                    if (winningNumbers.contains(number)) {
                        matches++;
                    }
                }
                int points = matches > 0 ? 1 << (matches - 1) : 0;
                totalPoints += points;
            }
            System.out.println(totalPoints);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
This program reads the input file, processes each card, and calculates the total points. The points for each card are calculated using bitwise shift (<<) to double the point value for each match after the first.

**perty**

We need to read passed the first words on each line. Each line starts like "Card 3:".

**GitHub Copilot**

I see, you want to ignore the "Card X:" part of each line. You can do this by splitting each line at the colon (:) and taking the second part. Here's how you can modify the code to do that:

```
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class App4_1 {
    public static void main(String[] args) {
        try {
            int totalPoints = 0;
            for (String line : Files.readAllLines(Paths.get("data/input4.txt"))) {
                String[] cardParts = line.split(": ");
                String[] parts = cardParts[1].split(" \\| ");
                Set<Integer> winningNumbers = new HashSet<>();
                for (String number : parts[0].split(" ")) {
                    winningNumbers.add(Integer.parseInt(number));
                }
                Set<Integer> yourNumbers = new HashSet<>();
                for (String number : parts[1].split(" ")) {
                    yourNumbers.add(Integer.parseInt(number));
                }
                int matches = 0;
                for (int number : yourNumbers) {
                    if (winningNumbers.contains(number)) {
                        matches++;
                    }
                }
                int points = matches > 0 ? 1 << (matches - 1) : 0;
                totalPoints += points;
            }
            System.out.println(totalPoints);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

In this updated code, we first split each line at the colon (:) and take the second part, which contains the winning numbers and the numbers you have. The rest of the code remains the same.

**perty**

Stacktrace: Exception in thread "main" java.lang.NumberFormatException: For input string: "" at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67) at java.base/java.lang.Integer.parseInt(Integer.java:671) at java.base/java.lang.Integer.parseInt(Integer.java:777) at se.artcomputer.aoc23.App4_1.main(App4_1.java:23) 

**GitHub Copilot**

The error message indicates that the program is trying to parse an empty string as an integer. This could happen if there are extra spaces in the input data.

To fix this, you can add a check to skip empty strings before parsing the numbers. Here's how you can modify the code to do that:

```
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class App4_1 {
    public static void main(String[] args) {
        try {
            int totalPoints = 0;
            for (String line : Files.readAllLines(Paths.get("data/input4.txt"))) {
                String[] cardParts = line.split(": ");
                String[] parts = cardParts[1].split(" \\| ");
                Set<Integer> winningNumbers = new HashSet<>();
                for (String number : parts[0].split(" ")) {
                    if (!number.isEmpty()) {
                        winningNumbers.add(Integer.parseInt(number));
                    }
                }
                Set<Integer> yourNumbers = new HashSet<>();
                for (String number : parts[1].split(" ")) {
                    if (!number.isEmpty()) {
                        yourNumbers.add(Integer.parseInt(number));
                    }
                }
                int matches = 0;
                for (int number : yourNumbers) {
                    if (winningNumbers.contains(number)) {
                        matches++;
                    }
                }
                int points = matches > 0 ? 1 << (matches - 1) : 0;
                totalPoints += points;
            }
            System.out.println(totalPoints);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
In this updated code, we added a check to skip empty strings before parsing the numbers. This should prevent the NumberFormatException from occurring.

**perty**

That worked fine. Now we will take on the next puzzle.

**GitHub Copilot**

Great! Please share the details of the next puzzle and I'll do my best to assist you.

## Part 2

**perty**

There's no such thing as "points". Instead, scratchcards only cause you to win more scratchcards equal to the number of winning numbers you have.

Specifically, you win copies of the scratchcards below the winning card equal to the number of matches. So, if card 10 were to have 5 matching numbers, you would win one copy each of cards 11, 12, 13, 14, and 15.

Copies of scratchcards are scored like normal scratchcards and have the same card number as the card they copied. So, if you win a copy of card 10 and it has 5 matching numbers, it would then win a copy of the same cards that the original card 10 won: cards 11, 12, 13, 14, and 15. This process repeats until none of the copies cause you to win any more cards. (Cards will never make you copy a card past the end of the table.)

This time, the above example goes differently:
```
Card 1: 41 48 83 86 17 | 83 86 6 31 17 9 48 53 
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19 
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14 1 
Card 4: 41 92 73 84 69 | 59 84 76 51 58 5 54 83 
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36 
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
```

Card 1 has four matching numbers, so you win one copy each of the next four cards: cards 2, 3, 4, and 5. Your original card 2 has two matching numbers, so you win one copy each of cards 3 and 4. Your copy of card 2 also wins one copy each of cards 3 and 4. Your four instances of card 3 (one original and three copies) have two matching numbers, so you win four copies each of cards 4 and 5. Your eight instances of card 4 (one original and seven copies) have one matching number, so you win eight copies of card 5. Your fourteen instances of card 5 (one original and thirteen copies) have no matching numbers and win no more cards. Your one instance of card 6 (one original) has no matching numbers and wins no more cards. Once all of the originals and copies have been processed, you end up with 1 instance of card 1, 2 instances of card 2, 4 instances of card 3, 8 instances of card 4, 14 instances of card 5, and 1 instance of card 6. In total, this example pile of scratchcards causes you to ultimately have 30 scratchcards!

Process all of the original and copied scratchcards until no more scratchcards are won. Including the original set of scratchcards, how many total scratchcards do you end up with?

**GitHub Copilot**  

This problem is a bit more complex. We'll need to keep track of the number of copies of each card, and process each card in order. Here's a step-by-step plan:

1. Read the input file line by line. Each line represents a card.
2. Split each line at the vertical bar (|) to separate the winning numbers from the numbers you have.
3. Convert each list of numbers into a Set of integers for easy comparison.
4. Store the cards in a list. Each card is represented as a pair of sets: the winning numbers and the numbers you have.
5. Initialize an array to keep track of the number of copies of each card. The size of the array is the number of cards. Initially, there is one copy of each card.
6. Process each card in order. For each card, count the number of numbers you have that are also winning numbers. This is the number of new copies you win. Add these copies to the cards below the current card.
7. Continue processing the cards until no more new copies are won.
8. Sum the number of copies of all cards to get the total number of scratchcards.

Now, let's implement this in code:

```
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App4_2 {
    public static void main(String[] args) {
        try {
            List<Set<Integer>> winningNumbersList = new ArrayList<>();
            List<Set<Integer>> yourNumbersList = new ArrayList<>();
            for (String line : Files.readAllLines(Paths.get("data/input4.txt"))) {
                String[] cardParts = line.split(": ");
                String[] parts = cardParts[1].split(" \\| ");
                Set<Integer> winningNumbers = new HashSet<>();
                for (String number : parts[0].split(" ")) {
                    if (!number.isEmpty()) {
                        winningNumbers.add(Integer.parseInt(number));
                    }
                }
                winningNumbersList.add(winningNumbers);
                Set<Integer> yourNumbers = new HashSet<>();
                for (String number : parts[1].split(" ")) {
                    if (!number.isEmpty()) {
                        yourNumbers.add(Integer.parseInt(number));
                    }
                }
                yourNumbersList.add(yourNumbers);
            }
            int[] copies = new int[winningNumbersList.size()];
            for (int i = 0; i < copies.length; i++) {
                copies[i] = 1;
            }
            for (int i = 0; i < copies.length; i++) {
                int matches = 0;
                for (int number : yourNumbersList.get(i)) {
                    if (winningNumbersList.get(i).contains(number)) {
                        matches++;
                    }
                }
                for (int j = i + 1; j < i + 1 + matches && j < copies.length; j++) {
                    copies[j] += copies[i];
                }
            }
            int totalCards = 0;
            for (int copy : copies) {
                totalCards += copy;
            }
            System.out.println(totalCards);
        } catch (IOException e
```
Sorry, the response hit the length limit. Please rephrase your prompt.
