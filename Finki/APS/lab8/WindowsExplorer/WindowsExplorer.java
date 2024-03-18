package mk.ukim.finki.lab8.WindowsExplorer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WindowsExplorer {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String commands[] = new String[N];

        for (int i = 0; i < N; i++)
            commands[i] = br.readLine();

        br.close();

        SLLTree<String> tree = new SLLTree<String>();
        tree.makeRoot("c:");


        // vasiot kod stoi ovde
        SLLNode<String> temp = tree.root;

        for (int i = 0; i < commands.length; i++) {
            if (commands[i].startsWith("CREATE")) {
                SLLNode<String> newNode = new SLLNode<>(commands[i].split("\\s+")[1]);

                newNode.sibling = temp.firstChild;
                temp.firstChild = newNode;

                sortChildren(temp);

                newNode.parent = temp;
            } else if (commands[i].startsWith("OPEN")) {
                if (commands[i].split("\\s+")[1].equals("programs")) {
                    assert temp != null;
                    temp = tree.findNode(temp.firstChild.sibling.sibling, commands[i].split("\\s+")[1], tree);
                } else {
                    temp = tree.findNode(temp, commands[i].split("\\s+")[1], tree);
                }
            } else if (commands[i].startsWith("DELETE")) {
                tree.remove(tree.findNode(temp, commands[i].split("\\s+")[1], tree));
            } else if (commands[i].startsWith("BACK")) {
                if (!temp.equals(tree.root))
                    temp = temp.parent;
            } else if (commands[i].startsWith("PATH")) {
                List<String> list = new ArrayList<>();
                SLLNode<String> tmp = temp;

                while (tmp != null && !tmp.equals(tree.root)) {
                    list.add(tmp.getElement());
                    tmp = tmp.parent;
                }

                String ret = "c:\\";
                for (int j = list.size() - 1; j >= 0; j--) {
                    ret += list.get(j) + "\\";
                }

                System.out.println(ret);
            } else if (commands[i].startsWith("PRINT")) {
                tree.printTree();
            }
        }

    }

    private static void sortChildren(SLLNode<String> node) {
        List<SLLNode<String>> childrenList = new ArrayList<>();

        SLLNode<String> child = node.firstChild;
        while (child != null) {
            childrenList.add(child);
            child = child.sibling;
        }

        childrenList.sort(Collections.reverseOrder());

        node.firstChild = null;
        for (int i = childrenList.size() - 1; i >= 0; i--) {
            childrenList.get(i).sibling = node.firstChild;
            node.firstChild = childrenList.get(i);
        }
    }
}
