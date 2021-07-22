import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class boj_9202_retry {
    static List<String> dict = new ArrayList<>();
    static TrieNode root = new TrieNode();
    static boolean[][] visited;
    static StringBuilder sb;
    static char[][] board;
    // 좌 우 위 아래 왼위, 우위, 왼하, 우하
    static int[] mx = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int[] my = {0, 0, -1, 1, -1, -1, 1, 1};

    static int count = 0;
    static int sum = 0;
    static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    static String answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int w = Integer.parseInt(s);
        for (int i = 0; i < w; i++) {
            insert(br.readLine());
        }

        br.readLine();
        int b = Integer.parseInt(br.readLine());
        for (int i = 0; i < b; i++) {
            board = new char[4][4];
            visited = new boolean[4][4];
            answer = "";
            sum = 0;
            count = 0;
            sb = new StringBuilder();

            for (int j = 0; j < 4; j++) {
                s = br.readLine();
                for (int k = 0; k < 4; k++) {
                    board[j][k] = s.charAt(k);
                }
            }
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    if(root.hasChild(board[y][x])) {
                        search(y, x, 1, root.children[board[y][x]]);
                    }
                }
            }

            String answerSb = sum +
                    " " +
                    answer +
                    " " +
                    count +
                    "\n";
            bw.write(answerSb);
            root.clearHit();
            br.readLine();
        }
        bw.flush();
        bw.close();
    }

    static void search(int y, int x, int length, TrieNode node) { // DFS
        // 1. 체크인
        visited[y][x] = true;
        sb.append(board[y][x]);
        // 2. 목적지인가?
        if(node.isEnd && !node.isHit) {
            node.isHit = true;
            sum += score[length];
            count++;
            String foundWord = sb.toString();
            if(compare(answer, foundWord) > 0) {
                answer = foundWord;
            }
        }
        // 3. 연결된 곳 순회
        for (int i = 0; i < 8; i++) {
            int ty = y + my[i];
            int tx = x + mx[i];
            // 4. 가능한가? - map 경계, 방문하지 않았는지, node가 해당 자식을 가지고 있는지
            if(ty >= 0 && tx >= 0 && ty < 4 && tx < 4) {
                if(!visited[ty][tx] && node.hasChild(board[ty][tx])) {
                    // 5. 간다
                    search(ty, tx, length + 1, node.getChild(board[ty][tx]));
                }
            }
        }
        // 6. 체크아웃
        visited[y][x] = false;
        sb.deleteCharAt(length - 1);
    }

    static int compare(String arg0, String arg1) {
        int result = Integer.compare(arg1.length(), arg0.length());
        if (result == 0) {
            return arg0.compareTo(arg1);
        } else {
            return result;
        }
    }

    static void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            int wordIndex = word.charAt(i) - 'A';
            if(current.children[wordIndex] == null) {
                current.children[wordIndex] = new TrieNode();
            }
            current = current.children[wordIndex];
        }
        current.isEnd = true;
    }

    static boolean containsNode(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            int wordIndex = word.charAt(i) - 'A';
            if(current.children[wordIndex] == null) {
                return false;
            }
            current = current.children[wordIndex];
        }
        return true;
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;
    boolean isHit;

    void clearHit(){
        isHit = false;
        for (TrieNode child : children) {
            if (child != null) {
                child.clearHit();
            }
        }
    }

    boolean hasChild(char c) { return children[c-'A'] != null; }

    TrieNode getChild(char c) {return children[c-'A'];}
}
