package SDS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_3176 {
    static int[] depth;
    static int[][] parent, min, max;
    static List[] tree;
    static int K, D, E, ansMin, ansMax;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        depth = new int[N + 1];
        K = 0;
        for (int i = 1; i <= N; i *= 2) {
            K++;
        }
        parent = new int[K][N + 1];
        min = new int[K][N + 1];
        max = new int[K][N + 1];

        tree = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<Tedge>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            tree[A].add(new Tedge(B, C));
            tree[B].add(new Tedge(A, C));
        }

        dfs(1, 1);


        // parent 채우기
        for (int i = 1; i < K; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
                min[i][j] = Math.min(min[i - 1][j], min[i - 1][parent[i - 1][j]]);
                max[i][j] = Math.max(max[i - 1][j], max[i - 1][parent[i - 1][j]]);

            }
        }

        // LCA 진행해서 공통부모 찾기
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            lca(D, E);
            sb.append(ansMin).append(" ").append(ansMax).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int id, int cnt) {
        // 1. 체크인 : 깊이 표시
        depth[id] = cnt;
        // 2. 자식들의 depth 기록
        int len = tree[id].size();
        for (int i = 0; i < len; i++) {
            Tedge next = (Tedge)tree[id].get(i);
            int nextId = next.end;
            if(depth[nextId] == 0) {
                dfs(nextId, cnt + 1);
                parent[0][nextId] = id;
                min[0][nextId] = next.cost; // 자식 -> 부모의 거리
                max[0][nextId] = next.cost;
            }
        }
    }

    static int lca (int a, int b) {
        ansMin = 1000001;
        ansMax = 0;
        // 1. depth[a] >= depth[b] 가 되도록 조정하기
        if(depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // 2. 더 깊은 a를 2^K승 점프하여 depth 맞추기
        for (int i = K-1; i >= 0; i--) {
            if(Math.pow(2, i) <= depth[a]- depth[b]) {// 2^i 보다 depth 차이가 더 크다면 a를 2^i번째 부모로 올린다.
                ansMax = Math.max(max[i][a], ansMax);
                ansMin = Math.min(min[i][a], ansMin);
                a = parent[i][a];
            }
        }

        // 3. a == b 라면 그게 공통 부모
        if(a == b) return a;

        // 4. a - b 는 같은 depth 이므로 2^(K-1) 번째 공통부모부터 공통부모가 아닐 때까지 내린다
        for (int i = K-1; i >= 0; i--) {
            if(parent[i][a] != parent[i][b]) {
                ansMax = Math.max(ansMax, Math.max(max[i][a], max[i][b]));
                ansMin = Math.min(ansMin, Math.min(min[i][a], min[i][b]));
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        ansMax = Math.max(ansMax, Math.max(max[0][a], max[0][b]));
        ansMin = Math.min(ansMin, Math.min(min[0][b], min[0][a]));

        return parent[0][a];
    }
}
class Tedge {
    int cost;
    int end;

    Tedge(int end, int cost) {
        this.end = end;
        this.cost = cost;
    }
}
