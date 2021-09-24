package KAKAO.E;

class Solution {
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        Node[] tree = new Node[info.length];
        initTree(info, edges, tree);



        return answer;
    }
    static void traverse(int node, int nextNode, int sheep, int wolves) {

    }
    static void initTree(int[] info, int[][] edges, Node[] tree) {
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];

            if(tree[parent] == null) {
                tree[parent] = new Node(parent, info[parent] == 0);
            }
            if(tree[child] == null) {
                tree[child] = new Node(child, info[child] == 0);
            }

            if(tree[parent].left == -1) {
                tree[parent].left = child;
            } else {
                tree[parent].right = child;
            }
        }

    }
    static class Node {
        int number;
        boolean isSheep;
        boolean visited;
        int left, right;

        public Node(int number, boolean isSheep) {
            this.number = number;
            this.isSheep = isSheep;
            visited = false;
            left = right = -1;
        }
    }
}

public class E {
    public static void main(String[] args) {
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
        Solution s = new Solution();
        s.solution(info, edges);
    }
}
