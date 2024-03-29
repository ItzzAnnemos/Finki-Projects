package mk.ukim.finki.NP.ZadaciZaVezbanje2.BlockContainer;

import java.util.*;

public class Block<T extends Comparable<T>> {
    private Set<T> elems;

    public Block(T elem) {
        this.elems = new TreeSet<>();
        this.elems.add(elem);
    }

    public void addElement(T elem) {
        elems.add(elem);
    }

    public boolean removeFromLastBlock(T a) {
        elems.remove(a);
        return elems.isEmpty();
    }

    public boolean isBlockFull(int size) {
        return elems.size() == size;
    }

    public Set<T> getElems() {
        return elems;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        elems.forEach((e) -> sb.append(e.toString()).append(", "));
        sb.setLength(sb.length()-2);
        return sb.toString();
    }
}
