import java.io.*;
import java.util.StringTokenizer;

public class boj_3830 {
    static int[] parent;
    static long[] dist; // 루트에서 i 까지의 거
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0) break;
            dist = new long[N + 1];
            parent = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }
            for (int i = 0; i < M; i++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                char type = st2.nextToken().charAt(0);
                int a = Integer.parseInt(st2.nextToken());
                int b = Integer.parseInt(st2.nextToken());
                if (type == '!') { // ! a b w : a가 b보다 w만큼 무겁다
                    int w = Integer.parseInt(st2.nextToken());
                    union(a, b, w);
                } else if (type == '?') { // ? a b : b가 a보다 얼마나 무겁냐?
                    if(find(a) != find(b)) {
                        sb.append("UNKNOWN\n");
                    }
                    else sb.append(dist[b] - dist[a]).append("\n");
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    static int find(int a) {
        if(parent[a] == a) return a;
        // 2. root가 아닌 경우 root와의 거리를 구해서 갱신
        int pa = find(parent[a]);
        dist[a] += dist[parent[a]];
        return parent[a] = pa;
    }

    static void union (int a, int b, long diff) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return;

        dist[pb] = dist[a] - dist[b] + diff;
        parent[pb] = pa;
    }

}
