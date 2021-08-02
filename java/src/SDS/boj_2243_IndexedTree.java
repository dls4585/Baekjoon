package SDS;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2243_IndexedTree {
    static int M = 1000000, S;
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        S = 1;
        while(S < M) {
            S *= 2;
        }
        tree = new long[4 * (M + 1)];
        Arrays.fill(tree, 0);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if(A == 2) {
                int C = Integer.parseInt(st.nextToken());
                update(1, M, 1, B, C);
            } else {
                int ret = query(1, M, 1, B);
                bw.write(ret + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    static int query(int left, int right, int node, long queryRank) {
        // 도착
        if(left == right) {
            update(1, M, 1, left, -1);
            return left;
        }
        int mid = (left + right) / 2;
        if(tree[2*node] >= queryRank) { // 왼쪽 자식이 포함하고 있으면
            return query(left, mid,2 * node, queryRank);
        }
        else { // 오른쪽으로 가야되면
            return query(mid + 1, right, 2 * node + 1, queryRank - tree[2 * node]);
        }
    }

    static void update(int left, int right, int node, int target, int diff) {
        // 연관 없음
        // do nothing
        // 연관 있음
        if(target >= left && target <= right) {
            tree[node] += diff;
            if(left == right) return;
            if(2*node < tree.length) {
                int mid = (left + right) / 2;
                update(left, mid, 2*node, target, diff);
                update(mid + 1, right, 2 * node + 1, target, diff);
            }
        }
    }
}
