import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        int[] A = new int[N];
        s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        s = br.readLine();
        int M = Integer.parseInt(s);
        int[] target = new int[M];
        s = br.readLine();
        StringTokenizer st2 = new StringTokenizer(s);
        for (int i = 0; i < M; i++) {
            target[i] = Integer.parseInt(st2.nextToken());
        }
        Arrays.sort(A);

        for (int i = 0; i < M; i++) {
            int first = 0, last = A.length - 1;
            boolean res = false;
            while(first <= last) {
                int mid = (first + last) / 2;
                if(A[mid] == target[i]) {
                    res = true;
                    break;
                }
                else {
                    if(target[i] > A[mid]) {
                        first = mid + 1;
                    } else {
                        last = mid - 1;
                    }
                }
            }
            bw.write(res ? "1\n" : "0\n");
        }
        bw.flush();
        bw.close();
    }
}
