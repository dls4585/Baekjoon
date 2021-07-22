import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_15686 {
    static int[][] map;
    static List<House> houses = new ArrayList<>();
    static List<House> chickens = new ArrayList<>();
    static boolean[] visited;
    static int index = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer sst = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(sst.nextToken());
                if(map[i][j] == 1) {
                    houses.add(new House(i, j, 1));
                }
                if(map[i][j] == 2) {
                    chickens.add(new House(i, j, 2));
                }
            }
        }
        visited = new boolean[chickens.size()];
        int realTotalMin = 10000000;
        for (int i = 1; i <= M; i++) {
            index = 0;
            int[][] combinations = new int[getCombination(chickens.size(), i)][i];
            makeCombination(combinations, 0, chickens.size(), i);
            int totalMin = 10000000;
            for (int[] combination : combinations) { // 0,1 이 있을 때
                int totalTime = 0;
                for (House h : houses) {
                    int min = 3 * N;
                    for (int c : combination) {
                        int d = Math.abs(chickens.get(c).i - h.i) + Math.abs(chickens.get(c).j - h.j);
                        min = Math.min(min, d);
                    }
                    totalTime += min;
                }
                totalMin = Math.min(totalTime, totalMin);
            }
            realTotalMin = Math.min(totalMin, realTotalMin);
        }
        bw.write(Integer.toString(realTotalMin) + "\n");
        bw.flush();
        bw.close();
    }
    public static int getCombination(int chickens, int M) {
        if(chickens == M || M == 0) {
            return 1;
        }
        return getCombination(chickens - 1, M - 1) + getCombination(chickens - 1, M);
    }

    public static void makeCombination(int[][] arr, int start, int n, int r) {
        if(r == 0) {
            saveCombination(arr, n);
            return;
        }
        for (int i = start; i < n; i++) {
            visited[i] = true;
            makeCombination(arr, i + 1, n, r - 1);
            visited[i] = false;
        }
    }
    public static void saveCombination(int[][] arr, int n) {
        int r = 0;
        for (int i = 0; i < n; i++) {
            if(visited[i]) {
                arr[index][r++] = i;
            }
        }
        index++;
    }
}
class House {
    int i, j;
    int type;
    int distance;
    int chickenI, chickenJ;
    House(int i, int j, int type) {
        this.i = i;
        this.j = j;
        this.type = type;
    }
}