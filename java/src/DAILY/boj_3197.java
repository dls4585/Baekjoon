package DAILY;

import java.io.*;
import java.util.*;

public class boj_3197 {
    static char[][] map;
    static int[][] snowDays;
    static int R, C, dayCount = 0, added = 0, temp = 0;
    static Queue<Pair> q = new LinkedList<>(), q2;
    static Stack<Pair> stack = new Stack<>();
    static Pair start, dest;
    // 상 하 좌 우
    static int[] mx = {0, 0, -1, 1};
    static int[] my = {-1, 1, 0, 0};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        snowDays = new int[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == '.' || map[i][j] == 'L') {
                    Pair p = new Pair(i, j);
                    q.add(p);
                    added++;
                    snowDays[i][j] = 0;
                }
                if(map[i][j] == 'L') {
                    if(start == null) start = new Pair(i, j);
                    else dest = new Pair(i, j);
                    snowDays[i][j] = 0;
                }
            }
        }

        stack.push(start);
        int max = -2;
        while(!q.isEmpty()) {
            for (int k = 0; k < added; k++) {
                Pair current = q.remove();
                // 3. 연결된 곳 순회
                for (int i = 0; i < 4; i++) {
                    int ty = current.i + my[i];
                    int tx = current.j + mx[i];
                    // 4. 갈 수 있는가? (벽이 아니고 X인가?)
                    if(ty >= 0 && ty < R && tx >= 0 && tx < C) {
                        if(map[ty][tx] == 'X' && snowDays[ty][tx] == 0) {
                            snowDays[ty][tx] = snowDays[current.i][current.j] + 1;
                            max = Math.max(snowDays[ty][tx], max);
                            q.add(new Pair(ty, tx));
                            temp++;
                        }
                    }
                }
            }
            added = temp;
            temp = 0;
        }

        int left = 0, right = max, min = Integer.MAX_VALUE;
        while(left <= right){
            int mid = (left + right) / 2;
            boolean ret = bfs(mid);
            if(ret) {
                min = Math.min(mid, min);
                right = min - 1;
            }
            else {
                left = mid + 1;
            }
        }

        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }


    static boolean bfs(int target) {
        visited = new boolean[R][C];
        q2 = new LinkedList<>();
        q2.add(start);
        visited[start.i][start.j] = true;
        while(!q2.isEmpty()) {
            Pair current = q2.remove();
                // 3. 연결된 곳 순회
            for (int i = 0; i < 4; i++) {
                int ty = current.i + my[i];
                int tx = current.j + mx[i];
                // 4. 갈 수 있는가? (벽이 아니고 X인가?)
                if(ty >= 0 && ty < R && tx >= 0 && tx < C) {
                    if(!visited[ty][tx] && snowDays[ty][tx] <= target) {
                        if(ty == dest.i && tx == dest.j) return true;
                        q2.add(new Pair(ty, tx));
                        visited[ty][tx] = true;
                    }
                }
            }
        }
        return false;
    }

}
class Pair {
    int i, j;

    Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
