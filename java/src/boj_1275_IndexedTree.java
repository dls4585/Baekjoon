import java.io.*;
import java.util.StringTokenizer;

public class boj_1275_IndexedTree {
    static long[] nums, tree;
    static int N, Q, S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        nums = new long[N];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(st2.nextToken());
        }
        S = 1;
        while (S < N) {
            S *= 2;
        }
        tree = new long[S * 2];
        initBU();
        for (int i = 0; i < Q; i++) {
            StringTokenizer st3 = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st3.nextToken());
            int y = Integer.parseInt(st3.nextToken());
            if(x > y) {
                int temp = x;
                x = y;
                y = temp;
            }
            int a = Integer.parseInt(st3.nextToken());
            long b = Long.parseLong(st3.nextToken());

            long ret = query(1, S, 1, x, y);
            bw.write(ret + "\n");
            update(1, S, 1, a, b - tree[S + a - 1]);
        }
        bw.flush();
        bw.close();
    }
    static void initBU() {
        int n = 0;
        for (int i = S; i < tree.length; i++) {
            if(n >= N) tree[i] = 0;
            else tree[i] = nums[n++];
        }
        for (int i = S-1; i >= 1; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }
    static long query(int left, int right, int node, int queryLeft, int queryRight) {
        // 연관 없음 return 0;
        if(left > queryRight || right < queryLeft) return 0;
        // 연관 있음
        else if(queryLeft<= left && right <= queryRight) {
            return tree[node];
        }
        // 판단 불가
        int mid = (left + right) / 2;
        return query(left, mid, 2 * node, queryLeft, queryRight) + query(mid + 1, right, 2 * node + 1, queryLeft, queryRight);
    }

    static void update(int left, int right, int node, int target, long diff) {
        // 연관 없음
        // do nothing

        // 연관 있음
        if(target >= left && target <= right) {
            tree[node] += diff;
            if (2 * node < tree.length) {
                int mid = (left + right) / 2;
                update(left, mid, 2 * node, target, diff);
                update(mid + 1, right, 2 * node + 1, target, diff);
            }
        }
    }
}
