import java.io.*;
import java.util.*;

public class boj_2252 {
    static HNode[] nodes;
    static Queue<HNode> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nodes = new HNode[N + 1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new HNode(i);
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            nodes[b].indegree++;
            nodes[a].out.add(nodes[b]);
        }

        for (int i = 1; i <= N; i++) {
            if(nodes[i].indegree == 0) q.add(nodes[i]);
        }

        while(!q.isEmpty()) {
            HNode next = q.remove();
            for (HNode adj : next.out) {
                adj.indegree--;
                if(adj.indegree == 0) q.add(adj);
            }
            bw.write(next.number + " ");
        }
        bw.flush();
        bw.close();
    }
}

class HNode {
    int number;
    int indegree;
    List<HNode> out;

    HNode(int number) {
        this.number = number;
        indegree = 0;
        out = new ArrayList<>();
    }
}