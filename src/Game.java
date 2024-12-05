import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player player;
    private Player dealer;
    Console console = new Console();
    Sound sound = new Sound();
    boolean firstRound = true;

    public Game(int initialBankroll) {
        deck = new Deck();
        player = new Player(initialBankroll);
        dealer = new Player(0); // Dealer doesn't need a bankroll
    }

    // Method to start a new round
    public void startRound() {
        if (firstRound){
            deck.shuffle();
            deck.shuffle();
            firstRound = false;
        }
        player.clearHand();
        dealer.clearHand();

        // Deal initial two cards to both player and dealer
        player.addCardToHand(deck.dealCard());
        dealer.addCardToHand(deck.dealCard());
        player.addCardToHand(deck.dealCard());
        dealer.addCardToHand(deck.dealCard());

        // Display initial hands
        displayTable();
    }

    // Method to handle the player’s turn
    private void playerTurn(Scanner scanner) {
        while (true) {
            try {
                if (player.hasBlackjack()) {
                    System.out.println("You have Blackjack!");
                    break;
                }
                System.out.println("Choose an action: \n1) Hit \n2) Stand \n3) Double down");
                int choice = scanner.nextInt();

                if (choice == 1) { // Hit
                    player.addCardToHand(deck.dealCard());
                    displayTable();
                    if (player.isBust()) {
                        System.out.println("You busted!");
                        break;
                    }
                } else if (choice == 2) { // Stand
                    displayTable();
                    break;
                } else if (choice == 3) { // Double Down
                    player.addCardToHand(deck.dealCard());
                    player.doubledown(player.getBet());
                    displayTable();
                    break;
                } else {
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }


    // Method to handle the dealer’s turn
    private void dealerTurn() {
        while (dealer.getHandTotal() < 17) {
            dealer.addCardToHand(deck.dealCard());
            displayTable();
        }
    }

    // Method to determine and display the outcome
    private void determineOutcome() {
        int playerTotal = player.getHandTotal();
        int dealerTotal = dealer.getHandTotal();


        if (player.isBust()) {
            sound.playSound("bust");
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

    public void displayTable() {
        console.clearScreen();
        System.out.println("\n" + Colors.PURPLE + "|--------TABLE--------|\n" + Colors.STOP);

        System.out.println("Dealer's Hand:");
        dealer.getHand();
        player.displayPlayerInfo();

        System.out.println("\n" + Colors.PURPLE + "|---------------------|\n" + Colors.STOP);

        System.out.println("Bankroll: " + Colors.GREEN + player.getBankroll() + Colors.STOP);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to run the game loop
    public void playGame() {
        Console.clearScreen();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWelcome to Blackjack!");

        boolean playAgain = true;
        while (playAgain) {
            try {
                System.out.println("Bankroll: " + Colors.GREEN + player.getBankroll() + Colors.STOP);

                // Validate bet amount
                int betAmount = -1;
                while (betAmount <= 0 || betAmount > player.getBankroll()) {
                    System.out.print("Enter bet amount (must be within your bankroll): ");
                    if (scanner.hasNextInt()) {
                        betAmount = scanner.nextInt();
                        if (betAmount <= 0) {
                            System.out.println("Bet amount must be a positive number.");
                        } else if (betAmount > player.getBankroll()) {
                            System.out.println("You can't bet more than your current bankroll.");
                        }
                    } else {
                        System.out.println("Invalid input! Please enter a number.");
                        scanner.nextLine(); // Clear invalid input
                    }
                }

                player.placeBet(betAmount);

                startRound();
                playerTurn(scanner);

                if (!player.isBust()) {
                    dealerTurn();
                }

                determineOutcome();

                // Validate play again choice
                int choice = -1;
                while (choice != 0 && choice != 1) {
                    System.out.println("Would you like to continue playing? \n1) Yes\n0) No");
                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                        if (choice != 0 && choice != 1) {
                            System.out.println("Invalid option. Please enter 1 for Yes or 0 for No.");
                        }
                    } else {
                        System.out.println("Invalid input! Please enter 1 or 0.");
                        scanner.nextLine(); // Clear invalid input
                    }
                }

                if (choice == 0) {
                    playAgain = false;
                    System.out.println("You chose to stop playing. Final bankroll: " + player.getBankroll());
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}

