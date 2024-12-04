import java.util.Scanner;

public class Player {
    private Hand hand;           // The player's hand of cards
    private int bankroll;        // The player's total available tokens
    private int bet;             // The current bet amount

    // Constructor to initialize a player with a bankroll
    public Player(int initialBankroll) {
        hand = new Hand();
        bankroll = initialBankroll;
        bet = 0;
    }
   
    public int getBet() {

        return bet;
    }
    // Method to place a bet, deducting the amount from the bankroll
    public void placeBet(int amount) {
        if (amount <= bankroll && amount > 0) {
            bet = amount;
            bankroll -= bet;
        } else {
            System.out.println("Invalid bet amount.");
        }
    }
    public void doubledown(int amount) {
    	if(amount*2<=bankroll && amount>0) {
    		bet=amount*2;
    		bankroll-=bet;
    	}else {
    		System.out.println("Invalid not enough chips to double down");
    	}
    }

    // Method to add winnings back to the bankroll
    public void winBet() {
        bankroll += bet * 2; // 1:1 payout for winning
    }

    // Method to handle a tie (bet returned to bankroll)
    public void tieBet() {
        bankroll += bet;
    }

    // Method to add a card to the player's hand
    public void addCardToHand(Card card) {
        hand.addCard(card);
    }

    // Method to clear the player's hand for a new round
    public void clearHand() {
        hand.clearHand();
    }

    // Method to check if the player’s hand is a bust
    public boolean isBust() {
        return hand.isBust();
    }

    // Method to check if the player has Blackjack
    public boolean hasBlackjack() {
        if (hand.hasBlackjack()){
            bankroll += bet;
        }
        return hand.hasBlackjack();
    }

    // Getter for the player's hand
    public void getHand() {
         hand.displayHand();
    }

    // Getter for the player's bankroll
    public int getBankroll() {
        return bankroll;
    }

    // Getter for the player's hand total
    public int getHandTotal() {
        return hand.getTotalValue();
    }

    // Display the player's hand and bankroll
    public void displayPlayerInfo() {
        System.out.println("Player's Hand:");
        hand.displayHand();
        System.out.println("Bankroll: " + bankroll);
    }
}
