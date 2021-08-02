package SDS;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_4963 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            String s = br.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            if(w == 0 && h == 0) break;
            Coord[][] map = new Coord[h][w];
            Queue<Coord> q = new LinkedList<>();
            for (int i = 0; i < h; i++) {
                s = br.readLine();
                StringTokenizer st2 = new StringTokenizer(s);
                for (int j = 0; j < w; j++) {
                    int a = Integer.parseInt(st2.nextToken());
                    map[i][j] = new Coord(i, j, a);
                }
            }

            constructGraph(map, w, h, q);
            int lands = 0;
            Coord next;
            while(q.size() > 0) {
                next = q.remove();
                if (next.visited) continue;
                DFS(next);
                lands++;
            }
            bw.write(Integer.toString(lands) + "\n");
        }
        bw.flush();
        bw.close();
    }
    public static void DFS(Coord coord) {
        if (coord.visited) return;
        coord.visited = true;
        for (Coord edge : coord.edges) {
            DFS(edge);
        }
    }
    public static void constructGraph(Coord[][] map, int w, int h, Queue<Coord> q) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if(map[i][j].isLand) {
                    link(map, i, j);
                    q.add(map[i][j]);
                }
            }
        }
    }
    public static void link(Coord[][] map, int row, int col) {
        Coord cur = map[row][col];
        if(map.length == 1 && map[0].length == 1) return;
        if(row == 0) {
            if (col == 0) {
                if(map.length == 1) {
                    checkAndEdge(map, cur, row, col + 1);// right
                    return;
                }
                checkAndEdge(map, cur, row, col + 1);// right
                checkAndEdge(map, cur, row + 1, col);// down
                checkAndEdge(map, cur, row + 1, col + 1);// right-down
            } else if (col == map[row].length - 1) {
                if(map.length == 1) {
                    checkAndEdge(map, cur, row, col - 1);// left
                    return;
                }
                checkAndEdge(map, cur, row, col - 1);// left
                checkAndEdge(map, cur, row + 1, col);// down
                checkAndEdge(map, cur, row + 1, col - 1);// left-down
            } else {
                if(map.length == 1) {
                    checkAndEdge(map, cur, row, col - 1);// left
                    checkAndEdge(map, cur, row, col + 1);// right
                    return;
                }
                checkAndEdge(map, cur, row, col - 1);// left
                checkAndEdge(map, cur, row, col + 1);// right
                checkAndEdge(map, cur, row + 1, col);// down
                checkAndEdge(map, cur, row + 1, col - 1);// left-down
                checkAndEdge(map, cur, row + 1, col + 1);// right-down
            }
        } else if (row == map.length - 1) {
            if (col == 0) {
                checkAndEdge(map, cur, row, col + 1);// right
                checkAndEdge(map, cur, row - 1, col);// up
                checkAndEdge(map, cur, row - 1, col + 1);// right-up
            } else if (col == map[row].length - 1) {
                checkAndEdge(map, cur, row, col - 1);// left
                checkAndEdge(map, cur, row - 1, col);// up
                checkAndEdge(map, cur, row - 1, col - 1);// left-up
            } else {
                checkAndEdge(map, cur, row, col - 1);// left
                checkAndEdge(map, cur, row - 1, col);// up
                checkAndEdge(map, cur, row, col + 1);// right
                checkAndEdge(map, cur, row - 1, col - 1);// left-up
                checkAndEdge(map, cur, row - 1, col + 1);// right-up
            }
        } else {
            if (col == 0) {
                checkAndEdge(map, cur, row, col + 1);// right
                checkAndEdge(map, cur, row - 1, col);// up
                checkAndEdge(map, cur, row + 1, col);// down
                checkAndEdge(map, cur, row - 1, col + 1);// right-up
                checkAndEdge(map, cur, row + 1, col + 1);// right-down
            } else if (col == map[row].length - 1) {
                checkAndEdge(map, cur, row, col - 1);// left
                checkAndEdge(map, cur, row - 1, col);// up
                checkAndEdge(map, cur, row + 1, col);// down
                checkAndEdge(map, cur, row - 1, col - 1);// left-up
                checkAndEdge(map, cur, row + 1, col - 1);// left-down
            } else {
                checkAndEdge(map, cur, row, col - 1);// left
                checkAndEdge(map, cur, row - 1, col);// up
                checkAndEdge(map, cur, row, col + 1);// right
                checkAndEdge(map, cur, row + 1, col);// down
                checkAndEdge(map, cur, row - 1, col - 1);// left-up
                checkAndEdge(map, cur, row - 1, col + 1);// right-up
                checkAndEdge(map, cur, row + 1, col + 1);// right-down
                checkAndEdge(map, cur, row + 1, col - 1);// left-down
            }
        }
    }
    public static void checkAndEdge(Coord[][] map, Coord cur, int row, int col) {
        if(map[row][col].isLand) {
            if(!cur.edges.contains(map[row][col])) {
                cur.edges.add(map[row][col]);
            }
            if (!map[row][col].edges.contains(cur)) {
                map[row][col].edges.add(cur);
            }
        }
    }
}


class Coord {
    int row;
    int col;
    boolean isLand;
    boolean visited;
    ArrayList<Coord> edges;

    Coord(int row, int col, int input) {
        this.row = row;
        this.col = col;
        this.isLand = input == 1;
        this.edges = new ArrayList<>();
        this.visited = false;
    }
}