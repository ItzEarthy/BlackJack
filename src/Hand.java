import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards; // List to hold cards in the hand
    private int totalValue;        // Total value of the hand, calculated with Blackjack rules

    // Constructor to initialize an empty hand
    public Hand() {
        cards = new ArrayList<>();
        totalValue = 0;
    }

    // Method to add a card to the hand
    public void addCard(Card card) {
        cards.add(card);
        calculateTotalValue();
    }

    // Method to calculate the total value of the hand, considering Aces as 1 or 11
    private void calculateTotalValue() {
        totalValue = 0;
        int aceCount = 0;

        // Loop through each card in the hand to add up values
        for (Card card : cards) {
            int cardValue = card.getValue();
            totalValue += cardValue;

            if (card.getRank().equals("A")) {
                aceCount++; // Count Aces, as they can be worth 1 or 11
            }
        }

        // Adjust value if Aces are present and the total value is over 21
        while (totalValue > 21 && aceCount > 0) {
            totalValue -= 10; // Convert an Ace from 11 to 1
            aceCount--;
        }
    }

    // Method to get the total value of the hand
    public int getTotalValue() {
        return totalValue;
    }

    // Method to check if the hand is a bust (over 21)
    public boolean isBust() {
        return totalValue > 21;
    }

    // Method to check if the hand has Blackjack (an Ace and a 10-value card)
    public boolean hasBlackjack() {
        return cards.size() == 2 && totalValue == 21;
    }

    // Method to clear the hand, for starting a new round
    public void clearHand() {
        cards.clear();
        totalValue = 0;
    }

    // Method to display all cards in the hand (for text-based interface)
    public void displayHand() {
        for (Card card : cards) {
            System.out.println(card);
        }
        System.out.println("Total Value: " + totalValue);
    }
}
