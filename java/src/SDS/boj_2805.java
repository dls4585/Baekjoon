package SDS;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2805 {
    static long[] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        trees = new long[N];

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        long end = 0;
        for (int i = 0; i < N; i++) {
            trees[i] = Long.parseLong(st2.nextToken());
            end = Math.max(end, trees[i]);
        }

        long start = 0, mid = end / 2;
        while(true) {
            long total = 0;
            for (int i = 0; i < N; i++) {
                long res = trees[i] - mid;
                if (res < 0) res = 0;
                total += res;
            }
            if(total == M) break;
            else if(total > M) {
                start = mid;
                mid = (start + end) / 2;
            } else {
                end = mid;
                mid = (start + end) / 2;
            }
            if (end - start == 1) {
                break;
            }
        }
        bw.write(Long.toString(mid) + "\n");
        bw.flush();
        bw.close();
    }
}
