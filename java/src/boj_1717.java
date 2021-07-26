import java.io.*;
import java.util.StringTokenizer;

public class boj_1717 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) { // 초기화
            parent[i] = i;
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st2.nextToken());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            if(k == 0) {
                union(a, b);
            }
            else {
                int pa = find(a);
                int pb = find(b);

                if(pa == pb) {
                    bw.write("YES\n");
                }
                else {
                    bw.write("NO\n");
                }
            }
        }
        bw.flush();
        bw.close();
    }

    static int find (int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static void union (int a, int b) {
        int pa = find(a);
        int pb = find(b);

        parent[pb] = pa;
    }
}
