package SUAPC;
import java.io.*;
import java.util.*;

public class C {
    static int count = 0, N, M;
    // 상 하 좌 우
    static int[][] dp;
    static char[][] map; //
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            String S = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = S.charAt(j);
                dp[i][j] = 1;
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (map[i][j] == map[i - 1][j - 1] && map[i][j] != map[i - 1][j] && map[i][j] != map[i][j - 1]) {
                    dp[i][j] = Math.max(dp[i][j], Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][i])) + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans += dp[i][j];
            }
        }
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }
}
