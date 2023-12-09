package se.artcomputer.aoc23;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App7_2 {
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

    static class Hand implements Comparable<Hand> {
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
                            this.cards[i] = 1;
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

        int handStrength() {
            int[] buckets = new int[15];
            int jokerCount = 0;

            for (int card : cards) {
                buckets[card]++;
                if (card == 1) {
                    jokerCount++;
                }
            }
            if (isFiveOfAKind(buckets, jokerCount)) {
                return 7;
            }
            if (isFourOfAKind(buckets, jokerCount)) {
                return 6;
            }
            if (isFullHouse(buckets, jokerCount)) {
                return 5;
            }
            if (isThreeOfAKind(buckets, jokerCount)) {
                return 4;
            }
            if (isTwoPairs(buckets, jokerCount)) {
                return 3;
            }
            if(isPair(buckets, jokerCount)) {
                return 2;
            }
            return 1;
        }

        boolean isFiveOfAKind(int[] buckets, int jokerCount) {
            for (int i = 2; i < buckets.length; i++) {
                if (buckets[i] + jokerCount == 5) {
                    return true;
                }
            }
            return false;
        }

        boolean isFourOfAKind(int[] buckets, int jokerCount) {
            for (int i = 2; i < buckets.length; i++) {
                if (buckets[i] + jokerCount == 4) {
                    return true;
                }
            }
            return false;
        }

        boolean isFullHouse(int[] buckets, int jokerCount) {
            boolean hasThreeOfAKind = false;
            boolean hasPair = false;
            int usedJokers = 0;
            for (int i = 2; i < buckets.length; i++) {
                if(buckets[i] == 0) {
                    continue;
                }
                if (buckets[i] + jokerCount - usedJokers == 3) {
                    hasThreeOfAKind = true;
                    usedJokers += 3 - buckets[i];
                }
                else if (buckets[i] + jokerCount - usedJokers == 2) {
                    hasPair = true;
                    usedJokers += 2 - buckets[i];
                }
            }
            return hasThreeOfAKind && hasPair;
        }

        boolean isThreeOfAKind(int[] buckets, int jokerCount) {
            for (int i = 2; i < buckets.length; i++) {
                if (buckets[i] + jokerCount == 3) {
                    return true;
                }
            }
            return false;
        }

        boolean isTwoPairs(int[] buckets, int jokerCount) {
            int pairCount = 0;
            for (int i = 2; i < buckets.length; i++) {
                if (buckets[i] + jokerCount == 2) {
                    pairCount++;
                }
            }
            return pairCount == 2;
        }

        boolean isPair(int[] buckets, int jokerCount) {
            for (int i = 2; i < buckets.length; i++) {
                if (buckets[i] + jokerCount == 2) {
                    return true;
                }
            }
            return false;
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
}
