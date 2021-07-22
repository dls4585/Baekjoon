import java.io.*;
import java.util.StringTokenizer;

public class boj_2042_IndexedTree {
    static int N,M, K, S;
    static long[] nums, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new long[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        S = 1;
        while (S < N) {
            S *= 2;
        }
        tree = new long[S * 2]; // initial : l == 1, S == r
        initBU();


        for (int i = 0; i < M + K; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            long c = Long.parseLong(st2.nextToken());

            switch (a) {
                case 1:
                    update(1, S, 1, b, c - tree[S + b - 1]);
                    break;
                case 2:
                    long ret = query(1, S, 1, b, c);
                    bw.write(ret + "\n");
                    break;
            }
        }

        bw.flush();
        bw.close();
    }
    static void initBU() {
        // Leaf에 값 반영
        int n = 0;
        for (int i = S; i < tree.length; i++) {
            if (n >= N) {
                tree[i] = 0;
            }
            else {
                tree[i] = nums[n++];
            }
        }
        // 내부 노드 채움
        for (int i = S-1; i >= 1; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }
    static long query(int left, int right, int node, int queryLeft, long queryRight) { // query(1,S,1,3,7)
        // 연관이 없음 return 0
        if (right < queryLeft || left > queryRight) {
            return 0;
        }
        // 판단 가능 -> 현재 노드 값 return
        else if(queryLeft <= left && queryRight >= right) {
            return tree[node];
        }
        // 판단 불가, 자식에게 위임, 자식에서 올라온 합을 return
        int mid = (left + right) / 2;
        return query(left, mid, 2 * node, queryLeft, queryRight) + query(mid + 1, right, 2 * node + 1, queryLeft, queryRight);
    }
    static void update(int left, int right, int node, int target, long diff) { // update(1,S,1,target, change - tree[S + target - 1])
        // 연관 없음
        // nothing

        // 연관 있음 -> 현재 노드에 diff 반영 -> 자식에게 diff 전달
        if(target >= left && target <= right) {
            tree[node] += diff;
            if(2*node < tree.length) {
                int mid = (left + right) / 2;
                update(left, mid, 2 * node, target, diff);
                update(mid + 1, right, 2 * node + 1, target, diff);
            }
        }
    }
}