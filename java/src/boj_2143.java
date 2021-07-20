import java.io.*;
import java.util.*;

public class boj_2143 {
    static long[] A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int T = Integer.parseInt(s);
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new long[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        B = new long[M];
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st2.nextToken());
        }

        List<Long> subA = new ArrayList<>();
        List<Long> subB = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            long sum = A[i];
            subA.add(sum);
            for (int j = i + 1; j < N; j++) {
                sum += A[j];
                subA.add(sum);
            }
        }

        for (int i = 0; i < M; i++) {
            long sum = 0;
            for (int j = i; j <M; j++) {
                sum += B[j];
                subB.add(sum);
            }
        }
        Collections.sort(subA);
        subB.sort(Comparator.reverseOrder());

        long result = 0;
        int p1 = 0, p2 = 0;
        while (p1 < subA.size() && p2 < subB.size()) {
            long currentA = subA.get(p1);
            long target = T - currentA;
            if(subB.get(p2) > target) {
                p2++;
            } else if (subB.get(p2) == target) {
                long countA = 0;
                long countB = 0;
                while (p1 < subA.size() && subA.get(p1) == currentA) {
                    p1++;
                    countA++;
                }
                while (p2 < subB.size() && subB.get(p2) == target) {
                    p2++;
                    countB++;
                }
                result += countA * countB;
            } else {
                p1++;
            }
        }
        bw.write(Long.toString(result) + "\n");
        bw.flush();
        bw.close();
    }
}
