import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class boj_1927 {
    static List<Long> arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        arr = new ArrayList<>();
        arr.add(0L);
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            long l = Long.parseLong(s);
            if(l == 0) {
                if(arr.size() == 1) {
                    bw.write("0\n");
                }
                else {
                    long ret = remove();
                    bw.write(Long.toString(ret) + "\n");
                }
            }
            else {
                insert(l);
            }
        }
        bw.flush();
        bw.close();
    }
    public static void heapify(int startIndex) {
        int child = startIndex;
        int parent = startIndex / 2;
        while(child > 1) {
            if(arr.get(child) < arr.get(parent)) {
                long temp = arr.get(parent);
                arr.set(parent, arr.get(child));
                arr.set(child, temp);
                child = parent;
                parent = parent / 2;
            } else break;
        }
    }
    public static void insert(long value) {
        arr.add(value);
        heapify(arr.size() - 1);
    }
    public static long remove() {
        long ret = arr.get(1);
        arr.set(1, arr.get(arr.size() - 1));
        arr.remove(arr.size() - 1);
        int l = 2, r = 3;
        int cur = 1;
        while(true) {
            if(l >= arr.size()) break;
            int next = cur;
            if (arr.get(l) < arr.get(cur)) {
                next = l;
            }
            if (r < arr.size() && arr.get(next) > arr.get(r)) {
                next = r;
            }
            if(next == cur) break;
            long temp = arr.get(cur);
            arr.set(cur, arr.get(next));
            arr.set(next, temp);
            cur = next;
            l = 2 * cur;
            r = 2 * cur + 1;
        }
        return ret;
    }
}
