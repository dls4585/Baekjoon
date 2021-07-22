import java.io.*;
import java.util.StringTokenizer;

public class boj_1991 {
    static TreeNode[] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int N = Integer.parseInt(s);
        trees = new TreeNode[N];
        for (int i = 0; i < N; i++) {
            trees[i] = new TreeNode((char) ('A' + i));
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            if(left != '.') {
                trees[parent - 'A'].left = trees[left - 'A'];
            }
            if(right != '.') {
                trees[parent - 'A'].right = trees[right - 'A'];
            }
        }
        preOrder(trees[0], bw);
        bw.write("\n");
        inOrder(trees[0], bw);
        bw.write("\n");
        postOrder(trees[0], bw);
        bw.write("\n");
        bw.flush();
        bw.close();
    }
    public static void preOrder(TreeNode node, BufferedWriter bw) throws IOException {
        if(node == null) return;
        bw.write(Character.toString(node.num));
        preOrder(node.left, bw);
        preOrder(node.right, bw);
    }
    public static void inOrder(TreeNode node, BufferedWriter bw) throws IOException {
        if(node == null) return;
        inOrder(node.left, bw);
        bw.write(Character.toString(node.num));
        inOrder(node.right, bw);
    }
    public static void postOrder(TreeNode node, BufferedWriter bw) throws IOException {
        if(node == null) return;
        postOrder(node.left, bw);
        postOrder(node.right, bw);
        bw.write(Character.toString(node.num));
    }
}

class TreeNode{
    char num;
    TreeNode left;
    TreeNode right;

    TreeNode(char num) {
        this.num = num;
        this.left = null;
        this.right = null;
    }
}
