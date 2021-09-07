package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        int cnt = scanner.nextInt();
        if (cnt > 10) {
            System.out.println("Error: can't generate a secret number with a length of " + cnt +
                    " because there aren't enough unique digits.");
            return;
        }
        System.out.println("Okay, let's start a game!");
        GameEngine gameEngine = new GameEngine(cnt);
        gameEngine.game();
    }
}
