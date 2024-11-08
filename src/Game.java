import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player player;
    private Player dealer;

    public Game(int initialBankroll) {
        deck = new Deck();
        player = new Player(initialBankroll);
        dealer = new Player(0); // Dealer doesn't need a bankroll
    }

    // Method to start a new round
    public void startRound() {
        deck.shuffle();
        player.clearHand();
        dealer.clearHand();

        // Deal initial two cards to both player and dealer
        player.addCardToHand(deck.dealCard());
        dealer.addCardToHand(deck.dealCard());
        player.addCardToHand(deck.dealCard());
        dealer.addCardToHand(deck.dealCard());

        // Display initial hands
        player.displayPlayerInfo();
        System.out.println("Dealer's Hand:");
        dealer.getHand();
    }

    // Method to handle the player’s turn
    private void playerTurn(Scanner scanner) {
        while (true) {
            System.out.println("Choose an action: 1) Hit 2) Stand");
            int choice = scanner.nextInt();

            if (choice == 1) { // Hit
                player.addCardToHand(deck.dealCard());
                player.displayPlayerInfo();

                if (player.isBust()) {
                    System.out.println("You busted!");
                    break;
                }
            } else if (choice == 2) { // Stand
                break;
            }
        }
    }

    // Method to handle the dealer’s turn
    private void dealerTurn() {
        while (dealer.getHandTotal() < 17) {
            dealer.addCardToHand(deck.dealCard());
        }
    }

    // Method to determine and display the outcome
    private void determineOutcome() {
        int playerTotal = player.getHandTotal();
        int dealerTotal = dealer.getHandTotal();

        System.out.println("Dealer's Hand:");
        dealer.getHand();

        if (player.isBust()) {
            System.out.println("Dealer wins.");
        } else if (dealer.isBust() || playerTotal > dealerTotal) {
            System.out.println("Player wins.");
            player.winBet();
        } else if (playerTotal == dealerTotal) {
            System.out.println("It's a tie.");
            player.tieBet();
        } else {
            System.out.println("Dealer wins.");
        }

        System.out.println("Bankroll after round: " + player.getBankroll());
    }

    // Method to run the game loop
    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Blackjack!");
        System.out.print("Enter your bet amount: ");
        int betAmount = scanner.nextInt();
        player.placeBet(betAmount);

        startRound();
        playerTurn(scanner);

        if (!player.isBust()) {
            dealerTurn();
        }

        determineOutcome();
        scanner.close();
    }
}