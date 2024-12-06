# Blackjack Game

This is a text-based Blackjack game implemented in Java. It supports player vs. dealer gameplay, featuring a deck of cards, a betting system, and sound effects.

---

## Table of Contents
- [Project Structure](#project-structure)
- [Requirements](#requirements)
- [Setup](#setup)
- [How to Play](#how-to-play)
- [Gameplay Rules](#gameplay-rules)
- [License](#license)
- [Credits](#credits)

---

## Project Structure

- **`Card.java`**: Represents a single card with a suit, rank, and value.
- **`Deck.java`**: Manages the deck of cards, including shuffling and dealing.
- **`Hand.java`**: Represents a hand of cards for the player or dealer and calculates the total value based on Blackjack rules.
- **`Player.java`**: Represents a player, including their hand, bankroll, and actions (hit, stand, bet, double down).
- **`Sound.java`**: Plays sound effects for different events in the game (e.g., shuffling, betting, winning).
- **`Colors.java`**: Contains ANSI color codes for styled console output.
- **`Console.java`**: Provides functionality to clear the console screen.
- **`Game.java`**: Handles the main game logic, including the player and dealer turns and determining the outcome.
- **`Main.java`**: The entry point for the game, initializing the game with a player’s bankroll and starting the game loop.

---

## Requirements

- **Java**: Java Development Kit (JDK) version 8 or higher.
- **Sound Files**: Ensure `.wav` files are present in the `src/Sounds/` directory:
  - `shuffle.wav`
  - `place.wav`
  - `bet.wav`
  - `win.wav`
  - `bust.wav`
  - `jazz.wav` (for background music)

---

## Setup

1. Clone or download the repository.
2. Place the required sound files into the `src/Sounds/` folder.
3. Open a terminal or command prompt.
4. Navigate to the project's root directory and compile the Java files:
   ```bash
   javac *.java java Main
# How to Play

1. **Start the Game**:
   - Upon launching the game, you'll be prompted to enter your initial bankroll.
   - Once your bankroll is set, the game begins.

2. **Placing Bets**:
   - Enter the amount you want to bet for each round.
   - Your bet cannot exceed your current bankroll.

3. **Gameplay Options**:
   - After the initial deal, choose from the following actions:
     - **Hit**: Draw an additional card.
     - **Stand**: Keep your current hand.
     - **Double Down**: Double your bet and receive one additional card.

4. **Winning Conditions**:
   - **Blackjack**: If your hand totals 21 with the first two cards, you win instantly.
   - **Bust**: If your hand exceeds 21, you lose the round.
   - The dealer must draw until their hand totals at least 17. If the dealer exceeds 21, they bust.
   - If neither you nor the dealer busts, the hand closest to 21 wins.

5. **End of Round**:
   - Your winnings (if any) are added back to your bankroll.
   - Decide whether to play another round or exit the game.

---

# Gameplay Rules

- **Deck**: The game uses a standard deck of 52 cards.
- **Card Values**:
  - Number cards are worth their face value.
  - Face cards (Jack, Queen, King) are worth 10.
  - Aces can be worth 1 or 11, depending on which value keeps the hand under 21.
- **Dealer Rules**:
  - The dealer must draw cards until their hand totals at least 17.
  - The dealer automatically stands once they reach 17 or higher.
- **Payouts**:
  - Winning hands pay 1:1 (e.g., if you bet 50 tokens, you win 50 tokens).
  - Blackjack pays 3:2.

---

# License

This game is proprietary and not open-source. The following restrictions apply:

1. **No Derivatives**: You may not modify, adapt, or create derivative works based on this project.
2. **No Monetary Gain**: This game or any part of its code cannot be used for financial profit or commercial purposes.
3. **No AI Training**: This code is not permitted for use in training any machine learning or AI systems.
4. **Attribution**: Any use or sharing of this project or its code must include proper attribution to the original creator.
5. **Non-Transferable**: You may not redistribute this project without explicit permission from the creator.

---

# Credits

- Developed and designed by [Your Name].
- Sound effects sourced from `.wav` files (ensure they comply with your licensing).

---

Thank you for playing Blackjack!  
Enjoy the game responsibly and feel free to share feedback for further improvements.

