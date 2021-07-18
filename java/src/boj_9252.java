import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class boj_9252 {
    static int[][] memo;
    static String A, B;
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
            if(B.charAt(0) == A.charAt(i)) {
                for (int j = i; j < A.length(); j++) {
                    memo[j][0] = 1;
                }
                break;
            }
        }

        for (int i = 1; i < A.length(); i++) {
            for (int j = 1; j < B.length(); j++) {
                if(memo[i][j] != 0) continue;
                if(A.charAt(i) == B.charAt(j)) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                }
                else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }
            }
        }
        int ret = memo[A.length() - 1][B.length() - 1];
        bw.write(Integer.toString(ret) + "\n");
        Stack<Character> ans = new Stack<>();
        int i = A.length() - 1;
        int j = B.length() - 1;
        while(i >= 0 && j >= 0) {
            if(i == 0){
                if(A.charAt(i) == B.charAt(j)) {
                    if(ans.size() < ret) ans.push(A.charAt(i));
                }
                j--;
            }
            else if(j == 0){
                if(A.charAt(i) == B.charAt(j)) {
                    if(ans.size() < ret) ans.push(A.charAt(i));
                }
                i--;
            }
            else {
                if(A.charAt(i) == B.charAt(j)) {
                    if(ans.size() < ret) ans.push(A.charAt(i));
                    i--; j--;
                } else {
                    if (memo[i - 1][j] > memo[i][j - 1]) {
                        i = i - 1;
                    }
                    else {
                        j = j - 1;
                    }
                }
            }
        }
        while(!ans.empty()) {
            bw.write(ans.pop());
        }
        bw.write("\n");
        bw.flush();
        bw.close();
    }
}
