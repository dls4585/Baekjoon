package SDS;

import java.io.*;
import java.util.*;

public class boj_2346 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String n = br.readLine();
        int N = Integer.parseInt(n);
        DCL q = new DCL();
        ArrayList<Integer> ans = new ArrayList<>();
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int k = 1;
        while(st.hasMoreTokens()) {
            q.insert(k++, Integer.parseInt(st.nextToken()));
        }

        DCNode ret = q.front();
        ans.add(ret.key);
        q.delete(ret);
        int index = ret.number;
        while(q.size > 0) {
            DCNode iter;
            if(index < 0) {
                iter = ret.prev;
                for (int i = index + 1; i < 0; i++) {
                    iter = iter.prev;
                }
            }
            else {
                iter = ret.next;
                for (int i = index - 1; i > 0; i--) {
                    iter = iter.next;
                }
            }
            ans.add(iter.key);
            q.delete(iter);
            ret = iter;
            index = iter.number;
        }

        ans.forEach(a -> {
            try {
                bw.write(Integer.toString(a));
                if (!a.equals(ans.get(N - 1))) {
                    bw.write(" ");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.write("\n");
        bw.flush();
    }
}

class DCL {
    private DCNode first, last;
    public int size;
    DCL() {
        size = 0;
        first = null;
        last = null;
    }

    public DCNode front() {
        return first;
    }

    public void insert(int k, int number) {
        DCNode node = new DCNode(k, number);
        if(size == 0) {
            first = node;
            last = node;
            node.prev = node;
            node.next = node;
        }
        else {
            last.next = node;
            node.prev = last;
            node.next = first;
            first.prev = node;
            last = node;
        }
        size++;
    }

    public void delete(DCNode node) {
        size--;
        if(size == 0) {
            first = last = null;
        }
        else {
            if (node == first) {
                first = node.next;
            }
            else if (node == last) {
                last = node.next;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;

        }
    }
}
class DCNode {
    public int key;
    public int number;
    public DCNode prev, next;

    DCNode(){}

    DCNode(int key, int number) {
        this.key = key;
        this.number = number;
        this.prev = this.next = null;
    }
}