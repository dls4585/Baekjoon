package SDS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_11657 {
    static int[] V;
    static List<NS> E = new ArrayList<>();
    static long[] D;
    static final int MAX = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        V = new int[N + 1];
        D = new long[N + 1];
        for (int i = 0; i < M; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st2.nextToken());
            int B = Integer.parseInt(st2.nextToken());
            int C = Integer.parseInt(st2.nextToken());
            E.add(new NS(A, B, C));
        }
        D[1] = 0;
        for (int i = 2; i <= N; i++) {
            D[i] = MAX;
        }
        for (int i = 1; i < N; i++) {
            for (NS e : E) {
                if(D[e.start] == MAX) continue;
                D[e.end] = Math.min(D[e.start] + e.cost, D[e.end]);
            }
        }
        boolean isChanged = false;
        for (NS e : E) {
            if (D[e.start] == MAX) continue;
            if(D[e.start] + e.cost < D[e.end]) { // 갱신 발생
                isChanged = true;
                break;
            }
        }

        if(isChanged) {
            bw.write("-1\n");
        } else {
            for (int i = 2; i <= N; i++) {
                if(D[i] == MAX) sb.append("-1\n");
                else sb.append(D[i]).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

class NS {
    int start, end;
    int cost;

    NS(int start, int end, int cost) {
        this.cost = cost;
        this.start = start;
        this.end = end;
    }
}
