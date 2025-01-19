import java.util.Scanner;

public class TicTacToe {
    // Constants for the game pieces
    static final char CROSS = 'X';
    static final char CIRCLE = 'O';
    static final char EMPTY = ' ';

    // Method to display the current state of the board
    public static void displayBoard(char[][] board) {
        System.out.println("+---+---+---+");
        for (int i = 0; i < board.length; i++) {
            System.out.printf("| %c | %c | %c |%n", board[i][0], board[i][1], board[i][2]);
            System.out.println("+---+---+---+");
        }
    }

    // Method to insert a move on the board
    public static boolean insertMove(int row, int col, char[][] board, char currentMove) {
        // Check if the position is empty
        if (board[row][col] == EMPTY) {
            board[row][col] = currentMove;
            return true;
        }
        return false;
    }

    // Method to check if there's a winner
    public static boolean checkWinner(char[][] board, char currentMove) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentMove && board[i][1] == currentMove && board[i][2] == currentMove) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentMove && board[1][j] == currentMove && board[2][j] == currentMove) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == currentMove && board[1][1] == currentMove && board[2][2] == currentMove) {
            return true;
        }
        if (board[0][2] == currentMove && board[1][1] == currentMove && board[2][0] == currentMove) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Initialize the board with empty spaces
        char[][] board = {
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY}
        };

        int moveCount = 0;
        char currentMove = CROSS;  // X starts first
        boolean gameWon = false;

        // Main game loop
        while (!gameWon && moveCount < 9) {
            // Display the current board
            displayBoard(board);

            // Get player move
            System.out.printf("Player %c enter your move (row col): ", currentMove);
            int row = input.nextInt();
            int col = input.nextInt();

            // Validate input
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid move! Row and column must be between 0 and 2.");
                continue;
            }

            // Try to insert the move
            if (!insertMove(row, col, board, currentMove)) {
                System.out.println("That position is already taken! Try again.");
                continue;
            }

            // Check for winner
            if (checkWinner(board, currentMove)) {
                displayBoard(board);
                System.out.printf("Player %c wins!%n", currentMove);
                gameWon = true;
            } else {
                moveCount++;
                // Switch players
                currentMove = (currentMove == CROSS) ? CIRCLE : CROSS;
            }
        }

        // Check for tie
        if (!gameWon) {
            displayBoard(board);
            System.out.println("Game is a tie!");
        }

        input.close();
    }
}