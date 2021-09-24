package KAKAO.G;

class Solution {
    static int N, M, max = -1;
    // 상 하 좌 우
    static int[] mx = {0, 0, -1, 1};
    static int[] my = {-1, 1, 0, 0};
    static Point A, B;
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        N = board.length;
        M = board[0].length;
        A = new Point(aloc[0], aloc[1]);
        B = new Point(bloc[0], bloc[1]);

        return max;
    }

    static void backtrack (int[][] board, int count, boolean aTurn) {

        for (int i = 0; i < 4; i++) {
            A.i += my[i];
            A.j += mx[i];
            count++;
            if(checkDone(board, aTurn)) {
                max = Math.max(max, count);
            }
            for (int j = 0; j < 4; j++) {
                B.i += my[i];
                B.j += mx[i];
                if(checkDone(board, aTurn)) {
                    max = Math.max(max, count);
                }
                backtrack(board, count+1, !aTurn);
                B.i -= my[i];
                B.j -= mx[i];
            }
            count--;
            A.i -= my[i];
            A.j -= mx[i];
        }
    }


    static boolean checkDone(int[][] board, boolean aTurn) {
        if (!checkWinConditionA(board, aTurn ? A : B)) {
            if(!aTurn) {

                return true;
            }
            return false;
        }
        if(A.i == B.i && A.j == B.j) {
            if(aTurn) {
                for (int i = 0; i < 4; i++) {
                    int ty = A.i + my[i];
                    int tx = A.j + mx[i];
                    if(canGo(board, ty, tx)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    static boolean checkWinConditionA(int[][] board, Point p) { // false : 헌재 턴이 짐 / true : nothing
        for (int k = 0; k < 4; k++) {
            int ty = p.i + my[k];
            int tx = p.j + mx[k];
            if(!canGo(board, ty, tx)) return false;
        }
        return true;
    }
    static boolean canGo(int[][] board, int ty, int tx) {
        return ty >= 0 && ty < N && tx >= 0 && tx < M && board[ty][tx] == 1;
    }

    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}

public class G {
}
