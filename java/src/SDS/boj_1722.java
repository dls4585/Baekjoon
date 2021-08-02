package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_1722 {
    static int[] p;
    static long[] factorial;
    static boolean[] isUsed;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        factorial = new long[N + 1];
        isUsed = new boolean[N + 1];
        if(M == 1) {
            long k = Integer.parseInt(st.nextToken());
            query(N, k);
            bw.write("\n");
        }
        else {
            p = new int[N];
            for (int i = 0; i < N; i++) {
                p[i] = Integer.parseInt(st.nextToken());
            }
        }
        bw.flush();
        bw.close();
    }
//    static void reverseQuery(int N) {
//        int candidate;
//        for (int i = 0; i < N; i++) {
//            candidate =
//        }
//    }
    static void query(int n, long target) {
        if(target == 1) {
            for (int i = 1; i <= n; i++) {
                if(!isUsed[i]) {
                    sb.append(i);
                    break;
                }
            }
            return;
        }
        long fact = getFactorial(n - 1);
        int range = 0;
        for (int i = 1; i <= n; i++) {
            if(target <= i * fact) {
                if(isUsed[i])
                range = i;
                break;
            }
        }
        sb.append(range);
        sb.append(" ");
        isUsed[range] = true;
        long leftCount = (range - 1) * fact;
        query(n - 1, target - leftCount);
    }
    static long getFactorial(int n) {
        if(n == 0) return 1;
        else if (factorial[n] != 0) {
            return factorial[n];
        }
        else {
            return factorial[n] = n * getFactorial(n - 1);
        }
    }
}
