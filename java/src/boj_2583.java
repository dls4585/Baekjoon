import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2583 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ND[][] map = new ND[M][N];
        mapInit(map);

        for (int i = 0; i < K; i++) {
            s = br.readLine();
            StringTokenizer st2 = new StringTokenizer(s);
            int LUx = Integer.parseInt(st2.nextToken());
            int LUy = Integer.parseInt(st2.nextToken());
            int RBx = Integer.parseInt(st2.nextToken());
            int RBy = Integer.parseInt(st2.nextToken());

            for (int j = LUy; j < RBy; j++) {
                for (int k = LUx; k < RBx; k++) {
                    map[j][k].drawed = true;
                }
            }
        }
        Queue<ND> q = new LinkedList<>();
        constructGraph(map, M, N, q);

        ND next;
        int numbers = 0;
        int lands = 0;
        Queue<Integer> ans = new LinkedList<>();
        while (q.size() > 0) {
            next = q.remove();
            if (next.visited) continue;
            numbers += DFS(next);
            lands++;
            ans.add(numbers);
            numbers = 0;
        }
        bw.write(Integer.toString(lands) + "\n");
        ans.stream().sorted().forEach(item -> {
            try {
                bw.write(Integer.toString(item) + " ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.write("\n");
        bw.flush();
        bw.close();

    }
    public static int DFS(ND nd) {
        if (nd.visited) return 0;
        int count = 0;
        nd.visited = true;
        count++;
        for (ND edge : nd.edges) {
            count += DFS(edge);
        }
        return count;
    }
    public static void constructGraph(ND[][] map, int M, int N, Queue<ND> q) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(!map[i][j].drawed) {
                    link(map, i, j);
                    q.add(map[i][j]);
                }
            }
        }
    }
    public static void link(ND[][] map, int row, int col) {
        ND cur = map[row][col];
        if(map.length == 1 && map[0].length == 1) return;
        if(row == 0) {
            if (col == 0) {
                if(map.length == 1) {
                    checkAndEdge(map, cur, row, col + 1);// right
                    return;
                }
                if(map[0].length != 1) {
                    checkAndEdge(map, cur, row, col + 1);// right
                }
                checkAndEdge(map, cur, row + 1, col);// down
            } else if (col == map[row].length - 1) {
                if(map.length == 1) {
                    checkAndEdge(map, cur, row, col - 1);// left
                    return;
                }
                if(map[0].length != 1) {
                    checkAndEdge(map, cur, row, col - 1);// left
                }
                checkAndEdge(map, cur, row + 1, col);// down
            } else {
                if(map.length == 1) {
                    checkAndEdge(map, cur, row, col - 1);// left
                    checkAndEdge(map, cur, row, col + 1);// right
                    return;
                }
                if(map[0].length != 1) {
                    checkAndEdge(map, cur, row, col - 1);// left
                    checkAndEdge(map, cur, row, col + 1);// right

                }
                checkAndEdge(map, cur, row + 1, col);// down
            }
        } else if (row == map.length - 1) {
            if (col == 0) {
                if(map[0].length != 1) {
                    checkAndEdge(map, cur, row, col + 1);// right
                }
                checkAndEdge(map, cur, row - 1, col);// up
            } else if (col == map[row].length - 1) {
                if(map[0].length != 1) {
                    checkAndEdge(map, cur, row, col - 1);// left
                }
                checkAndEdge(map, cur, row - 1, col);// up
            } else {
                if(map[0].length != 1) {
                    checkAndEdge(map, cur, row, col - 1);// left
                    checkAndEdge(map, cur, row, col + 1);// right
                }
                checkAndEdge(map, cur, row - 1, col);// up
            }
        } else {
            if (col == 0) {
                if(map[0].length != 1) {
                    checkAndEdge(map, cur, row, col + 1);// right
                    checkAndEdge(map, cur, row + 1, col);// down
                }
                checkAndEdge(map, cur, row - 1, col);// up
            } else if (col == map[row].length - 1) {
                if(map[0].length != 1) {
                    checkAndEdge(map, cur, row, col - 1);// left
                    checkAndEdge(map, cur, row + 1, col);// down
                }
                checkAndEdge(map, cur, row - 1, col);// up
            } else {
                if(map[0].length != 1) {
                    checkAndEdge(map, cur, row, col - 1);// left
                    checkAndEdge(map, cur, row, col + 1);// right
                }
                checkAndEdge(map, cur, row - 1, col);// up
                checkAndEdge(map, cur, row + 1, col);// down
            }
        }
    }
    public static void checkAndEdge(ND[][] map, ND cur, int row, int col) {
        if(!map[row][col].drawed) {
            if(!cur.edges.contains(map[row][col])) {
                cur.edges.add(map[row][col]);
            }
            if (!map[row][col].edges.contains(cur)) {
                map[row][col].edges.add(cur);
            }
        }
    }
    public static void mapInit(ND[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new ND(i, j);
            }
        }
    }
}

class ND {
    int row;
    int col;
    boolean drawed;
    boolean visited;
    ArrayList<ND> edges;

    ND (int row, int col) {
        this.row = row;
        this.col = col;
        this.drawed = false;
        this.visited = false;
        this.edges = new ArrayList<>();
    }

}