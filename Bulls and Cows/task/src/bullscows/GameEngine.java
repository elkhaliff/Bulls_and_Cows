package bullscows;

import java.util.Random;
import java.util.Scanner;

public class GameEngine {
    private String secretCode;
    private int turn;
    private boolean win;

    public GameEngine(int countSymbols) {
        win = false;
        turn = 0;
        secretCode = getSecretCode(countSymbols);
    }

    private char inc(char symbol) {
        int step = Character.getNumericValue(symbol);
        step = (step < 9) ? step + 1 : 0;
        return Character.forDigit(step, 10);
    }

    private String setUnique(String strIn) {
        StringBuilder out = new StringBuilder();
        char symbol;
        for (int i = 0; i < strIn.length(); i++) {
            symbol = strIn.charAt(i);
            if (i == 0 && symbol == '0') symbol = inc(symbol);
            while (out.indexOf(Character.toString(symbol)) >= 0)
                symbol = inc(symbol);
            out.append(symbol);
        }
        return out.toString();
    }

    public String getSecretCode(int count) {
        Random random = new Random();
        long longRand = random.nextLong();
        String out = String.valueOf(longRand);
        return setUnique(out.substring(out.length() - count));
    }

    private void makeTurn() {
        Scanner scanner = new Scanner(System.in);
        turn++;
        System.out.println("Turn " + turn + ". Answer:");
        String answer = scanner.nextLine();
        System.out.println(answer);
        System.out.println("Grade: " + checkResult(answer));
    }

    private String checkResult(String answer) {
        String out;
        int bulls = 0;
        int cows = 0;
        char anim;
        for (int i = 0; i < answer.length(); i++) {
            anim = answer.charAt(i);
            if (anim == secretCode.charAt(i))
                bulls++;
            else if (secretCode.contains(Character.toString(anim)))
                cows++;
        }

        if (bulls == 0 && cows == 0)
            out = "None. ";
        else
            out = bulls + " bull(s) and " + cows + " cow(s). ";

        win = secretCode.equals(answer);
        return out;
    }

    private void printWin() {
        System.out.println("Congratulations! You guessed the secret code.");
    }

    public void game() {
        while (!win)
            makeTurn();
        printWin();
    }
}
