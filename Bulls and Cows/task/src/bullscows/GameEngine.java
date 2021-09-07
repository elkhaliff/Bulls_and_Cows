package bullscows;

import java.util.Random;
import java.util.Scanner;

public class GameEngine {
    private final int countSymbols;
    private final int range;
    private String secretCode;
    private int turn;
    private boolean win;

    public String getSecretCode() {
        return secretCode;
    }

    public GameEngine(int countSymbols, int range) {
        this.countSymbols = countSymbols;
        this.range = range;
        win = false;
        turn = 0;
        secretCode = makeSecretCode();
    }

    private String intToStr(int intPos) {
        int res = (intPos < 10) ? intPos + 48 : intPos + 87;
        return Character.toString(res);
    }

    private String getRandSymbol() {
        Random random = new Random();
        int intRand = random.nextInt(range);
        return intToStr(intRand);
    }

    private String makeSecretCode() {
        StringBuilder out = new StringBuilder();
        String symbol;
        for (int i = 0; i < countSymbols; i++) {
            do {
                symbol = getRandSymbol();
            } while (out.indexOf(symbol) >= 0);
            out.append(symbol);
        }
        return out.toString();
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

    public String welcomeMsg() {
        StringBuilder msg = new StringBuilder("The secret is prepared: ");
        for (int i = 0; i < countSymbols; i++)
            msg.append("*");
        msg.append(" (0-");
        if (range < 11)
            msg.append(intToStr(range-1));
        else if (range == 11)
            msg.append("9, a");
        else
            msg.append("9, a-" + intToStr(range-1));
        msg.append(").");
        return msg.toString();
    }
}