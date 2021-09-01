package SUAPC;
import java.io.*;
import java.util.*;

public class D {
    static double[] p;
    static int N;
    static double ans;
    static boolean[] ON;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        p = new double[N];
        ON = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            p[i] = Double.parseDouble(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            backtracking(0, 0, i);
        }
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }

    static void backtracking(int i, int count, int size) {
        if(count == size) {
            double percent = 1;
            int ONs = count;
            for (int j = 0; j < N; j++) {
                if(ON[j]) {
                    percent *= p[j];
                }
                else {
                    percent *= (1 - p[j]);
                }
            }
            for (int j = 0; j < N-1; j++) {
                if ((ON[j] && !ON[j + 1]) || (!ON[j] && ON[j + 1])) {
                    ONs++;
                }
            }
            ans += (percent * ONs);
            return;
        }
        for (int j = i; j < N; j++) {
            if(ON[j]) continue;
            ON[j] = true;
            backtracking(j, count + 1, size);
            ON[j] = false;
        }
    }
}
