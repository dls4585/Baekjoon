import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1922 {
    static PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
    static int[] parent;
    static int count = 0, cost = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            Edge e = new Edge(a, b, cost);
            edges.add(e);
        }

        while(!edges.isEmpty()) {
            Edge e = edges.remove();
            if(count == N-1) break;

            if(find(e.start) == find(e.dest)) continue;

            union(e.start, e.dest);
            count++;
            cost += e.cost;
        }
        bw.write(cost + "\n");
        bw.flush();
        bw.close();
    }

    static int find(int a) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static void union (int a, int b) {
        int pa = find(a);
        int pb = find(b);

        parent[pb] = pa;
    }

}

class Edge {
    int cost;
    int start,dest;
    Edge (int start, int dest, int cost) {
        this.start = start;
        this.dest = dest;
        this.cost = cost;
    }
}

