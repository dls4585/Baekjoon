package KAKAO.F;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        for (int[] s : skill) {
            act(board, s);
        }
        for (int[] ints : board) {
            for (int j = 0; j < ints.length; j++) {
                if (ints[j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
    static void act(int[][] board, int[] skill) {
        if(skill[0] == 1) {
            for (int i = skill[1]; i <= skill[3]; i++) {
                for (int j = skill[2]; j <= skill[4]; j++) {
                    board[i][j] -= skill[5];
                }
            }
        } else {
            for (int i = skill[1]; i <= skill[3]; i++) {
                for (int j = skill[2]; j <= skill[4]; j++) {
                    board[i][j] += skill[5];
                }
            }
        }
    }
}

public class F {
    public static void main(String[] args) {
        int[][] board = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] skill = {{1, 1, 1, 2, 2, 4}, {1, 0, 0, 1, 1, 2}, {2, 2, 0, 2, 0, 100}};
        Solution s = new Solution();
        System.out.println(s.solution(board, skill));
    }
}
