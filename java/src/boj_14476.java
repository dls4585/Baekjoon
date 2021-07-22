import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_14476 {
    static int[] LR;
    static int[] RL;
    static int[] num;
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        num = new int[N];
        LR = new int[N];
        RL = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        LR[0] = num[0];
        RL[N - 1] = num[N - 1];

        for (int i = 1; i < N; i++) {
            LR[i] = gcd(LR[i - 1], num[i]);
            RL[N - i - 1] = gcd(RL[N - i], num[N - i - 1]);
        }
        int max = RL[1], maxIndex = -1; // K == 0일 때 RL
        if(max % num[0] != 0) maxIndex = 0;
        K = 1;

        for (; K < N - 1; K++) {
            int gcd = gcd(LR[K - 1], RL[K + 1]);
            if(num[K] % gcd == 0) continue;
            max = Math.max(max, gcd);
            maxIndex = K;
        }

        max = Math.max(max, LR[N - 2]);
        if(maxIndex != -1 && num[maxIndex] % max == 0) maxIndex = -1;

        if(maxIndex == -1) {
            bw.write("-1\n");
        }
        else bw.write(max + " " + num[maxIndex] + "\n");
        bw.flush();
        bw.close();
    }
    // gcd(a,b) = gcd(b, a%b) until a%b == 0
    static int gcd(int a, int b) {
        while(b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
