package mk.ukim.finki.lab5.ShakerSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShakerSort {
    static void shakerSort(int a[], int n) {
        int flag = 1, flag2 = 1;
        int start = 0;
        int end = n - 1;

        for (int i = 0; i < n; i++) {
            if (flag2 == 1) {
                flag2 = 0;

                for (int j = end; j > start; j--) {
                    if (a[j] < a[j - 1]) {
                        int tmp = a[j];
                        a[j] = a[j - 1];
                        a[j - 1] = tmp;
                        flag2 = 1;
                    }
                }
                start++;

                printArray(a);

                for (int j = start; j < end; j++) {
                    if (a[j] > a[j + 1]) {
                        int tmp = a[j];
                        a[j] = a[j + 1];
                        a[j + 1] = tmp;
                        flag2 = 1;
                    }
                }
                end--;

                printArray(a);

                if (flag == 1) {
                    flag = 0;
                } else {
                    flag = 1;
                }
            } else {
                break;
            }
        }
    }

    static void printArray(int a[]) {
        for (int l = 0; l < a.length; l++) {
            System.out.print(a[l]);
            if (l != a.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
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
        shakerSort(a, n);
    }
}
