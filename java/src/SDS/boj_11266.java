package SDS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_11266 {
    static int order = 1;
    static int[] visited;
    static List[] adjlist;
    static boolean[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        visited = new int[V + 1];
        adjlist = new List[V + 1];
        ans = new boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            adjlist[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < E; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st2.nextToken());
            int B = Integer.parseInt(st2.nextToken());

            adjlist[A].add(B);
            adjlist[B].add(A);
        }

        for (int i = 1; i <= V; i++) {
            if(visited[i] == 0) {
                dfs(true, i, 0);
            }
        }
        int cnt = 0;
        for (int i = 1; i <= V; i++) {
            if(ans[i]) {
                cnt++;
                sb.append(i).append(" ");
            }
        }
        sb.append("\n");
        bw.write(cnt + "\n" + sb.toString());
        bw.flush();
        bw.close();
    }

    static int dfs(boolean isRoot, int id, int parent) { // 자식들 중 가작 작은 방문 번호를 반환
        // 1. 체크인
        visited[id] = order++;
        // 2. 목적지인가?

        int ret = visited[id];
        int child = 0;
        // 3. 연결된 곳
        int len = adjlist[id].size();
        for (int i = 0; i < len; i++) {
            int next = (int) adjlist[id].get(i);

            if(next == parent) continue;

            // 4. 갈 수 있는가?
            // 자식을 최초 방문했을 경우
            if(visited[next] == 0) {
                child++;

                // 자식들 방문 순서 중 가장 최소값
                // 5. 간다.
                int min = dfs(false, next, id);

                // root 아니고 자식 순서가 나보다 크면 단절점
                if(!isRoot && min >= visited[id]) {
                    ans[id] = true;
                }
                ret = Math.min(min, ret);
            }
            else {
                ret = Math.min(ret, visited[next]);
            }
        }
        if(isRoot && child >= 2) {
            ans[id] = true;
        }
        // 6. 체크아웃
        return ret;
    }
}
