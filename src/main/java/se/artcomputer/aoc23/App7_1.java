package se.artcomputer.aoc23;

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

    @Override
    public int compareTo(Hand other) {
        int thisStrength = handStrength();
        int otherStrength = other.handStrength();

        if (thisStrength != otherStrength) {
            return Integer.compare(thisStrength, otherStrength);
        }

        for (int i = 0; i < this.cards.length; i++) {
            int cardComparison = this.cards[i] - other.cards[i];
            if (cardComparison != 0) {
                return cardComparison;
            }
        }

        return 0;
    }
}