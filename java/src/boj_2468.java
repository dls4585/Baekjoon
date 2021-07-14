import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2468 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int min = 101, max = -1;
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int N = Integer.parseInt(st.nextToken());

        Point[][] map = new Point[N][N];
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            StringTokenizer st2 = new StringTokenizer(s);
            for (int j = 0; j < N; j++) {
                int a = Integer.parseInt(st2.nextToken());
                if(min > a) min = a;
                if(max < a) max = a;
                map[i][j] = new Point(i, j, a);
            }
        }
        int maxSafeAreas = -1;
        for (int i = min - 1; i <= max; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    map[j][k].visited = false;
                    map[j][k].edges.clear();
                    map[j][k].isSafe = map[j][k].height > i;
                }
            }
            Queue<Point> q = new LinkedList<>();
            constructGraph(map, N, q);
            Point next;
            int safeAreas = 0;
            while (q.size() > 0) {
                next = q.remove();
                if (next.visited) continue;
                DFS(next);
                safeAreas++;
            }
            if (safeAreas > maxSafeAreas) maxSafeAreas = safeAreas;
        }
        bw.write(Integer.toString(maxSafeAreas)+"\n");
        bw.flush();
        bw.close();
    }

    public static void DFS(Point point) {
        if (point.visited) return;
        point.visited = true;
        for (Point edge : point.edges) {
            DFS(edge);
        }
    }
    public static void constructGraph(Point[][] map, int N, Queue<Point> q) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j].isSafe) {
                    link(map, i, j);
                    q.add(map[i][j]);
                }
            }
        }
    }
    public static void link(Point[][] map, int row, int col) {
        Point cur = map[row][col];
        if(row == 0) {
            if (col == 0) {
                checkAndEdge(map, cur, row, col + 1);// right
                checkAndEdge(map, cur, row + 1, col);// down
            } else if (col == map[row].length - 1) {
                checkAndEdge(map, cur, row, col - 1);// left
                checkAndEdge(map, cur, row + 1, col);// down
            } else {
                checkAndEdge(map, cur, row, col - 1);// left
                checkAndEdge(map, cur, row, col + 1);// right
                checkAndEdge(map, cur, row + 1, col);// down
            }
        } else if (row == map.length - 1) {
            if (col == 0) {
                checkAndEdge(map, cur, row, col + 1);// right
                checkAndEdge(map, cur, row - 1, col);// up
            } else if (col == map[row].length - 1) {
                checkAndEdge(map, cur, row, col - 1);// left
                checkAndEdge(map, cur, row - 1, col);// up
            } else {
                checkAndEdge(map, cur, row, col - 1);// left
                checkAndEdge(map, cur, row - 1, col);// up
                checkAndEdge(map, cur, row, col + 1);// right
            }
        } else {
            if (col == 0) {
                checkAndEdge(map, cur, row, col + 1);// right
                checkAndEdge(map, cur, row - 1, col);// up
                checkAndEdge(map, cur, row + 1, col);// down
            } else if (col == map[row].length - 1) {
                checkAndEdge(map, cur, row, col - 1);// left
                checkAndEdge(map, cur, row - 1, col);// up
                checkAndEdge(map, cur, row + 1, col);// down
            } else {
                checkAndEdge(map, cur, row, col - 1);// left
                checkAndEdge(map, cur, row - 1, col);// up
                checkAndEdge(map, cur, row, col + 1);// right
                checkAndEdge(map, cur, row + 1, col);// down
            }
        }
    }
    public static void checkAndEdge(Point[][] map, Point cur, int row, int col) {
        if(map[row][col].isSafe) {
            if(!cur.edges.contains(map[row][col])) {
                cur.edges.add(map[row][col]);
            }
            if (!map[row][col].edges.contains(cur)) {
                map[row][col].edges.add(cur);
            }
        }
    }
}

class Point {
    int row;
    int col;
    int height;
    boolean isSafe;
    boolean visited;
    ArrayList<Point> edges;

    Point(int row, int col, int height) {
        this.row = row;
        this.col = col;
        this.height = height;
        this.isSafe = false;
        this.edges = new ArrayList<>();
        this.visited = false;
    }
}