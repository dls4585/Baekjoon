import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1837 {
    static List<Integer> primes = new ArrayList<>();
    static boolean[] isPrime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        String P = st.nextToken();
        int K = Integer.parseInt(st.nextToken());
        isPrime = new boolean[K];
        Arrays.fill(isPrime, true);
        makePrimeList(K);

        boolean badFlag = false;
        for (int i : primes) {
            int len = P.length();
            int r = 0;
            for (int j = 0; j < len; j++) {
                int a = Integer.parseInt(String.valueOf(s.charAt(j)));
                a = r * 10 + a;
                if(a / i == 0) {
                    r = a;
                }
                else {
                    r = a % i;
                }
            }
            if (r == 0) {
                badFlag = true;
                bw.write("BAD " + i + "\n");
                break;
            }
        }
        if(!badFlag) {
            bw.write("GOOD\n");
        }
        bw.flush();
        bw.close();
    }
    static void makePrimeList(int K) {
        for (int i = 2; i < K; i++) {
            if(isPrime[i]) {
                for (int j = i*2; j < K; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
        for (int i = 2; i < K; i++) {
            if(isPrime[i]) primes.add(i);
        }
    }

}
