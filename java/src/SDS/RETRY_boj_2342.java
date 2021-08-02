package SDS;

import java.io.*;
import java.util.List;

public class RETRY_boj_2342 {
    static int[][][] dp; // i번째 스텝, 왼발(j) 위치, 오른발(k) 위치
    static List<Integer> input;
    static int N;
    public static void main(String[] args) {

        dp = new int[N][5][5];

    }
    static int DDR(int step, int left, int right) {
        // 1. 마지막 스텝 도달하면 끝

        // 2. 이미 값을 알고 있으면 return

        // 3. 가능한 경우의 수 중 가장 작은 경우로 갱신하고 return
        // 왼발 움직이는 경우 vs 오른발 움직이는 경우

        return dp[step][left][right]; // = Math.min(leftScore , rightScore)
    }
    static int cost(int from, int to) {

        // 지금 위치 그대로
        if(from == to) return 1;
        // 가운데 출발
        if(from == 0) return 2;
        // 반대편
        if(Math.abs(from-to) == 2) return 4;
        // 인접 칸
        return 3;
    }
}
