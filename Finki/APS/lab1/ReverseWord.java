package mk.ukim.finki.lab1;

import java.util.Scanner;

public class ReverseWord {
    public static void printReversed(String word) {
        int n = word.length();
        int j = n;
        char [] newWord = new char[n];
        for (int i = 0;i < n;i++) {
            newWord[--j] = word.charAt(i);
        }
        String reversed = new String(newWord);
        System.out.println(reversed);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        for (int i = 0; i < n; i++) {
            String word = input.next();
            printReversed(word);
        }
    }
}
