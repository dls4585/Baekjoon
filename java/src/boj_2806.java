import java.io.*;
import java.util.StringTokenizer;

public class boj_2806 {
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        s = br.readLine();
        StringTokenizer st2 = new StringTokenizer(s);
        A = new int[N + 1];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st2.nextToken());
        }

        int left, right;
        left = right = 0;
        int total = A[left];
        int count = 0;
        int min = 100010;
        while (left < N) {
            if (total >= S) {
                min = Math.min(min, right - left + 1);
                if (right == left) total += A[++right];
                total -= A[left++];
            } else {
                total += A[++right];
            }
            if (right == N) break;
        }
        if(min == 100010) min = 0;
        bw.write(Integer.toString(min) + "\n");
        bw.flush();
        bw.close();
    }
}
