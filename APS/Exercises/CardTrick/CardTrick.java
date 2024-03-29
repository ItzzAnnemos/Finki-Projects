package mk.ukim.finki.ZadaciZaVezbanje.CardTrick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CardTrick {
    public static int count(int N){
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1;i < 52;i++) {
            queue.add(i);
        }

        int shuffles = 0;

        while (queue.peek() != N) {
            for (int i = 0;i < 7;i++) {
                stack.push(queue.poll());
            }

            for (int i = 0;i < 7;i++) {
                Integer fromStack = stack.pop();
                Integer fromQueue = queue.poll();

                queue.add(fromStack);
                queue.add(fromQueue);
            }
            shuffles++;
        }

        return shuffles;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        System.out.println(count(Integer.parseInt(br.readLine())));
    }
}
