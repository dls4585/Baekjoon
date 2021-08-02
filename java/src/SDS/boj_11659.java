package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_11659 {
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        dp = new int[N + 1];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st2.nextToken());
        }
        dp[1] = arr[1];
        for (int i = 1; i <= N - 1; i++) {
            dp[i + 1] = dp[i] + arr[i + 1];
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st3 = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st3.nextToken());
            int end = Integer.parseInt(st3.nextToken());

            sb.append(dp[end] - dp[start - 1]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
