package mk.ukim.finki.lab2.word;

import java.util.Scanner;
import static java.lang.Math.abs;

public class ArrayMeanWordLength {

    //TODO: implement function
    public static String wordClosestToAverageLength(Array<String> arr) {
        int sum = 0;
        double avg = 0.0;
        for (int i = 0;i < arr.getSize();i++) {
            sum += arr.get(i).length();
        }

        avg = (double) sum / arr.getSize();

        double min = 99.9;
        int index = 0;

        for (int i = 0;i < arr.getSize();i++) {

            if (arr.get(i).length() == avg) {
                return arr.get(i).toString();
            }

            if (abs(avg - arr.get(i).length()) < min) {
                min = abs(avg - arr.get(i).length());
                index = i;
            }
        }

        return arr.get(index).toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        Array<String> arr = new Array<>(N);
        input.nextLine();

        for(int i=0;i<N;i++) {
            arr.insertLast(input.nextLine());
        }

        System.out.println(wordClosestToAverageLength(arr));
    }
}
