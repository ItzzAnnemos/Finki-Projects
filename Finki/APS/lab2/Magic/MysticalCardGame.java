package mk.ukim.finki.lab2.Magic;

import java.util.Scanner;

public class MysticalCardGame {
    //TODO: implement function
    public static void startDuel(SLL<Card> firstSorcererCards, SLL<Card> secondSorcererCards) {
        int bestFirst = 0, bestSecond = 0;

        SLLNode<Card> tmp = firstSorcererCards.getFirst();
        SLLNode<Card> tmp2 = secondSorcererCards.getFirst();
        SLLNode<Card> best1 = tmp;
        SLLNode<Card> best2 = tmp2;
        SLLNode<Card> mid1 = tmp;
        SLLNode<Card> mid2 = tmp2;

        for (int i = 0; i < 8; i++) {
            if (tmp.element.significance() > bestFirst) {
                bestFirst = tmp.element.significance();
                best1 = tmp;
            }

            if (tmp2.element.significance() > bestSecond) {
                bestSecond = tmp2.element.significance();
                best2 = tmp2;
            }

            if (i == 3) {
                mid1 = tmp;
                mid2 = tmp2;
            }

            tmp = tmp.succ;
            tmp2 = tmp2.succ;
        }

        firstSorcererCards.delete(best1);
        secondSorcererCards.insertAfter(best1.element, mid2);
        /*secondSorcererCards.delete(best2);
        firstSorcererCards.insertAfter(best2.element, mid1);*/
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SLL<Card> firstSorcererCards = new SLL<Card>();
        SLL<Card> secondSorcererCards = new SLL<Card>();

        for (int i = 0; i < 8; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            firstSorcererCards.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        for (int i = 0; i < 8; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            secondSorcererCards.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        startDuel(firstSorcererCards, secondSorcererCards);
        System.out.println(firstSorcererCards);
        System.out.println(secondSorcererCards);
    }
}
