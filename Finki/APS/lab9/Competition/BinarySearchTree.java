package mk.ukim.finki.lab9.Competition;

public class BinarySearchTree<E extends Comparable<E>> {
    private BNode<E> root;

    public BinarySearchTree() {
        root = null;
    }

    private int findRank(E x, BNode<E> t) {
        if (t == null) {
            return 0; // Not found
        }

        if (x.compareTo(t.info) > 0) {
            return findRank(x, t.right);
        }

        if (x.compareTo(t.info) == 0) {
            return 1 + findRank(x, t.right);
        }

        return 1 + findRank(x, t.left) + findRank(x, t.right);

    }

    public int findR(E x) {
        return findRank(x, root);
    }

    private BNode<E> insert(E x, BNode<E> t) {
        if (t == null) {
            t = new BNode<E>(x);
        } else if (x.compareTo(t.info) <= 0) {
            t.left = insert(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = insert(x, t.right);
        }

        return t;
    }

    public void insert(E x) {
        root = insert(x, root);
    }

    private BNode<E> findMin(BNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    private BNode<E> findMax(BNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.right == null) {
            return t;
        }
        return findMax(t.right);
    }

    private BNode<E> find(E x, BNode<E> t) {
        if (t == null)
            return null;
        if (x.compareTo(t.info) < 0) {
            return find(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            return find(x, t.right);
        } else {
            return t; // Match
        }
    }

    public BNode<E> find(E x) {
        return find(x, root);
    }
}
