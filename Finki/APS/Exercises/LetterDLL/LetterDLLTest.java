package mk.ukim.finki.ZadaciZaVezbanje.LetterDLL;

import java.util.Scanner;

public class LetterDLLTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        DLL<String> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(sc.nextLine());
        }

        System.out.println(list.letterOrganize(list));
    }


}
