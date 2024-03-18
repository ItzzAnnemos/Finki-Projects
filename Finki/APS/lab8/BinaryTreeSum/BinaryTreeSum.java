package mk.ukim.finki.lab8.BinaryTreeSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BinaryTreeSum {
    public static void main(String[] args) throws Exception {
        int i, j, k;
        int index;
        String action;

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        BNode<Integer>[] nodes = new BNode[N];
        BTree<Integer> tree = new BTree<Integer>();

        for (i = 0; i < N; i++)
            nodes[i] = new BNode<Integer>();

        for (i = 0; i < N; i++) {
            line = br.readLine();
            st = new StringTokenizer(line);
            index = Integer.parseInt(st.nextToken());
            nodes[index].info = Integer.parseInt(st.nextToken());
            action = st.nextToken();
            if (action.equals("LEFT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
            } else if (action.equals("RIGHT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
            } else {
                // this node is the root
                tree.makeRootNode(nodes[index]);
            }
        }

        int baranaVrednost = Integer.parseInt(br.readLine());

        br.close();

        // vasiot kod ovde   // your code here
        Stack<BNode<Integer>> stack = new Stack<>();
        stack.push(tree.root);
        BNode<Integer> tmp;
        int sum1 = 0, sum2 = 0;

        while (!stack.empty()) {
            tmp = stack.pop();
            if (tmp.info > baranaVrednost) {
                sum2 += tmp.info;
            }
            if (tmp.info < baranaVrednost) {
                sum1 += tmp.info;
            }

            if (tmp.left != null) {
                stack.push(tmp.left);
            }

            if (tmp.right != null) {
                stack.push(tmp.right);
            }
        }

        System.out.printf("%d %d", sum1, sum2);
    }
}
