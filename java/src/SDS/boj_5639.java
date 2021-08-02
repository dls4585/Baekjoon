package SDS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class boj_5639 {
    static List<Integer> nums = new ArrayList<>();
    static int size, index = 0, S = 1;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            try {
                nums.add(Integer.parseInt(br.readLine()));
            } catch(Exception e) {
                break;
            }
        }
        size = nums.size();

        TN root = new TN(nums.get(0));
        for (int i = 1; i < nums.size(); i++) {
            insert(root, nums.get(i));
        }
        postOrder(root, bw);
        bw.flush();
        bw.close();
    }
    public static void insert(TN node, int value) {
        if(value < node.number) {
            if(node.left != null) {
                insert(node.left, value);
            }
            else {
                node.left = new TN(value);
            }
        }
        else {
            if(node.right != null) {
                insert(node.right, value);
            }
            else {
                node.right = new TN(value);
            }
        }
    }

    public static void postOrder(TN node, BufferedWriter bw) throws IOException {
        if(node == null) return;
        postOrder(node.left, bw);
        postOrder(node.right, bw);
        bw.write(node.number + "\n");
    }
}
class TN {
    int number;
    TN left;
    TN right;
    TN (int number) {
        this.number = number;
        this.left = null;
        this.right = null;
    }
}
