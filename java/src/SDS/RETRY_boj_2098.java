package SDS;

import java.io.*;
import java.util.*;

public class RETRY_boj_2098 {
    static int N;
    static int[][] map, dp;
    static int INF = 50000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();

        int MAX = 1 << N;
        dp = new int[N][MAX];

        // 초기화


    }

    static int TSP (int id, int visited) {
        // DFS

        // 1. 모든 지점 방문한 경우
        if (visited == (1 << N) - 1) {
            // 못 가는 곳이면 무한대 리턴
            // 아니면 알고 있는 값 리턴
        }

        // 2. 이미 계산한 경우
        if(dp[id][visited] != INF) return dp[id][visited];

        // 3. 반복문 돌면서 재귀탐색
        for (int i = 0; i < N; i++) {
            int next = visited | (1 << i); // 다음에 방문할 녀석

            // 이미 방문한 경우 continue

            // 길이 없는 경우 continue

            // TSP 진행
        }
        return dp[id][visited];
    }

}
