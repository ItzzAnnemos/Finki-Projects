package mk.ukim.finki.ZadaciZaVezbanje.ZigZagSLL;

public class SLLNode<E extends Comparable<E>> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }

    public int compareTo(SLLNode<E> otherNode) {
        return this.element.compareTo(otherNode.element);
    }
}
