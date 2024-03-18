package mk.ukim.finki.NP.ZadaciZaVezbanje2.BlockContainer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BlockContainer<T extends Comparable<T>> {
    private List<Block<T>> blocks;
    private int num;
    private int capacity;

    public BlockContainer(int n) {
        this.blocks = new ArrayList<>();
        this.num = -1;
        this.capacity = n;
    }

    public void add(T a) {
        if (num == -1 || blocks.get(num).isBlockFull(capacity)) {
            blocks.add(new Block<>(a));
            num++;
        } else
            blocks.get(num).addElement(a);
    }

    public void remove(T a) {
        if (blocks.get(num).removeFromLastBlock(a)) {
            blocks.remove(num--);
        }
    }

    public void sort() {
        List<T> sortedElems = blocks.stream()
                .flatMap((b) -> b.getElems().stream())
                .sorted(Comparable::compareTo)
                .collect(Collectors.toList());

        this.blocks = new ArrayList<>();
        this.num = -1;

        IntStream.range(0, sortedElems.size())
                .forEach((i) -> add(sortedElems.get(i)));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        blocks.forEach((b) -> sb.append("[").append(b).append("]").append(","));
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
