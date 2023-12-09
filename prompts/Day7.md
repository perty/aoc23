# Day 7
## Part 1
### GitHub Copilot
Sure, I'm ready to assist you with Day 7, Part 1. Please provide the details of the puzzle.
### perty
In Camel Cards, you get a list of hands, and your goal is to order them based on the strength of each hand. A hand consists of five cards labeled one of A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, or 2. The relative strength of each card follows this order, where A is the highest and 2 is the lowest.

Every hand is exactly one type. From strongest to weakest, they are:

* Five of a kind, where all five cards have the same label: AAAAA
* Four of a kind, where four cards have the same label and one card has a different label: AA8AA
* Full house, where three cards have the same label, and the remaining two cards share a different label: 23332
* Three of a kind, where three cards have the same label, and the remaining two cards are each different from any other card in the hand: TTT98
* Two pair, where two cards share one label, two other cards share a second label, and the remaining card has a third label: 23432
* One pair, where two cards share one label, and the other three cards have a different label from the pair and each other: A23A4
* High card, where all cards' labels are distinct: 23456

Hands are primarily ordered based on type; for example, every full house is stronger than any three of a kind.

If two hands have the same type, a second ordering rule takes effect. Start by comparing the first card in each hand. If these cards are different, the hand with the stronger first card is considered stronger. If the first card in each hand have the same label, however, then move on to considering the second card in each hand. If they differ, the hand with the higher second card wins; otherwise, continue with the third card in each hand, then the fourth, then the fifth.

So, 33332 and 2AAAA are both four of a kind hands, but 33332 is stronger because its first card is stronger. Similarly, 77888 and 77788 are both a full house, but 77888 is stronger because its third card is stronger (and both hands have the same first and second card).

To play Camel Cards, you are given a list of hands and their corresponding bid (your puzzle input). For example:

```
32T3K 765 
T55J5 684 
KK677 28 
KTJJT 220 
QQQJA 483 
```

This example shows five hands; each hand is followed by its bid amount. Each hand wins an amount equal to its bid multiplied by its rank, where the weakest hand gets rank 1, the second-weakest hand gets rank 2, and so on up to the strongest hand. Because there are five hands in this example, the strongest hand will have rank 5 and its bid will be multiplied by 5.

So, the first step is to put the hands in order of strength:

32T3K is the only one pair and the other hands are all a stronger type, so it gets rank 1. KK677 and KTJJT are both two pair. Their first cards both have the same label, but the second card of KK677 is stronger (K vs T), so KTJJT gets rank 2 and KK677 gets rank 3. T55J5 and QQQJA are both three of a kind. QQQJA has a stronger first card, so it gets rank 5 and T55J5 gets rank 4. Now, you can determine the total winnings of this set of hands by adding up the result of multiplying each hand's bid with its rank (765 * 1 + 220 * 2 + 28 * 3 + 684 * 4 + 483 * 5). So the total winnings in this example are 6440.

Find the rank of every hand in your set. What are the total winnings?
### GitHub Copilot
To solve this problem, we need to follow these steps:

Parse the input to get the hands and their corresponding bids.
Define a function to determine the strength of a hand. This function should return a tuple where the first element is the type of the hand and the second element is a list of the cards in the hand sorted by their strength. The type of the hand should be represented as an integer where a higher number represents a stronger hand.
Sort the hands by their strength using the function defined in step 2.
Calculate the total winnings by multiplying each hand's bid with its rank and summing up the results.

Here's the Java code that implements these steps:
    
```java
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class App7_1 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("data/input7.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<Hand> hands = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                hands.add(new Hand(parts[0], Integer.parseInt(parts[1])));
            }
            Collections.sort(hands);
            int totalWinnings = 0;
            for (int i = 0; i < hands.size(); i++) {
                totalWinnings += hands.get(i).bid * (i + 1);
            }
            System.out.println(totalWinnings);
        }
    }
}

class Hand implements Comparable<Hand> {
    String cards;
    int bid;

    Hand(String cards, int bid) {
        this.cards = cards;
        this.bid = bid;
    }

    @Override
    public int compareTo(Hand other) {
        // Implement the comparison logic here
        return 0;
    }
}
```
In this code, I've created a Hand class to represent a hand of cards. The Hand class implements the Comparable interface, which allows us to sort a list of hands. The compareTo method in the Hand class should be implemented to determine the order of the hands based on their strength.
### perty
So how could we do the compareTo method?
### GitHub Copilot
The compareTo method should compare two Hand objects based on their strength. Here's how you could implement it:

First, create a helper method handStrength that takes a Hand object and returns a tuple. The first element of the tuple is an integer representing the type of the hand (higher for stronger hands), and the second element is a list of the cards in the hand sorted by their strength.

In the compareTo method, call handStrength for both this and other. Compare the first elements of the tuples. If they are not equal, return the result of the comparison.

If the first elements of the tuples are equal, compare the second elements (the lists of cards). Start from the first card and compare the cards at the same positions in the lists. If you find cards that are not equal, return the result of the comparison.

If all cards are equal, return 0.

Here's the pseudocode for the compareTo method and the handStrength helper method:

```java
class Hand implements Comparable<Hand> {
    String cards;
    int bid;

    Hand(String cards, int bid) {
        this.cards = cards;
        this.bid = bid;
    }

    private Pair<Integer, List<Character>> handStrength() {
        // Calculate the strength of the hand and return it as a pair
    }

    @Override
    public int compareTo(Hand other) {
        Pair<Integer, List<Character>> thisStrength = handStrength();
        Pair<Integer, List<Character>> otherStrength = other.handStrength();

        int typeComparison = thisStrength.first.compareTo(otherStrength.first);
        if (typeComparison != 0) {
            return typeComparison;
        }

        for (int i = 0; i < thisStrength.second.size(); i++) {
            int cardComparison = thisStrength.second.get(i).compareTo(otherStrength.second.get(i));
            if (cardComparison != 0) {
                return cardComparison;
            }
        }

        return 0;
    }
}
```
In this pseudocode, Pair is a hypothetical class that represents a pair of two elements. You would need to replace it with an actual class available in your programming language. The handStrength method is also hypothetical and needs to be implemented.
### perty
The handStrength could return an int only. When the hands have same strength, we just look at the individual cards.
### GitHub Copilot
That's correct. If two hands have the same strength, we can compare the individual cards. Here's how you could implement the handStrength and compareTo methods:

```java
class Hand implements Comparable<Hand> {
    String cards;
    int bid;

    Hand(String cards, int bid) {
        this.cards = cards;
        this.bid = bid;
    }

    private int handStrength() {
        // Calculate the strength of the hand and return it as an integer
    }

    @Override
    public int compareTo(Hand other) {
        int thisStrength = handStrength();
        int otherStrength = other.handStrength();

        if (thisStrength != otherStrength) {
            return Integer.compare(thisStrength, otherStrength);
        }

        for (int i = 0; i < this.cards.length(); i++) {
            int cardComparison = Character.compare(this.cards.charAt(i), other.cards.charAt(i));
            if (cardComparison != 0) {
                return cardComparison;
            }
        }

        return 0;
    }
}
```
In this code, the handStrength method should calculate the strength of the hand and return it as an integer. The compareTo method first compares the strengths of the hands. If they are equal, it compares the individual cards. If it finds cards that are not equal, it returns the result of the comparison. If all cards are equal, it returns 0.
### perty
For implementing the handStrength method, we need to compare it with possible types, starting with the strongest, Five of a kind.
### GitHub Copilot
Yes, you're correct. The handStrength method should check each possible hand type, starting from the strongest. If the hand matches a type, the method should return the strength corresponding to that type. Here's how you could implement it:

```java
private int handStrength() {
    String sortedCards = sortCardsByStrength(cards);

    if (isFiveOfAKind(sortedCards)) {
        return 7;
    } else if (isFourOfAKind(sortedCards)) {
        return 6;
    } else if (isFullHouse(sortedCards)) {
        return 5;
    } else if (isThreeOfAKind(sortedCards)) {
        return 4;
    } else if (isTwoPair(sortedCards)) {
        return 3;
    } else if (isOnePair(sortedCards)) {
        return 2;
    } else {
        return 1; // High card
    }
}
```
In this code, sortCardsByStrength, isFiveOfAKind, isFourOfAKind, isFullHouse, isThreeOfAKind, isTwoPair, and isOnePair are helper methods that you need to implement. Each of these methods should take a string of sorted cards and return a boolean indicating whether the cards match the corresponding hand type.

The sortCardsByStrength method should sort the cards in descending order of their strength. You can implement this method by converting the cards to a list, sorting the list using a custom comparator that compares the cards based on their strength, and then converting the list back to a string.
### perty
How about we represent the cards by their value instead? A hand would then be an array of int and a bid.
### GitHub Copilot
That's a great idea! Representing the cards by their value will make the comparison easier. Here's how you could modify the Hand class:

```java
class Hand implements Comparable<Hand> {
    int[] cards;
    int bid;

    Hand(String cardString, int bid) {
        this.cards = new int[5];
        for (int i = 0; i < 5; i++) {
            char card = cardString.charAt(i);
            if (card >= '2' && card <= '9') {
                this.cards[i] = card - '0';
            } else {
                switch (card) {
                    case 'T':
                        this.cards[i] = 10;
                        break;
                    case 'J':
                        this.cards[i] = 11;
                        break;
                    case 'Q':
                        this.cards[i] = 12;
                        break;
                    case 'K':
                        this.cards[i] = 13;
                        break;
                    case 'A':
                        this.cards[i] = 14;
                        break;
                }
            }
        }
        Arrays.sort(this.cards);
        this.bid = bid;
    }

    // rest of the code
}
```
In this code, I've modified the constructor of the Hand class to convert the card string into an array of integers. The cards are then sorted in ascending order. The handStrength and compareTo methods should be updated accordingly to work with the integer array.

### perty
Yes, but we shall not sort the cards, their original order is significant when comparing hands of equal type.

### GitHub Copilot
You're correct. The original order of the cards is significant when comparing hands of the same type. Here's how you can modify the Hand class to store the cards as an array of integers without sorting them:
    
```java
class Hand implements Comparable<Hand> {
    int[] cards;
    int bid;

    Hand(String cardString, int bid) {
        this.cards = new int[5];
        for (int i = 0; i < 5; i++) {
            char card = cardString.charAt(i);
            if (card >= '2' && card <= '9') {
                this.cards[i] = card - '0';
            } else {
                switch (card) {
                    case 'T':
                        this.cards[i] = 10;
                        break;
                    case 'J':
                        this.cards[i] = 11;
                        break;
                    case 'Q':
                        this.cards[i] = 12;
                        break;
                    case 'K':
                        this.cards[i] = 13;
                        break;
                    case 'A':
                        this.cards[i] = 14;
                        break;
                }
            }
        }
        this.bid = bid;
    }

    // rest of the code
}
```
In this code, I've modified the constructor of the Hand class to convert the card string into an array of integers without sorting them. The handStrength and compareTo methods should be updated accordingly to work with the integer array.

### perty
When it comes to determine the hand's strength, I've noticed that in most cases it is about cards of similar value, so if we could put the cards in buckets based on value, we could for example see if there is a bucket with three cards, then it is three of a kind.

### GitHub Copilot
That's a great observation! Grouping the cards into buckets based on their value will indeed make it easier to determine the hand's strength. Here's how you can implement this:
    
```java
private int handStrength() {
    int[] buckets = new int[15]; // 2-14 for card values, 0 and 1 are unused
    for (int card : cards) {
        buckets[card]++;
    }

    boolean hasPair = false;
    boolean hasThreeOfAKind = false;

    for (int count : buckets) {
        if (count == 2) {
            hasPair = true;
        } else if (count == 3) {
            hasThreeOfAKind = true;
        } else if (count == 4) {
            return 6; // Four of a kind
        } else if (count == 5) {
            return 7; // Five of a kind
        }
    }

    if (hasPair && hasThreeOfAKind) {
        return 5; // Full house
    } else if (hasThreeOfAKind) {
        return 4; // Three of a kind
    } else if (hasPair) {
        return 2; // One pair
    }

    // Check for two pairs
    int pairCount = 0;
    for (int count : buckets) {
        if (count == 2) {
            pairCount++;
        }
    }
    if (pairCount == 2) {
        return 3; // Two pairs
    }

    return 1; // High card
}
```
In this code, I've created an array buckets to count the number of each card. Then I've checked the counts to determine the hand's strength. If there's a count of 4 or 5, it's four of a kind or five of a kind, respectively. If there's a pair and three of a kind, it's a full house. If there's three of a kind or a pair, it's three of a kind or one pair, respectively. If there are two pairs, it's two pairs. Otherwise, it's a high card.

### perty
Great but we need to check for two pairs before one pair. In this code, we will return 2 even if there are two pairs.

### GitHub Copilot
You're correct. The check for two pairs should be done before the check for one pair. Here's the corrected handStrength method:
        
```java
private int handStrength() {
    int[] buckets = new int[15]; // 2-14 for card values, 0 and 1 are unused
    for (int card : cards) {
        buckets[card]++;
    }

    boolean hasPair = false;
    boolean hasThreeOfAKind = false;

    for (int count : buckets) {
        if (count == 2) {
            hasPair = true;
        } else if (count == 3) {
            hasThreeOfAKind = true;
        } else if (count == 4) {
            return 6; // Four of a kind
        } else if (count == 5) {
            return 7; // Five of a kind
        }
    }

    if (hasPair && hasThreeOfAKind) {
        return 5; // Full house
    } else if (hasThreeOfAKind) {
        return 4; // Three of a kind
    }

    // Check for two pairs
    int pairCount = 0;
    for (int count : buckets) {
        if (count == 2) {
            pairCount++;
        }
    }
    if (pairCount == 2) {
        return 3; // Two pairs
    } else if (hasPair) {
        return 2; // One pair
    }

    return 1; // High card
}
```
In this code, I've moved the check for two pairs before the check for one pair. Now, if there are two pairs, the method will correctly return 3. If there's only one pair, it will return 2.

### perty
WE could simplfy it by couting pairs directly and use pairCount instead of hasPair.

### GitHub Copilot
You're right. We can simplify the code by counting pairs directly in the first loop and using pairCount instead of hasPair. Here's the updated handStrength method:

```java
private int handStrength() {
    int[] buckets = new int[15]; // 2-14 for card values, 0 and 1 are unused
    int pairCount = 0;
    boolean hasThreeOfAKind = false;

    for (int card : cards) {
        buckets[card]++;
    }

    for (int count : buckets) {
        if (count == 2) {
            pairCount++;
        } else if (count == 3) {
            hasThreeOfAKind = true;
        } else if (count == 4) {
            return 6; // Four of a kind
        } else if (count == 5) {
            return 7; // Five of a kind
        }
    }

    if (pairCount == 1 && hasThreeOfAKind) {
        return 5; // Full house
    } else if (hasThreeOfAKind) {
        return 4; // Three of a kind
    } else if (pairCount == 2) {
        return 3; // Two pairs
    } else if (pairCount == 1) {
        return 2; // One pair
    }

    return 1; // High card
}
```
In this code, I've removed the hasPair variable and added a pairCount variable that is incremented whenever a pair is found. The checks for full house, two pairs, and one pair are updated to use pairCount.

### perty
The result is correct!

### GitHub Copilot
Great! I'm glad to hear that the solution worked. If you have any more questions or need further assistance, feel free to ask.
