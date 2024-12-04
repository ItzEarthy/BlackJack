import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Sound sound = new Sound();
        sound.playSound("jazz");
        int bankroll = 0;
        System.out.println("Enter your bankroll: ");
        Scanner sc = new Scanner(System.in);
        bankroll = sc.nextInt();

        Game game = new Game(bankroll);
        game.playGame();

    }
}