package mk.ukim.finki.ZadaciZaVezbanje.ExpressionEvaluator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExpressionEvaluator {
    public static int evaluateExpression(String expression){
        String [] strings = expression.split("\\+");
        int product, sum = 0;

        for (int i = 0;i < strings.length;i++) {
            String [] tmp = strings[i].split("\\*");
            product = 1;
            for (int j = 0;j < tmp.length;j++) {
                product *= Integer.parseInt(tmp[j]);
            }
            sum += product;
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }
}
