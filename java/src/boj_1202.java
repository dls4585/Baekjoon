import java.io.*;
import java.util.*;

public class boj_1202 {
    static PriorityQueue<Jewelry> pq = new PriorityQueue<>(Comparator.comparingInt(Jewelry::getValue).reversed());
    // value 기준 내림차순 (new Comparator와 같음)
    static int index = 0;
    static List<Jewelry> jewelries = new ArrayList<>();
    static List<Integer> bags = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int N = Integer.parseInt(st.nextToken()); // 보석 개수
        int K = Integer.parseInt(st.nextToken()); // 가방 개수

        for (int i = 0; i < N; i++) {
            s = br.readLine();
            StringTokenizer st2 = new StringTokenizer(s);
            int weight = Integer.parseInt(st2.nextToken());
            int value = Integer.parseInt(st2.nextToken());
            jewelries.add(new Jewelry(weight, value));
        }
        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }
        jewelries.sort(Comparator.comparingInt(Jewelry::getWeight)); // weight 기준 오름차순 정렬
        Collections.sort(bags);
        long total = 0;
        for (int bag : bags) {
            int i;
            for (i = index; i < jewelries.size(); i++) {
                if (jewelries.get(i).weight <= bag) {
                    pq.add(jewelries.get(i));
                } else break;
            }
            index = i;
            if(!pq.isEmpty()) {
                Jewelry j = pq.remove();
                total += j.value;
            }
        }
        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }
}
class Jewelry {
    int weight;
    int value;

    public int getWeight() {
        return weight;
    }
    public int getValue() {
        return value;
    }
    Jewelry(int weight, int value) {
        this.value = value;
        this.weight = weight;
    }
}
