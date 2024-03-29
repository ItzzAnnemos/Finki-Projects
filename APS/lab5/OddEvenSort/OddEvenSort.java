package mk.ukim.finki.lab5.OddEvenSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class OddEvenSort {
    static void oddEvenSort(int a[], int n) {
        int j = 0, l = 0;
        int countEven = 0, countOdd = 0;

        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                countEven++;
            } else {
                countOdd++;
            }
        }

        int[] odd = new int[countOdd];
        int[] even = new int[countEven];

        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                even[j++] = a[i];
            } else {
                odd[l++] = a[i];
            }
        }

        Arrays.sort(odd);
        Arrays.sort(even);

        j = 0;
        l = countEven - 1;

        for (int i = 0; i < n; i++) {
            if (i < countOdd) {
                a[i] = odd[j++];
            } else {
                a[i] = even[l--];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int i;
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String[] pom = s.split(" ");
        int[] a = new int[n];
        for (i = 0; i < n; i++)
            a[i] = Integer.parseInt(pom[i]);
        oddEvenSort(a, n);
        for (i = 0; i < n - 1; i++)
            System.out.print(a[i] + " ");
        System.out.print(a[i]);
    }
}
