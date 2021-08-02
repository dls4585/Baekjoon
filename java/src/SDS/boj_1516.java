package SDS;

import java.io.*;
import java.util.*;

public class boj_1516 {
    static GNode[] nodes;
    static Queue<GNode> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);

        nodes = new GNode[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new GNode(i);
        }
        for (int i = 1; i <= N; i++) {
            s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            nodes[i].cache = nodes[i].time = Integer.parseInt(st.nextToken());
            while(true) {
                int a = Integer.parseInt(st.nextToken());
                if(a == -1) break;
                nodes[a].out.add(nodes[i]);
                nodes[i].indegree++;
            }
            if(nodes[i].indegree == 0) q.add(nodes[i]);
        }
        while(!q.isEmpty()) {
            GNode next = q.remove();

            for (GNode adj : next.out) {
                adj.indegree--;
                adj.cache = Math.max(adj.cache, next.cache + adj.time);
                if(adj.indegree == 0) q.add(adj);
            }
        }

        for (int i = 1; i <= N; i++) {
            bw.write(nodes[i].cache + "\n");
        }
        bw.flush();
        bw.close();
    }
}

class GNode {
    int number;
    int time;
    int cache;
    int indegree;
    PriorityQueue<GNode> out;

    GNode(int number) {
        this.number = number;
        time = 0;
        cache = 0;
        indegree = 0;
        out = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));
    }
}
