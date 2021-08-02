package SDS;

import java.io.*;
import java.util.*;

public class RETRY_boj_1102 {
    static int[][] map, dp; // dp[i][visited]
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();

    }

    static int dfs(int cnt, int visited) {
        // 1. 발전소 수리가 끝난 경우

        // 2. 이미 계산한 값을 알고 있는 경우

        // 3. 반복문 돌면서 재귀 탐색
            // 이중 포문 i가 j를 켜주는 것
            // i 발전소가 켜져있으면
            // 같은 번호의 발전소 continue
            // 만약 j도 켜져있으면 continue
            // 최소값 구하기
                // i의 힘으로 j를 재시작 시켜줬을 때 : dfs(cnt+1, visited | 1 << j) + map[i][j]
                // dp[cnt][visited] 랑 min

        return dp[cnt][visited];
    }
}
