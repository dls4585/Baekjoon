package SUAPC;
import java.io.*;
import java.util.*;

public class A {
    static int N;
    static long K;
    static long[] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());
        v = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(v);
        long ans = 0;
        long m = v[0];
        for (int x = 1; x < N; x++) {
            ans = Math.max(ans, m * x + v[x] * (N - x));
        }
        long ret = (long) Math.ceil((double) K / ans);
        bw.write(ret + "\n");
        bw.flush();
        bw.close();
    }
}
