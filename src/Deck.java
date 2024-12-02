import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.*;

public class Deck {
    private ArrayList<Card> cards;
    Sound sound = new Sound();

    // Constructor to initialize the deck with 52 cards
    public Deck() {
        int totalDecks = 3;
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11}; // Values for Blackjack

        for (int d = 0; d < totalDecks; d++) {
            for (String suit : suits) {
                for (int i = 0; i < ranks.length; i++) {
                    String rank = ranks[i];
                    int value = values[i];
                    cards.add(new Card(suit, rank, value));
                }
            }
        }
    }

    // Method to shuffle the deck
    public void shuffle() {
        Collections.shuffle(cards);
        sound.playSound("shuffle");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    // Method to deal a card from the top of the deck
    public Card dealCard() {
        if (!cards.isEmpty()) {
            sound.playSound("place"); // Play sound before returning the card
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return cards.remove(cards.size() - 1);

        } else {
            System.out.println("The deck is empty!");
            return null; // Return null if no cards are left
        }
    }

    // Method to get the number of cards remaining in the deck
    public int cardsRemaining() {
        return cards.size();
    }

    @Override
    public String toString() {
        return "Cards in Deck=" + cards;
    }
}
