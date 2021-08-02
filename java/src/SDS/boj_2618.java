package SDS;

import java.io.*;
import java.util.*;

public class boj_2618 {
    static int[][] dp; // dp[i][j] : 경찰차1이 i 사건을 경찰차2가 j사건을 처리했을 때 이동거리 합
    static XY[] events;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();
    }
}

class XY {
    int X, Y;
    XY(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
}