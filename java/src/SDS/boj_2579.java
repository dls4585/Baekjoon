package SDS;

import java.io.*;

public class boj_2579 {
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        dp[1] = arr[1];
        if(N == 1) {
            bw.write(dp[N]+"\n");
            bw.flush();
            bw.close();
            return;
        }
        dp[2] = arr[1] + arr[2];

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 3] + arr[i - 1] + arr[i], dp[i - 2] + arr[i]);
        }
        bw.write(dp[N] + "\n");
        bw.flush();
        bw.close();
    }
}
