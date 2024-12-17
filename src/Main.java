import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    private static final String[] COLORS = {"Red", "Blue", "Green", "Yellow", "White", "Black"};
    private String[] code;
    private int attempts = 10;

    public Main() {
        code = createCode();
    }
    private String[] createCode() {
        Random rand = new Random();
        ArrayList<String> availableColors = new ArrayList<>();
        Collections.addAll(availableColors, COLORS);

        String[] newCode = new String[4];
        for (int i = 0; i < 4; i++) {
            int index = rand.nextInt(availableColors.size());
            newCode[i] = availableColors.get(index);
            availableColors.remove(index);
        }
        return newCode;
    }

    private void checkGuess(String[] guess) {
        int correctPos = 0;
        int correctColor = 0;

        for (int i = 0; i < 4; i++) {
            if (guess[i].equals(code[i])) {
                correctPos++;
            } else if (contains(guess[i])) {
                correctColor++;
            }
        }

        System.out.println("Correct position: " + correctPos);
        System.out.println("Correct color: " + correctColor);
    }

    private boolean contains(String color) {
        for (String c : code) {
            if (c.equals(color)) {
                return true;
            }
        }
        return false;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (attempts > 0) {
            System.out.println("Guess 4 colors (Red, Blue, Green, Yellow, White, Black):");
            System.out.println("Attempts left: " + attempts);
            String input = scanner.nextLine();
            String[] guess = input.split(" ");

            if (guess.length != 4) {
                System.out.println("You must guess 4 colors.");
                continue;
            }

            checkGuess(guess);
            attempts--;

            if (checkWin(guess)) {
                System.out.println("Victory!! .. You win!");
                return;
            }
        }

        System.out.println("Game over! There was:");
        for (String color : code) {
            System.out.print(color + " ");
        }
        System.out.println();
    }

    private boolean checkWin(String[] guess) {
        for (int i = 0; i < 4; i++) {
            if (!guess[i].equals(code[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.start();
    }
}
