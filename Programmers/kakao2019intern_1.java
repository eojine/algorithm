import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int anwer = 0;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < moves.length; i++) {
            int col = moves[i] - 1;
            int row = 0;
            while (board[row][col] == 0) {
                row++;
                if (row == board.length) break;
            }
            if (row == board.length) continue;

            int pushNum = board[row][col];
            board[row][col] = 0;
            if (!st.isEmpty() && st.peek() == pushNum){
                st.pop();
                anwer += 2;
            } else {
                st.add(pushNum);
            }

        }
        return anwer;
    }
}
