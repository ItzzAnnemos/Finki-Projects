package mk.ukim.finki.ZadaciZaVezbanje.TarotCards;

import java.util.Scanner;

public class TarotCards {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        SLL<Card> firstSet = new SLL<>();
        SLL<Card> secondSet = new SLL<>();

        for (int i = 0; i < 12; i++) {
            firstSet.insertLast(new Card(sc.nextInt(), sc.nextInt()));
        }

        for (int i = 0; i < 12; i++) {
            secondSet.insertLast(new Card(sc.nextInt(), sc.nextInt()));
        }

        System.out.println(firstSet);
        System.out.println(secondSet);
        System.out.println();

        tarotCards(firstSet, secondSet);

    }

    public static void tarotCards(SLL<Card> firstPart, SLL<Card> secondPart) {
        //1
        SLLNode<Card> tmp = firstPart.getFirst();
        firstPart.deleteFirst();
        secondPart.insertLast(tmp.element);

        //2
        tmp = secondPart.getFirst();
        secondPart.deleteFirst();
        firstPart.insertLast(tmp.element);

        //3
        tmp = firstPart.getFirst();
        SLLNode<Card> secondLast = null;
        int count = 1;
        while (tmp != null) {
            if (count == firstPart.length() - 2) {
                secondLast = tmp;
                firstPart.delete(tmp);
            }
            count++;
            tmp = tmp.succ;
        }

        count = 1;
        tmp = secondPart.getFirst();
        while (tmp != null) {
            if (count == secondPart.length() / 2) {
                if (secondLast != null) {
                    secondPart.insertAfter(secondLast.element, tmp);
                }
            }
            count++;
            tmp = tmp.succ;
        }

        System.out.println(firstPart);
        System.out.println(secondPart);
    }
}
