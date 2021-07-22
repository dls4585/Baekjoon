import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class boj_4358 {
    static Map<String, Integer> map = new TreeMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s;
        double size = 0;
        while(true) {
            try {
                s = br.readLine();
                if(map.containsKey(s)) {
                    int count = map.get(s);
                    map.put(s, ++count);
                }
                else {
                    map.put(s, 1);
                }
                size++;
            } catch(Exception e) {
                break;
            }
        }
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            double percent = map.get(key) / size;
            percent *= 100;
            System.out.println(key + " " + String.format("%.4f", percent));
        }

        bw.flush();
        bw.close();
    }
}
