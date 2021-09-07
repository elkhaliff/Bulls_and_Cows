package bullscows;

import java.util.Scanner;

public class Main {

    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        String countSymbols_ = scanner.nextLine();
        int countSymbols;
        if (!isNumber(countSymbols_)) {
            System.out.println("Error: \"" + countSymbols_ + "\" isn't a valid number.");
            return;
        } else
            countSymbols = Integer.parseInt(countSymbols_);

        System.out.println("Input the number of possible symbols in the code:");
        String range_ = scanner.nextLine();
        int range;
        if (!isNumber(range_)) {
            System.out.println("Error: \"" + range_ + "\" isn't a valid number.");
            return;
        } else
            range = Integer.parseInt(range_);

        if (range > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }
        if (countSymbols > range || countSymbols == 0 || range == 0) {
            System.out.println("Error: it's not possible to generate a code with a length of " + countSymbols +
                    " with " + range + " unique symbols.");
            return;
        }
        GameEngine gameEngine = new GameEngine(countSymbols, range);
        System.out.println(gameEngine.welcomeMsg());
        System.out.println("Okay, let's start a game!");
        gameEngine.game();
    }
}