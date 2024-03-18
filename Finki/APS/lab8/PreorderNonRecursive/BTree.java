package mk.ukim.finki.lab8.PreorderNonRecursive;

import java.util.ArrayList;
import java.util.List;

public class BTree<E> {
    public BNode<E> root;

    public BTree() {
        root = null;
    }

    public BTree(E info) {
        root = new BNode<E>(info);
    }

    public void makeRoot(E elem) {
        root = new BNode<E>(elem);
    }

    public void makeRootNode(BNode<E> node) {
        root = node;
    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {

        BNode<E> tmp = new BNode<E>(elem);

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }

    public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }

    public void PreorderNonRecursive() {
        // vasiot kod ovde // your code here
        BNode<E> tmp;
        ArrayStack<BNode<E>> stack = new ArrayStack<>(30);
        ArrayStack<E> values = new ArrayStack<>(30);

        stack.push(root);
        int n = 0;

        while (!stack.isEmpty()) {
            tmp = stack.pop();

            values.push(tmp.info);
            n++;

            if (tmp.right != null)
                stack.push(tmp.right);

            if (tmp.left != null)
                stack.push(tmp.left);
        }

        List<E> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(values.pop());
        }

        String ret = "";
        for (int i = list.size() - 1;i >= 0;i--) {
            ret += list.get(i);
            if (i != 0)
                ret += " ";
        }

        System.out.println(ret);
    }
}
