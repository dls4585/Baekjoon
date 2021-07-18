import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2248 {
    static int N, L;
    static long I;
    static int[][] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        I = Long.parseLong(st.nextToken());

        memo = new int[N][L];
        for (int[] ints : memo) {
            Arrays.fill(ints, 0);
        }
        for (int i = 1, j = 0; i <= N; i++) {
            if(dp(i,j) >= I) bw.write("0");
            else {
                bw.write("1");
                I -= dp(i, j);
                j++;
            }
        }
        bw.write("\n");
        bw.flush();
        bw.close();
    }
    public static int dp(int i, int j) { // 길이 i , 1의 개수 j인 prefix를 가진 이진수의 개수
        if(j == L || i >= N) return 1;
        if(memo[i][j] != 0) return memo[i][j];
        return memo[i][j] = dp(i + 1, j) + dp(i + 1, j + 1);
    }
}
