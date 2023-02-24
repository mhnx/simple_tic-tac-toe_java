package tictactoe;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // String boardPosition = scanner.next();
        String boardPosition = "_________";
        printBoard(boardPosition);
        makeAMove(boardPosition);
    }

    static void printBoard(String boardPosition) {
        System.out.print("---------");
        for (int i = 0; i < 9; i++) {
            if (i == 0 || i == 3 || i == 6) {
                System.out.print("\n|");
            }
            System.out.print(" " + boardPosition.charAt(i));
            if (i == 2 || i == 5 || i == 8) {
                System.out.print(" |");
            }
        }
        System.out.println("\n---------");
    }

    private static int analyzeBoard(String boardPosition) {
        boolean hasEmptyCells = boardPosition.contains("_");
        boolean xWins = (
                    (boardPosition.charAt(0) == 'X' && boardPosition.charAt(1) == 'X' && boardPosition.charAt(2) == 'X') ||
                    (boardPosition.charAt(3) == 'X' && boardPosition.charAt(4) == 'X' && boardPosition.charAt(5) == 'X') ||
                    (boardPosition.charAt(6) == 'X' && boardPosition.charAt(7) == 'X' && boardPosition.charAt(8) == 'X') ||
                    (boardPosition.charAt(0) == 'X' && boardPosition.charAt(3) == 'X' && boardPosition.charAt(6) == 'X') ||
                    (boardPosition.charAt(1) == 'X' && boardPosition.charAt(4) == 'X' && boardPosition.charAt(7) == 'X') ||
                    (boardPosition.charAt(2) == 'X' && boardPosition.charAt(5) == 'X' && boardPosition.charAt(8) == 'X') ||
                    (boardPosition.charAt(0) == 'X' && boardPosition.charAt(4) == 'X' && boardPosition.charAt(8) == 'X') ||
                    (boardPosition.charAt(2) == 'X' && boardPosition.charAt(4) == 'X' && boardPosition.charAt(6) == 'X')
                );
        boolean oWins = (
                    (boardPosition.charAt(0) == 'O' && boardPosition.charAt(1) == 'O' && boardPosition.charAt(2) == 'O') ||
                    (boardPosition.charAt(3) == 'O' && boardPosition.charAt(4) == 'O' && boardPosition.charAt(5) == 'O') ||
                    (boardPosition.charAt(6) == 'O' && boardPosition.charAt(7) == 'O' && boardPosition.charAt(8) == 'O') ||
                    (boardPosition.charAt(0) == 'O' && boardPosition.charAt(3) == 'O' && boardPosition.charAt(6) == 'O') ||
                    (boardPosition.charAt(1) == 'O' && boardPosition.charAt(4) == 'O' && boardPosition.charAt(7) == 'O') ||
                    (boardPosition.charAt(2) == 'O' && boardPosition.charAt(5) == 'O' && boardPosition.charAt(8) == 'O') ||
                    (boardPosition.charAt(0) == 'O' && boardPosition.charAt(4) == 'O' && boardPosition.charAt(8) == 'O') ||
                    (boardPosition.charAt(2) == 'O' && boardPosition.charAt(4) == 'O' && boardPosition.charAt(6) == 'O')
                );

        int xs = 0;
        int os = 0;

        boolean impossible = false;

        for (int i = 0; i < 9; i++) {
            if (boardPosition.charAt(i) == 'X') {
                xs++;
            } else if (boardPosition.charAt(i) == 'O') {
                os++;
            }
        }

        if (xs - os >= 2 || xs - os <= -2) {
            impossible = true;
        }

        // if (!xWins && !oWins && hasEmptyCells) { System.out.println("Game not finished"); }
        if (!xWins && !oWins && !hasEmptyCells) { System.out.println("Draw"); return 1; }
        if (xWins && !oWins) { System.out.println("X wins"); return 1; }
        if (!xWins && oWins) { System.out.println("O wins"); return 1; }
        // if (xWins && oWins || impossible) { System.out.println("Impossible "); }

        return 0;
    }

    static void makeAMove(String boardPosition) {
        Scanner scanner = new Scanner(System.in);
        boolean loop;
        String str;
        int turnCounter = 0;

        do {
            loop = false;
            int cell = -1;

            int x = 0;
            int y = 0;

            do {
                try {
                    str = scanner.nextLine();
                    str = str.trim();

                    Scanner sc = new Scanner(str);
                    x = Integer.valueOf(sc.next());
                    y = Integer.valueOf(sc.next());
                    loop = false;
                } catch (Exception e) {
                    System.out.println("You should enter numbers!");
                    loop = true;
                }
            } while (loop);

            if (!loop && (x > 3 || x < 1 || y > 3 || y < 1)) {
                System.out.println("Coordinates should be from 1 to 3!");
                loop = true;
            }

            if (!loop) {
                if (x == 1 && y == 1) { cell = 0; }
                if (x == 1 && y == 2) { cell = 1; }
                if (x == 1 && y == 3) { cell = 2; }
                if (x == 2 && y == 1) { cell = 3; }
                if (x == 2 && y == 2) { cell = 4; }
                if (x == 2 && y == 3) { cell = 5; }
                if (x == 3 && y == 1) { cell = 6; }
                if (x == 3 && y == 2) { cell = 7; }
                if (x == 3 && y == 3) { cell = 8; }
            }

            if (!loop && boardPosition.charAt(cell) != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                loop = true;
            }

            if (!loop) {
                char[] boardChars = boardPosition.toCharArray();

                if (turnCounter % 2 == 0) {
                    boardChars[cell] = 'X';
                } else {
                    boardChars[cell] = 'O';
                }

                turnCounter++;

                boardPosition = String.valueOf(boardChars);
                printBoard(boardPosition);
                if (analyzeBoard(boardPosition) == 1) {
                    loop = false;
                } else {
                    loop = true;
                }
            }
        } while (loop);
    }
}
