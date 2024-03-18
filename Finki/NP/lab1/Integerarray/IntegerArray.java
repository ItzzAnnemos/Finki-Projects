package mk.ukim.finki.NP.lab1.Integerarray;

import java.util.Arrays;

public class IntegerArray {
    private int[] arr;

    public IntegerArray(int[] a) {
        this.arr = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            this.arr[i] = a[i];
        }
    }

    public int length() {
        return arr.length;
    }

    public int getElementAt(int i) {
        return arr[i];
    }

    public int sum() {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public double average() {
        return (double) sum() / arr.length;
    }

    public IntegerArray getSorted() {
        int[] sortedArr = Arrays.stream(arr).sorted().toArray();

        return new IntegerArray(sortedArr);
    }

    public IntegerArray concat(IntegerArray ia) {
        int[] newArr = new int[arr.length + ia.length()];
        int j = 0;

        for (int i = 0; i < arr.length + ia.length(); i++) {
            if (i < arr.length) {
                newArr[i] = arr[i];
            }
            if (i >= arr.length) {
                newArr[i] = ia.getElementAt(j);
                j++;
            }
        }

        return new IntegerArray(newArr);
    }

    public String toString() {
        String ret = new String();
        ret = "[";
        ret += arr[0];
        for (int i = 1; i < arr.length; i++) {
            ret += ", " + arr[i];
        }
        ret += "]";
        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if(((IntegerArray)o).length() != this.length())
            return false;
        for(int i=0; i<this.arr.length;i++)
            if(((IntegerArray) o).getElementAt(i) != this.arr[i])
                return false;
        return true;
    }
}
