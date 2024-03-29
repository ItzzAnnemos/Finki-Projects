package mk.ukim.finki.NP.lab6.IntegerList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntegerList {
    private List<Integer> list;

    public IntegerList() {
        this.list = new ArrayList<>();
    }

    public IntegerList(Integer... numbers) {
        this.list = new ArrayList<>();
        Collections.addAll(list, numbers);
    }

    public void add(int el, int idx) {
        if (idx > size())
            IntStream.range(size(), idx).forEach(i -> list.add(0));

        list.add(idx, el);
    }

    public int remove(int idx) {
        if (idx > list.size() || idx < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return list.remove(idx);
    }

    public void set(int el, int idx) {
        if (idx > list.size() || idx < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        list.set(idx, el);
    }

    public int get(int idx) {
        if (idx > list.size() || idx < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return list.get(idx);
    }

    public int size() {
        return list.size();
    }

    public int count(int el) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == el)
                count++;
        }

        return count;
    }

    public void removeDuplicates() {
        Collections.reverse(list);
        list = list.stream().distinct().collect(Collectors.toList());
        Collections.reverse(list);
    }

    public int sumFirst(int k) {
        return list.stream()
                .limit(k)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int sumLast(int k) {
        if(k > size()) k = 0;
        return list.subList(size() - k, size()).stream().reduce(Integer::sum).orElse(0);
    }

    public void shiftRight(int idx, int k) {
        int newIndex = (idx + k) % size();
        Integer el = list.remove(idx);
        list.add(newIndex, el);
    }

    public void shiftLeft(int idx, int k) {
        int newIndex = idx - (k % size());
        if (newIndex < 0)
            newIndex += size();
        Integer el = list.remove(idx);
        list.add(newIndex, el);
    }

    public IntegerList addValue(int value) {
        IntegerList result = new IntegerList();
        list.forEach(i -> result.add(i + value, list.indexOf(i)));
        return result;
    }
}
