package SDS;

import java.io.*;
import java.util.*;

public class boj_1753 {
    static List[] adjlist;
    static long[] D;
    static PriorityQueue<DSnode> pq = new PriorityQueue<>(Comparator.comparingLong(o->o.cost));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        D = new long[V + 1];
        adjlist = new List[V + 1];
        for (int i = 1; i <= V; i++) {
            adjlist[i] = new ArrayList<DSnode>();
        }
        int K = Integer.parseInt(br.readLine());
        D[K] = 0;
        for (int i = 1; i <= V; i++) {
            if(i == K) continue;
            D[i] = Long.MAX_VALUE;
        }

        for (int i = 0; i < E; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st2.nextToken());
            int v = Integer.parseInt(st2.nextToken());
            long w = Long.parseLong(st2.nextToken());

            adjlist[u].add(new DSnode(v, w));

        }
        pq.add(new DSnode(K, 0));
        while (!pq.isEmpty()) {
            DSnode now = pq.remove();

            if(now.cost > D[now.number]) continue;
            
            int len = adjlist[now.number].size();
            for (int i = 0; i < len; i++) {
                DSnode next = (DSnode) adjlist[now.number].get(i);
                
                if(D[next.number] > now.cost + next.cost) {
                    D[next.number] = now.cost + next.cost;
                    pq.add(new DSnode(next.number, D[next.number]));
                }
            }
        }
        for (int i = 1; i <= V; i++) {
            if(D[i] == Long.MAX_VALUE) sb.append("INF\n");
            else sb.append(D[i]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

class DSnode {
    long cost;
    int number;

    DSnode(int number, long cost) {
        this.cost = cost;
        this.number = number;
    }
}
