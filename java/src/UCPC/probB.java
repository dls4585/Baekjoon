package UCPC;

import java.io.*;
import java.util.*;

public class probB {
    static int[][] beforeMap, afterMap, copyBefore;
    // 상 하 좌 우
    static int[] mx = {0, 0, -1, 1};
    static int[] my = {-1, 1, 0, 0};
    static boolean[][] visited;

    static int N, M;
    static int deleted = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        beforeMap = new int[N][M];
        copyBefore = new int[N][M];
        afterMap = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                copyBefore[i][j] = beforeMap[i][j] = Integer.parseInt(st2.nextToken());
            }
        }
        boolean flag = true;
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                afterMap[i][j] = Integer.parseInt(st2.nextToken());
                if(afterMap[i][j] != beforeMap[i][j]) {
                    flag = false;
                }
            }
        }
        if(flag) {
            bw.write("YES\n");
            bw.flush();
            bw.close();
            return;
        }
        int startI = 0, startJ = 0;
        boolean find = false;
        int target = 0, original = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(afterMap[i][j] != beforeMap[i][j]) {
                    original = beforeMap[i][j];
                    target = afterMap[i][j];
                    startI = i;
                    startJ = j;
                    find = true;
                    break;
                }
            }
            if(find) break;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startI, startJ));

        while(!q.isEmpty()) {
            Node next = q.remove();
            if(visited[next.i][next.j]) continue;
            deleted++;
            beforeMap[next.i][next.j] = target;
            visited[next.i][next.j] = true;
            for (int i = 0; i < 4; i++) {
                int ty = next.i + my[i];
                int tx = next.j + mx[i];
                // 4. 갈 수 있는가? if(맵을 벗어나지 않고, '.' 이거나 'S'거나)
                if (ty >= 0 && ty < N && tx >= 0 && tx < M) {
                    if (!visited[ty][tx]) {
                        if (beforeMap[ty][tx] == original) {
                            q.add(new Node(ty, tx));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(afterMap[i][j] != beforeMap[i][j]) {
                    bw.write("NO\n");
                    bw.flush();
                    bw.close();
                    return;
                }
            }
        }
        bw.write("YES\n");
        bw.flush();
        bw.close();
    }

}
class Node {
    int i, j;
    Node (int i, int j) {
        this.i = i;
        this.j = j;
    }
}