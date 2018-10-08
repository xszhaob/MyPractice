package bo.zhao.practice.leetcode;

public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        int[] arr1 = new int[9];
        int[] arr2 = new int[9];
        int[][][] arr3 = new int[3][3][9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[] oneArr = new int[9];
                init(oneArr);
                arr3[i][j] = oneArr;
            }
        }

        for (int i = 0; i < 9; i++) {
            init(arr1);
            init(arr2);
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.' && board[j][i] == '.') {
                    continue;
                }

                if (board[i][j] != '.') {
                    int c = board[i][j] - '0';
                    int[] oneArr = arr3[i / 3][j / 3];
                    if (arr1[c - 1] != -1 || oneArr[c - 1] != -1) {
                        return false;
                    } else {
                        arr1[c - 1] = c;
                        oneArr[c - 1] = c;
                    }
                }

                if (board[j][i] != '.') {
                    int c2 = board[j][i] - '0';
                    if (arr2[c2 - 1] != -1) {
                        return false;
                    } else {
                        arr2[c2 - 1] = c2;
                    }
                }

            }
        }
        return true;
    }

    private void init(int[] arr) {
        for (int i = 0; i < 9; i++) {
            arr[i] = -1;
        }
    }

    public static void main(String[] args) {
        char[][] arr = {
                {'.', '.', '4', '.', '.', '.', '6', '3', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '9', '.'},
                {'.', '.', '.', '5', '6', '.', '.', '.', '.'},
                {'4', '.', '3', '.', '.', '.', '.', '.', '1'},
                {'.', '.', '.', '7', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '5', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
        };
        ValidSudoku sudoku = new ValidSudoku();
        System.out.println(sudoku.isValidSudoku(arr));
    }
}
