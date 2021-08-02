package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_1735 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] za = new int[2];
        int[] mo = new int[2];

        for (int i = 0; i < 2; i++) {
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            za[i] = Integer.parseInt(st.nextToken());
            mo[i] = Integer.parseInt(st.nextToken());
        }

        int gcdA = gcd(mo[0], za[0]);
        int gcdB = gcd(mo[1], za[1]);
        za[0] /= gcdA;
        mo[0] /= gcdA;
        za[1] /= gcdB;
        mo[1] /= gcdB;

        int moo = mo[0] * mo[1];
        int zaA = za[0] * mo[1];
        int zaB = za[1] * mo[0];

        int Za = zaA + zaB;
        int GCD = gcd(moo, Za);
        moo /= GCD;
        Za /= GCD;

        bw.write(Za + " " + moo + "\n");
        bw.flush();
        bw.close();

    }
    static int gcd(int a, int b) {
        while(b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
