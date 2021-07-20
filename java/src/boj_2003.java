import java.io.*;
import java.util.StringTokenizer;

public class boj_2003 {
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        s = br.readLine();
        StringTokenizer st2 = new StringTokenizer(s);
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st2.nextToken());
        }

        int left, right;
        left = right = 0;
        int total = 0;
        int count = 0;
        total = A[left];
        while (left < N) {
            if (total == M) {
                total -= A[left];
                if(total == 0) {
                    right++;
                    if(right == N) right--;
                }
                left++;
                count++;
            }
            else if (total < M) {
                if (right == N - 1) break;
                right++;
                total += A[right];
            } else {
                total -= A[left];
                left++;
            }
            if(left == right) {
                total = A[left];
            }
        }
        bw.write(Integer.toString(count) + "\n");
        bw.flush();
        bw.close();
    }
}
