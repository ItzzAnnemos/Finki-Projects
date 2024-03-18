package mk.ukim.finki.lab6.PostFixEvaluation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PostFixEvaluation {
    static int evaluatePostfix(char[] izraz, int n) {
        ArrayStack<Integer> stack = new ArrayStack<>(n);
        int first, second, rez;

        char ch;

        int i = 0;
        while (i < izraz.length) {
            ch = izraz[i];

            if (Character.isDigit(ch)) {
                StringBuilder num = new StringBuilder();
                while (i < izraz.length && (Character.isDigit(izraz[i]) || izraz[i] == '.')) {
                    num.append(izraz[i]);
                    i++;
                }

                int tmp = Integer.parseInt(num.toString());
                stack.push(tmp);

            } else if (isOperator(ch)) {
                second = stack.pop();

                first = stack.pop();

                if (ch == '+')
                    rez = first + second;
                else if (ch == '*'){
                    rez = first * second;
                } else if (ch == '-') {
                    rez = first - second;
                } else {
                    rez = first / second;
                }

                stack.push(rez);
                i++;
            } else {
                i++;
            }
        }
        return stack.peek();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = evaluatePostfix(exp, exp.length);
        System.out.println(rez);

        br.close();

    }
}
