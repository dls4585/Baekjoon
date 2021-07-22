import java.io.*;
import java.util.StringTokenizer;

public class boj_2824_retry {
    static long A = 1, B = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A *= Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B *= Integer.parseInt(st2.nextToken());
        }
        long gcd = gcd(A, B);
        if(gcd >= 1000000000) {
            gcd %= 1000000000;
            bw.write(String.format("%09d\n", gcd));
        } else bw.write(gcd + "\n");
        bw.flush();
        bw.close();
    }
    static long gcd(long a, long b) {
        if (a < b) {
            long temp = a;
            a = b;
            b = temp;
        }
        while(b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
