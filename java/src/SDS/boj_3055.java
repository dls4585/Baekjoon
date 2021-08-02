package SDS;

import java.io.*;
import java.util.*;

public class boj_3055 {
    static boolean findPath;
    // 좌 우 위 아래
    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static int R, C;
    static char[][] map;
    static int[][] dp;
    static Queue<Land> q;
    static boolean findAnswer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(s);
        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());
        map = new char[R][C];
        dp = new int[R][C];
        q = new LinkedList<>();

        Land st = null;
        for (int r = 0; r < R; r++) {
            s = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = s.charAt(c);
                if(map[r][c] == 'S') {
                    st = new Land(r, c, 'S');
                } else if(map[r][c] == '*') {
                    q.add(new Land(r, c, '*'));
                }
            }
        }
        
        q.add(st);

        while (!q.isEmpty()) {
            // 1. 큐에서 꺼내옴
            Land p = q.remove();
            // 2. 목적지인가? if(p == D) ?
            if(p.c == 'D') {
                bw.write(Integer.toString(dp[p.i][p.j]) + "\n");
                findAnswer = true;
                break;
            }
            // 3. 갈 수 있는 곳 순회 for (좌 우 위 아래)
            for (int i = 0; i < 4; i++) {
                int ty = p.i + my[i];
                int tx = p.j + mx[i];
                // 4. 갈 수 있는가? if(맵을 벗어나지 않고, '.' 이거나 'S'거나)
                if(ty >= 0 && ty < R && tx >= 0 && tx < C) {
                    if(p.c == '*') {
                        if(map[ty][tx] == '.' || map[ty][tx] == 'S') {
                            // 5. 체크인
                            map[ty][tx] = '*';
                            // 6. 큐에 넣음 q.add(next);
                            q.add(new Land(ty, tx, '*'));
                        }
                    } else {
                        // 4. 갈 수 있는가? if(맵을 벗어나지 않고, '.' 이거나 'D'거나)
                        if (map[ty][tx] == '.' || map[ty][tx] == 'D') {
                            if(dp[ty][tx] == 0) { // 방문하지 않은 곳
                                // 5. 체크인 dp[r][c] = current dp
                                dp[ty][tx] = dp[p.i][p.j] + 1;
                                // 6. 큐에 넣음 q.add(next);
                                q.add(new Land(ty, tx, map[ty][tx]));
                            }
                        }
                    }
                }

            }
        }

        if(!findAnswer) {
            bw.write("KAKTUS\n");
        }
        bw.flush();
        bw.close();
    }

    // // DFS 스텝
    // 1. 체크인
    // 2. 목적지인가?
    // 3. 연결된 곳 순회
    // 4. 갈 수 있는가?
    // 5. 간다.
    // 6. 체크아웃


}

class Land {
    int i, j;
    char c;

    Land(int i, int j, char c) {
        super();
        this.i = i;
        this.j = j;
        this.c = c;
    }
}

