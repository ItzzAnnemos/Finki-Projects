package mk.ukim.finki.lab3.CountWordPairs;

import java.util.Arrays;
import java.util.Scanner;

public class CountWordPairs {

    //TODO: implement function
    public static int countWordPairs(String [] words) {
        String first;
        int pairs = 0;
        for (int i = 0;i < words.length;i++) {
            first = words[i];
            for (int j = 1 + i;j < words.length;j++) {
                if (first.charAt(0) == words[j].charAt(0)) {
                    pairs++;
                }
            }
        }
        return pairs;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();

        String words[] = new String[N];

        for(int i=0;i<N;i++) {
            words[i] = input.next();
        }

        System.out.println(countWordPairs(words));

    }
}
