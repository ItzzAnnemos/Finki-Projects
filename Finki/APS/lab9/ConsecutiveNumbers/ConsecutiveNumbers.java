package mk.ukim.finki.lab9.ConsecutiveNumbers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class ConsecutiveNumbers {
    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        @SuppressWarnings("unchecked")
        BNode<Integer> nodes[] = new BNode[N];
        BTree<Integer> tree = new BTree<Integer>();

        for (i = 0; i < N; i++)
            nodes[i] = null;

        for (i = 0; i < N; i++) {
            String line = br.readLine();
            st = new StringTokenizer(line);
            int index = Integer.parseInt(st.nextToken());
            nodes[index] = new BNode<Integer>(Integer.parseInt(st.nextToken()));
            String action = st.nextToken();
            if (action.equals("LEFT")) {
                BNode<Integer> child = tree.addChild(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index].info);
                nodes[index] = child;
            } else if (action.equals("RIGHT")) {
                BNode<Integer> child = tree.addChild(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index].info);
                nodes[index] = child;
            } else {
                // this node is the root
                tree.makeRoot(nodes[index].info);
                nodes[index] = tree.root;
            }
        }

        br.close();

        // vasiot kod ovde
        BNode<Integer> current;
        Stack<BNode<Integer>> stack = new Stack<>();
        List<Integer> arr = new ArrayList<>();

        stack.push(tree.root);

        while (!stack.isEmpty()) {
            current = stack.pop();

            arr.add(current.info);

            if (current.right != null)
                stack.push(current.right);

            if (current.left != null)
                stack.push(current.left);
        }

        for (i = 0; i < arr.size() - 1; i++) {
            if (!(arr.get(i) + 2 == arr.get(i + 1))) {
                System.out.println("false");
                return;
            }

        }

        System.out.println("true");

    }
}
