import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class MineSweeper {
    private int[][] board;
    private boolean[][] mines;
    private int rowSize;
    private int colSize;
    private int remainingCells;

    public MineSweeper(int rowSize, int colSize) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.remainingCells = rowSize * colSize;

        board = new int[rowSize][colSize];
        mines = new boolean[rowSize][colSize];
        initializeBoard();
        initializeMines();
    }

    private void initializeBoard() {
        for (int i = 0; i < rowSize; i++) {
            Arrays.fill(board[i], -1);
        }
    }

    private void initializeMines() {
        int totalMines = rowSize * colSize / 4;
        Random random = new Random();

        while (totalMines > 0) {
            int row = random.nextInt(rowSize);
            int col = random.nextInt(colSize);

            if (!mines[row][col]) {
                mines[row][col] = true;
                totalMines--;
            }
        }
    }

    public void play() {
        System.out.println("Mayın Tarlası Oyuna Hoşgeldiniz!");
        printBoard();

        Scanner scanner = new Scanner(System.in);
        while (remainingCells > 0) {
            System.out.print("Satır Giriniz: ");
            int row = scanner.nextInt();

            System.out.print("Sütun Giriniz: ");
            int col = scanner.nextInt();

            if (!isValidMove(row, col)) {
                System.out.println("Geçersiz hamle, lütfen tekrar deneyin!");
                continue;
            }

            if (mines[row][col]) {
                System.out.println("Game Over!!");
                revealBoard();
                break;
            } else {
                int adjacentMines = countAdjacentMines(row, col);
                board[row][col] = adjacentMines;
                remainingCells--;
                printBoard();

                if (remainingCells == 0) {
                    System.out.println("Oyunu Kazandınız!");
                    revealBoard();
                }
            }
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < rowSize && col >= 0 && col < colSize && board[row][col] == -1;
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rowSize && j >= 0 && j < colSize && mines[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    private void printBoard() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (board[i][j] != -1) {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println("===========================");
    }

    private void revealBoard() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (mines[i][j]) {
                    System.out.print("* ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("===========================");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Matrisin satır sayısını giriniz: ");
        int rowSize = scanner.nextInt();

        System.out.print("Matrisin sütun sayısını giriniz: ");
        int colSize = scanner.nextInt();

        MineSweeper game = new MineSweeper(rowSize, colSize);
        game.play();
    }
}
