package mk.ukim.finki.lab4.ArithmeticExpression;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ArithmeticExpression {
    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {
        if (l == r) {
            return c[l] - '0';
        } else {
            int balance = 0;

            for (int i = r; i >= l; i--) {
                if (c[i] == '(') {
                    balance++;
                } else if (c[i] == ')') {
                    balance--;
                } else if ((c[i] == '+' || c[i] == '-') && balance == 0) {
                    int left = presmetaj(c, l, i - 1);
                    int right = presmetaj(c, i + 1, r);

                    if (c[i] == '+') {
                        return left + right;
                    } else {
                        return left - right;
                    }
                }
            }

            return presmetaj(c, l + 1, r - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length - 1);
        System.out.println(rez);

        br.close();

    }
}
