import java.io.*;
import java.util.*;

public class boj_6416 {
    static Map<Integer, TRND> map = new HashMap<>();
    static boolean endFlag = false, TCFlag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = 1;
        while(!endFlag) { // TEST CASE
            int u = 0, v = 0;
            TCFlag = false;
            map.clear();
            while(true) { // READ LINE UNTIL 0 0
                String s = br.readLine();
                if (s.equals("")) continue;
                if (s.equals("-1 -1")) {
                    endFlag = true;
                    break;
                }
                StringTokenizer st = new StringTokenizer(s);
                while(st.hasMoreTokens()) {
                    u = Integer.parseInt(st.nextToken());
                    v = Integer.parseInt(st.nextToken());
                    if(u == 0 && v == 0) {
                        TCFlag = true;
                        break;
                    }
                    if(!map.containsKey(u)) {
                        map.put(u, new TRND(u));
                    }
                    map.get(u).outAdd(v);
                    if(!map.containsKey(v)) {
                        map.put(v, new TRND(v));
                    }
                    map.get(v).inAdd(u);
                }
                if(TCFlag) break;
            }
            if(endFlag) break;
            bw.write("Case "+t+" is " + " tree.");
            bw.flush();
            bw.close();
        }
    }
}

class TRND {
    int number, inCount, outCount;
    List<Integer> in, out;

    TRND(int number) {
        this.number = number;
        this.inCount = 0;
        this.outCount = 0;
        in = new ArrayList<>();
        out = new ArrayList<>();
    }

    public void inAdd(int target) {
        in.add(target);
        inCount++;
    }
    public void outAdd(int target) {
        out.add(target);
        outCount++;
    }
}
