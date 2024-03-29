package mk.ukim.finki.lab1;

import java.util.Scanner;

public class PushZero {
    static void pushZerosToBeginning(int[] arr, int n) {
        int[] array2 = new int[n];
        int l = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                array2[l] = arr[i];
                l++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                array2[l] = arr[i];
                l++;
            }
        }

        System.out.println("Transformiranata niza e:");
        for (int a:array2) {
            System.out.print(a + " ");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = input.nextInt();
        }

        pushZerosToBeginning(array, n);
    }
}
