package SDS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_11438 {
    static int[][] parent;
    static int[] depth;
    static List<ArrayList<Integer>> tree = new ArrayList<>();
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 2^K > N 인 K 찾기
        K = 0;
        for (int i = 1; i <= N; i *= 2) {
            K++;
        }

        // LCA 관련 변수 초기화
        depth = new int[N + 1]; // 노드 i 의 depth
        parent = new int[K][N + 1];

        // tree 변수 초기화
        tree.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        // 2. Depth 확인 (dfs?)
        dfs(1, 1);
//        for (int i = 2; i <= N; i++) {
//            depth[i] = findDepth(i);
//        }

        // 3. parent 채우기
        for (int i = 1; i < K; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }

        // 4. LCA 진행
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(lca(a, b)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int id, int cnt) {
        // 1. 체크인 : depth 를 기록
        depth[id] = cnt;
        // 2. 자식들의 depth를 기록
        int len = tree.get(id).size();
        for (int i = 0; i < len; i++) {
            int next = tree.get(id).get(i);
            // 아직 깊이를 몰라
            if(depth[next] == 0) {
                dfs(next, cnt + 1);
                parent[0][next] = id;
            }
        }
    }

    static int lca(int a, int b) {
        //1 depth >= dpeth[b] 가 되도록 조정하기
        if(depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        } // a 가 무조건 밑에 있게

        // 2. 더 깊은 a를 2^K승 점프하여 depth 맞추기
        for (int i = K-1; i >= 0; i--) {
            if(Math.pow(2, i) <= depth[a] - depth[b]) {
                a = parent[i][a];
            }
        }

        // 3. depth를 맞췄는데 같다면 종료
        if(a == b) return a;

        // 4. a-b는 같은 depth이므로 2^K승 점프하여 공통부모 바로 아래까지 내리기
        for (int i = K-1; i >= 0; i--) {
            if(parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }
        return parent[0][a];
    }

}
