package mk.ukim.finki.NP.lab5.ResizableArray;

public class ResizableArray<T> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ResizableArray() {
        this.elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void addElement(T element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    private void resize() {
        int newSize = elements.length * 2;
        Object[] newArray = new Object[newSize];
        System.arraycopy(elements, 0, newArray, 0, size);

        this.elements = newArray;
    }

    public boolean removeElement(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(element)) {
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[--size] = null;

                if (size < elements.length / 2) {
                    shrink();
                }

                return true;
            }
        }

        return false;
    }

    private void shrink() {
        int newSize = Math.max(1, elements.length / 2);
        Object[] newArray = new Object[newSize];
        System.arraycopy(elements, 0, newArray, 0, newSize);

        this.elements = newArray;
        this.size = newSize;
    }

    public boolean contains(T element) {
        for (Object o : elements) {
            if (o == null) {
                return false;
            }
            if (o.equals(element)) {
                return true;
            }
        }

        return false;
    }

    public Object[] toArray() {
        return elements;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int count() {
        return size;
    }

    public T elementAt(int idx) {
        if (idx > count() || idx < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return getElement(idx);
    }

    @SuppressWarnings("unchecked")
    private T getElement(int idx) {
        return (T) elements[idx];
    }

    public static <T> void copyAll(ResizableArray<? super T> dest, ResizableArray<? extends T> src) {
        int length1 = src.count();
        int length2 = dest.count();
        int j = 0;

        for (int i = length2;i < length1 + length2;i++) {
            dest.addElement(src.elementAt(j++));
        }
    }
}
