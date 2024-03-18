package mk.ukim.finki.NP.lab5.Queue;

public class Node <T> {
    public T element;
    public Node<T> next;

    public Node(T element, Node<T> next) {
        this.element = element;
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
