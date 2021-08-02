package SDS;

import java.io.*;
import java.util.Arrays;

public class boj_9251 {
    static String A, B;
    static int[][] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        A = br.readLine();
        B = br.readLine();
        memo = new int[A.length()][B.length()];

        for (int[] ints : memo) {
            Arrays.fill(ints, 0);
        }
        memo[0][0] = A.charAt(0) == B.charAt(0) ? 1 : 0;

        for (int i = 0; i < B.length(); i++) {
            if(A.charAt(0) == B.charAt(i)) {
                Arrays.fill(memo[0], i, memo[0].length , 1);
                break;
            }
        }
        for (int i = 0; i < A.length(); i++) {
            if(A.charAt(i) == B.charAt(0)) {
                for (int j = i; j < memo.length; j++) {
                    memo[j][0] = 1;
                }
                break;
            }
        }

        for (int i = 1; i < memo.length; i++) {
            for (int j = 1; j < memo[i].length; j++) {
                if(memo[i][j] != 0) continue;
                if(A.charAt(i) == B.charAt(j)) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }
            }
        }
        int ret = memo[A.length() - 1][B.length() - 1];
        bw.write(Integer.toString(ret) + "\n");
        bw.flush();
        bw.close();
    }
}
