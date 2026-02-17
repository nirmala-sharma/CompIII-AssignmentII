import java.util.Stack;
public class NQueenProblem {

    static int n = 0;
    static int solution = 0;
    public static Stack<Position> stack = new Stack<>();
    static int filled = 0;
    static char[][] board;

    public static void main(String[] args) {
        int size = 8;   // default value

        if (args.length > 0) {
            size = Integer.parseInt(args[0]);  // override if user gives input
        }

        if (size < 4) {
            System.out.println("Invalid input (" + size + "). Please provide a value of n >= 4.");
        } else {
            new NQueenProblem(size);
            placement();
        }
    }

    public NQueenProblem(int number) {
        n = number;
        board = new char[n][n];
    }

    static void placement() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '-';
            }
        }

        stack.clear();
        int row = 0;
        filled = 0;
        int[] nextCol = new int[n];

        while (row >= 0) {
            boolean placed = false;

            if (row < n) {
                for (int col = nextCol[row]; col < n; col++) {
                    if (isSafe(board, row, col, n)) {
                        board[row][col] = 'Q';
                        stack.push(new Position(row, col));
                        filled++;

                        nextCol[row] = col + 1;
                        row++;
                        if (row < n) nextCol[row] = 0;

                        placed = true;
                        break;
                    }
                }
            }

            if (row == n) {
                print();

                row--;
                Position last = stack.pop();
                board[last.row][last.col] = '-';
                filled--;

                continue;
            }

            if (!placed) {
                nextCol[row] = 0;
                row--;

                if (row >= 0) {
                    Position last = stack.pop();
                    board[last.row][last.col] = '-';
                    filled--;
                }
            }
        }
    }

    static void print() {
        solution++;
        System.out.println(solution + ".");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((board[i][j] == 'Q' ? "Q" : "-") + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean isSafe(char[][] board, int row, int col, int n) {
        for (int r = 0; r < row; r++) {
            if (board[r][col] == 'Q') return false;
        }

        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c] == 'Q') return false;
        }

        for (int r = row - 1, c = col + 1; r >= 0 && c < n; r--, c++) {
            if (board[r][c] == 'Q') return false;
        }

        return true;
    }

    static class Position {
        int row;
        int col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
