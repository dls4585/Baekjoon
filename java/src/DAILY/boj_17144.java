package DAILY;
import java.io.*;
import java.util.*;

public class boj_17144 {
    static int R, C, T;
    static Coord airCleaner = null; // 공기 청정기의 윗 칸을 저장
    static Queue<Coord> dust;
    static Coord[][] map;
    // 상 하 좌 우
    static int[] mx = {0, 0, -1, 1};
    static int[] my = {-1, 1, 0, 0};

    static class Coord {
        int i, j;
        int dust, plus;
        public Coord(int i, int j) {
            this.i = i;
            this.j = j;
            plus = 0;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        dust = new LinkedList<>();
        map = new Coord[R][C];
        int total = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = new Coord(i, j);
                int value = Integer.parseInt(st.nextToken());
                if(airCleaner == null && value == -1) {
                    airCleaner = map[i][j];
                }
                if(value != 0 && value != -1) {
                    map[i][j].dust = value;
                    dust.add(map[i][j]);
                }
            }
        }

        for (int t = 0; t < T; t++) {
            while (!dust.isEmpty()) {
                Coord now = dust.remove();
                int spread = now.dust / 5;
                if (spread == 0) continue;
                int count = 0;
                for (int i = 0; i < 4; i++) {
                    int ty = now.i + my[i];
                    int tx = now.j + mx[i];
                    if (ty >= 0 && ty < R && tx >= 0 && tx < C) {
                        if ((ty != airCleaner.i || tx != airCleaner.j) && (ty != airCleaner.i + 1 || tx != airCleaner.j)) {
                            map[ty][tx].plus += spread;
                            count++;
                        }
                    }
                }
                now.dust -= (spread * count);
            }

            // 공기청정기 위로
            cleanTop();
            // 아래로
            cleanBottom();
            total = plusDust();
        }
        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }
    static int plusDust() {
        int total = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(i == airCleaner.i && j == airCleaner.j) continue;
                if (i == airCleaner.i + 1 && j == airCleaner.j) continue;
                map[i][j].dust += map[i][j].plus;
                map[i][j].plus = 0;
                if(map[i][j].dust != 0) {
                    dust.add(map[i][j]);
                    total += map[i][j].dust;
                }
            }
        }
        return total;
    }
    static void cleanTop() {
        int ty = airCleaner.i;
        int tx = airCleaner.j + 1;

        int tempDust = map[ty][tx].dust;
        int tempPlus = map[ty][tx].plus;
        map[ty][tx].dust = map[ty][tx].plus = 0;
        tx++;
        while(tx < C) {
            int td = map[ty][tx].dust;
            int tp = map[ty][tx].plus;
            map[ty][tx].dust = tempDust;
            map[ty][tx].plus = tempPlus;
            tempDust = td;
            tempPlus = tp;
            tx++;
        }
        tx--;
        ty--;
        while(ty >= 0) {
            int td = map[ty][tx].dust;
            int tp = map[ty][tx].plus;
            map[ty][tx].dust = tempDust;
            map[ty][tx].plus = tempPlus;
            tempDust = td;
            tempPlus = tp;
            ty--;
        }
        ty++;
        tx--;
        while(tx >= 0) {
            int td = map[ty][tx].dust;
            int tp = map[ty][tx].plus;
            map[ty][tx].dust = tempDust;
            map[ty][tx].plus = tempPlus;
            tempDust = td;
            tempPlus = tp;
            tx--;
        }
        tx++;
        ty++;
        while(ty != airCleaner.i) {
            int td = map[ty][tx].dust;
            int tp = map[ty][tx].plus;
            map[ty][tx].dust = tempDust;
            map[ty][tx].plus = tempPlus;
            tempDust = td;
            tempPlus = tp;
            ty++;
        }
    }
    static void cleanBottom() {
        int ty = airCleaner.i + 1;
        int tx = airCleaner.j + 1;
        int tempDust = map[ty][tx].dust;
        int tempPlus = map[ty][tx].plus;
        map[ty][tx].dust = map[ty][tx].plus = 0;
        tx++;
        while(tx < C) {
            int td = map[ty][tx].dust;
            int tp = map[ty][tx].plus;
            map[ty][tx].dust = tempDust;
            map[ty][tx].plus = tempPlus;
            tempDust = td;
            tempPlus = tp;
            tx++;
        }
        tx--;
        ty++;
        while(ty < R) {
            int td = map[ty][tx].dust;
            int tp = map[ty][tx].plus;
            map[ty][tx].dust = tempDust;
            map[ty][tx].plus = tempPlus;
            tempDust = td;
            tempPlus = tp;
            ty++;
        }
        ty--;
        tx--;
        while(tx >= 0) {
            int td = map[ty][tx].dust;
            int tp = map[ty][tx].plus;
            map[ty][tx].dust = tempDust;
            map[ty][tx].plus = tempPlus;
            tempDust = td;
            tempPlus = tp;
            tx--;
        }
        tx++;
        ty--;
        while (ty != airCleaner.i + 1) {
            int td = map[ty][tx].dust;
            int tp = map[ty][tx].plus;
            map[ty][tx].dust = tempDust;
            map[ty][tx].plus = tempPlus;
            tempDust = td;
            tempPlus = tp;
            ty--;
        }
    }
}
