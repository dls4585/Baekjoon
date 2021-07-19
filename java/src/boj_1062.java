import java.io.*;
import java.util.*;

public class boj_1062 {
    static int N, K;
    static boolean[][] word;
    static boolean[] used;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        word = new boolean[51][26];
        // 0-> 'a' 1 -> 'b' ,,, 25 -> 'z'
        // "abc" -> word[0][0] = true, word[0][1] = true, word[0][2] = true, else false
        // i번째 단어의 j번째 문자가 포함?
        used = new boolean[26];
        ans = 0;

        for (int i = 0; i < N; i++) {
            s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                word[i][s.charAt(j) - 'a'] = true;
            }
        }
        if(K < 5) {
            bw.write("0\n");
            bw.flush();
            bw.close();
            return;
        }
        used[0] = true;
        used['n' - 'a'] = true;
        used['t' - 'a'] = true;
        used['c' - 'a'] = true;
        used['i' - 'a'] = true;
        backtrack(0, K - 5);
        bw.write(Integer.toString(ans) + "\n");
        bw.flush();
        bw.close();
    }
    public static void backtrack(int i, int n) { // i : 알파벳, n : 남은 개수
        if (n == 0) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                boolean know = true; // j번째 단어를 알 수 있느냐?
                for (int k = 0; k < 26; k++) {
                    if (word[j][k] && !used[k]) {
                        know = false;
                        break;
                    }
                }
                if(know) count++;
            }
            ans = Math.max(ans, count);
            return;
        }
        for (int j = i; j < 26; j++) {
            if(check(j)) continue;
            used[j] = true;
            backtrack(j + 1, n - 1);
            used[j] = false;
        }
    }
    public static boolean check(int i) {
        return i == 0 || i == 'i' - 'a' || i == 'c' - 'a' || i == 't' - 'a' || i == 'n' - 'a';
    }
}
