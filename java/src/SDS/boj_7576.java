package SDS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_7576 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        Pair[][] box = new Pair[N][M];
        int ones = 0, minus = 0;
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            StringTokenizer st2 = new StringTokenizer(s);
            for (int j = 0; j < M; j++) {
                int a = Integer.parseInt(st2.nextToken());
                Pair p = new Pair(i, j, a);
                if (a == 1) {
                    ones++;
                    q.add(p);
                }
                if  (a == -1) {
                    minus++;
                }
                box[i][j] = p;
            }
        }
        if (minus + ones == N * M) {
            bw.write("0\n");
            bw.flush();
            bw.close();
            return;
        }
        if (ones == 0) {
            bw.write("-1\n");
            bw.flush();
            bw.close();
            return;
        }
        Pair next;
        int forToday = ones;
        int forNextDay = 0;
        int days = 0;
        int a = 0;
        while (true) {
            next = q.remove();
            forNextDay += a = visitAdjacent(box, next.row, next.col, q);
            ones += a;
            if(ones == N*M - minus) {
                days++;
                break;
            }
            forToday--;
            if(forToday == 0) {
                forToday = forNextDay;
                forNextDay = 0;
                days++;
            }
            if(q.size() == 0) break;
        }
        if (ones < N*M - minus) {
            bw.write("-1\n");
        } else {
            bw.write(Integer.toString(days) + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static int visitAdjacent(Pair[][] box, int N, int M, Queue<Pair> q) {
        int count = 0;
        if(N == 0) {
            if(M == 0) {
                count += checkAndAdd(box, N, M + 1, q); // right
                count += checkAndAdd(box, N + 1, M, q); // down
            } else if (M == box[N].length - 1) {
                count += checkAndAdd(box, N, M - 1, q); // left
                count += checkAndAdd(box, N + 1, M, q); // down
            } else {
                count += checkAndAdd(box, N, M - 1, q); // left
                count += checkAndAdd(box, N, M + 1, q); // right
                count += checkAndAdd(box, N + 1, M, q); // down
            }
        } else if (N == box.length - 1) {
            if(M == 0) {
                count += checkAndAdd(box, N, M + 1, q); // right
                count += checkAndAdd(box, N - 1, M, q); // up
            } else if (M == box[N].length - 1) {
                count += checkAndAdd(box, N, M - 1, q); // left
                count += checkAndAdd(box, N - 1, M, q); // up
            } else {
                count += checkAndAdd(box, N, M - 1, q); // left
                count += checkAndAdd(box, N, M + 1, q); // right
                count += checkAndAdd(box, N - 1, M, q); // up
            }
        } else {
            if(M == 0) {
                count += checkAndAdd(box, N, M + 1, q); // right
                count += checkAndAdd(box, N - 1, M, q); // up
                count += checkAndAdd(box, N + 1, M, q); // down
            } else if (M == box[N].length - 1) {
                count += checkAndAdd(box, N, M - 1, q); // left
                count += checkAndAdd(box, N - 1, M, q); // up
                count += checkAndAdd(box, N + 1, M, q); // down
            } else {
                count += checkAndAdd(box, N, M - 1, q); // left
                count += checkAndAdd(box, N, M + 1, q); // right
                count += checkAndAdd(box, N - 1, M, q); // up
                count += checkAndAdd(box, N + 1, M, q); // down
            }
        }
        return count;
    }

    public static int checkAndAdd(Pair[][] box, int N, int M, Queue<Pair> q) {
        if (box[N][M].number == 0) {
            box[N][M].number = 1;
            q.add(box[N][M]);
            return 1;
        }
        return 0;
    }
}

class Pair {
    int row;
    int col;
    int number; // 1 : visited, 0: yet, -1 : no

    Pair (int row, int col, int number) {
        this.row = row;
        this.col = col;
        this.number = number;
    }
}