package SUAPC;
import java.io.*;
import java.util.*;

public class H {
    static int N;
    static long X, ans = 0;
    static PriorityQueue<Double> pq = new PriorityQueue<>(Comparator.reverseOrder());
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            double a = Double.parseDouble(st.nextToken());
            if(a == X) ans++;
            else pq.add(a);
        }
        while (!pq.isEmpty()) {
            double a = pq.remove();
            if(pq.isEmpty()) break;
            double b = pq.remove();

            double ret = Math.min(a + b + X / 2.0, X);
            if(ret == X) {
                ans++;
            }
            else {
                pq.add(ret);
            }
        }
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }
}