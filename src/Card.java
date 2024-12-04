public class Card {
    private String suit; // Suit of the card (e.g., Hearts, Diamonds)
    private String rank; // Rank of the card (e.g., 2, 10, J, Q, K, A)
    private int value;   // Value of the card in Blackjack (A = 1 or 11, 2-10, J/Q/K = 10)

    // Constructor to initialize a card with a given suit, rank, and value
    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    // Getter method for card suit
    public String getSuit() {
        return suit;
    }

    // Getter method for card rank
    public String getRank() {
        return rank;
    }

    // Getter method for card value
    public int getValue() {
        return value;
    }

    // Method to display the card as a string (e.g., "Ace of Hearts")
    @Override
    public String toString() {
        if (suit == "Hearts"){
            return Colors.RED + Colors.WHITE_BACKROUND+"♥ " + rank + Colors.ANSI_RESET;
        }
        else if (suit == "Clubs"){
            return Colors.BLACK + Colors.WHITE_BACKROUND+"♣ " + rank + Colors.ANSI_RESET;
        }
        else if (suit == "Diamonds"){
            return Colors.RED + Colors.WHITE_BACKROUND+"♦ " + rank + Colors.ANSI_RESET;
        }
        else if (suit == "Spades"){
            return Colors.BLACK + Colors.WHITE_BACKROUND+"♠ " + rank + Colors.ANSI_RESET;
        }
        else{
            return null;
        }

    }

}
