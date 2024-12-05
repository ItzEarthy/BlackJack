import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.*;

public class Deck {
    private ArrayList<Card> cards;
    Sound sound = new Sound();

    // Constructor to initialize the deck with 52 cards
    public Deck() {
        int totalDecks = 1;
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

    public void createDeck() {
        int totalDecks = 1;
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
        shuffle();
        shuffle();
    }

    // Method to shuffle the deck
    public void shuffle() {
        Collections.shuffle(cards);
        sound.playSound("shuffle");
        try {
            System.out.println("Deck Shuffling...");
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    // Method to deal a card from the top of the deck
    public Card dealCard() {

            sound.playSound("place");
            if(cards.isEmpty()){
                System.out.println("The deck is empty!");
                System.out.println("Creating new deck...");
                createDeck();
            }
            try {
                System.out.println("Dealing Card...");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return cards.remove(cards.size() - 1);

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
