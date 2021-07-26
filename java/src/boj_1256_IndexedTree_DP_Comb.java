import java.io.*;
import java.util.StringTokenizer;

public class boj_1256_IndexedTree_DP_Comb {
    static int[][] dp;
    static int max = (int) 1e9;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int Na = Integer.parseInt(st.nextToken());
        int Mz = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        dp = new int[201][201];
        long totalC = getCombination(Na + Mz, Mz);
        if(totalC < K) {
            bw.write("-1\n");
            bw.flush();
            bw.close();
            return;
        }
        query(Na, Mz, K);

        bw.write(sb.toString() + "\n");
        bw.flush();
        bw.close();
    }

    static void query(int aCount, int zCount, long target) {
        if(aCount + zCount == 0) {
            return;
        }
        else if(aCount == 0) {
            // a 다씀
            sb.append("z");
            // z 써야함
            query(aCount, zCount - 1, target);
        }
        else if(zCount == 0) {
            sb.append("a");
            query(aCount - 1, zCount, target);
        }
        else {
            int leftCount = getCombination(aCount + zCount - 1, zCount); // a를 하나 쓰면 왼쪽으로 감
            if(leftCount >= target) { // 왼쪽으로 갈 수 있음
                // a 하나 씀
                sb.append("a");
                query(aCount - 1, zCount, target);
            } else { // 오른쪽으로 감
                // z 하나 씀 (target - leftCount)
                sb.append("z");
                query(aCount, zCount - 1, target - leftCount);
            }
        }

    }

    static int getCombination(int n, int k) { // nCk
        if (k == n || k == 0) {
            return 1;
        } else if (dp[n][k] != 0) {
            return dp[n][k];
        }
        return dp[n][k] = Math.min(max, getCombination(n - 1, k - 1) + getCombination(n - 1, k));
    }
}
