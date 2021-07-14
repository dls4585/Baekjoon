import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_2606 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int T = Integer.parseInt(s);
        Node[] nodes = new Node[T];
        for (int i = 0; i < T; i++) {
            nodes[i] = new Node(i + 1);
        }
        s = br.readLine();
        int E = Integer.parseInt(s);
        for (int i = 0; i < E; i++) {
            s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            nodes[A - 1].addEdge(B);
            nodes[B - 1].addEdge(A);
        }
        int count = DFS(nodes, 1);

        bw.write(Integer.toString(count - 1) + "\n");
        bw.flush();
        bw.close();

    }
    public static int DFS(Node[] nodes, int number) {
        if(nodes[number-1].isInfected) return 0;
        else {
            int count = 0;
            nodes[number-1].isInfected = true;
            count++;
            for (int i = 0; i < nodes[number - 1].edges.size(); i++) {
                count += DFS(nodes, nodes[number-1].edges.get(i));
            }
            return count;
        }
    }
}

class Node {
    int number;
    boolean isInfected;
    ArrayList<Integer> edges;
    Node(int number){
        this.number = number;
        this.isInfected = false;
        this.edges = new ArrayList<>();
    }
    void addEdge(int number) {
        edges.add(number);
    }
}