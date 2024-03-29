package mk.ukim.finki.NP.lab1.Integerarray;

import java.io.InputStream;
import java.util.Scanner;

public class ArrayReader {
    public static IntegerArray readIntegerArray(InputStream input) {
        Scanner in = new Scanner(input);
        int n = in.nextInt();

        int[] newArr = new int[n];

        for (int i = 0; i < n; i++) {
            if (in.hasNextInt())
                newArr[i] = in.nextInt();
        }

        return new IntegerArray(newArr);
    }
}
