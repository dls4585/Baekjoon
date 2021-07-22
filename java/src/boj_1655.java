import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class boj_1655 {
    static PriorityQueue<Integer> minPQ = new PriorityQueue<>();
    static PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            int x = Integer.parseInt(s);
            if (minPQ.size() == maxPQ.size()) {
                maxPQ.add(x);
            } else if (minPQ.size() + 1 == maxPQ.size()) {
                minPQ.add(x);
            }

            if(minPQ.size() > 0 && maxPQ.size() > 0) {
                if(minPQ.peek() < maxPQ.peek()) {
                    int minRem = minPQ.remove();
                    int maxRem = maxPQ.remove();
                    minPQ.add(maxRem);
                    maxPQ.add(minRem);
                }
            }
            bw.write(maxPQ.peek() + "\n");
        }
        bw.flush();
        bw.close();
    }
}
