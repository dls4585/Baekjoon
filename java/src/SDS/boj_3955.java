package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class boj_3955 {
    static int max = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int t = Integer.parseInt(s);


        // x : 인당 나눠줄 사탕 수
        // y : tkxkd qhdwl tn
        // A*x + 1 = B*y
        // -Ax +By = 1
        // A(-x) + By = 1
        for (int i = 0; i < t; i++) {
            s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int A = Integer.parseInt(st.nextToken()); // K
            int B = Integer.parseInt(st.nextToken()); // C

            ExtendedGcdResult result = extendedGcd(A, B);

            // D = gcd(A,B)
            // Ax+By = C 일 때 C%D == 0 이어야 해를 가질 수 있음 : 베주 항등식
            // 이 문제에서 D == 1 이어야 함 because C == 1
            if(result.r != 1) { // 정수해 자체가 존재 x
                bw.write("IMPOSSIBLE\n");
            }
            else {
                // x0 = s * C/D
                // y0 = t * C/D
                long x0 = result.s;
                long y0 = result.t;
                // -x = x0 + B/D * k
                // y = y0 - A/D * k
                long bottom = (long) Math.ceil((double) (y0 - max) / A);
                double top =  Math.min((double) y0 / A, (double) -1*x0 /B);
                long ltop = (long) Math.ceil(top) - 1;
                if(bottom > ltop) {
                    bw.write("IMPOSSIBLE\n");
                    continue;
                }
                long k = (bottom <= 0 && ltop >= 0) ? 0 : ltop;
                long y = y0 - A * k;

                bw.write(y + "\n");

                // k 범위 구하
                // x < 0
                // x0 + B*k < 0
                // k < -x0 / B
                // 0 < y <= max
                // 0 < y0 - A*k <= max
                // - y0 < -A * k <= max - y0

                // (y0 - max) / A <= k < y0/A
                //                   k < -x0 / b

                // (k < y0/A) 교 (h < -x / B)의 최대값 -> 우측 경계값
                // (y0 - max) / A <= k 의 최소값 -> 좌측 경계값

                // 좌측 경계값 > 우측 경계값 : IMPOSSIBLE (정수해는 존재하지만 문제의 범위를 만족하는 정수해 x)

            }
        }
        bw.flush();
        bw.close();
    }

    static ExtendedGcdResult extendedGcd(int A, int B) {
        long s0 = 1, s1 = 0;
        long t0 = 0, t1 = 1;
        long r0 = A, r1 = B;
        long temp = 0, q = 0;

        while(r1 != 0) {
            q = r0 / r1;

            temp = r0 - r1 * q;
            r0 = r1;
            r1 = temp;

            temp = s0 - s1 * q;
            s0 = s1;
            s1 = temp;

            temp = t0 - t1 * q;
            t0 = t1;
            t1 = temp;

        }
        return new ExtendedGcdResult(s0, t0, r0);
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



class ExtendedGcdResult {
    long s, t, r;

    ExtendedGcdResult(long s, long t, long r) {
        this.s = s;
        this.t = t;
        this.r = r;
    }
}
