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
            if (player.hasBlackjack()){
                System.out.println("You have Blackjack. Player Wins!");
                break;
            }
            System.out.println("Choose an action: 1) Hit 2) Stand 3) Double down");
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
            } else if (choice == 3) {//double down
                player.addCardToHand(deck.dealCard());
                player.displayPlayerInfo();

                player.doubledown(player.getBet());
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
        Console.clearScreen();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWelcome to Blackjack!");

        boolean playagain = true;
        while (playagain == true) {
            System.out.print("Enter bet ammount: ");
            int betAmount = scanner.nextInt();
            player.placeBet(betAmount);

            startRound();
            playerTurn(scanner);

            if (!player.isBust()) {
                dealerTurn();
            }

            determineOutcome();

            System.out.println("Would you like to continue playing? 1/yes : 0/no");
            int choice1 = scanner.nextInt();
            if (choice1 == 0) {
                playagain = false;
                System.out.println("Player has choosen to stop playing, final bankroll amount: " + player.getBankroll());
            } else if (choice1 == 1) {
                Console.clearScreen();
                System.out.println("PLayer has choosen to continue playing, Goodluck!");
            } else {
                System.out.println("Invalid option");
                break;
            }


        }
        scanner.close();
    }
}

