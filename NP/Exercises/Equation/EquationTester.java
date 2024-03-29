package mk.ukim.finki.NP.ZadaciZaVezbanje.Equation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EquationTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = Integer.parseInt(sc.nextLine());

        if (testCase == 1) { // Testing with Integer, Integer
            List<Equation<Integer, Integer>> equations1 = new ArrayList<>();
            List<Integer> inputs = new ArrayList<>();
            while (sc.hasNext()) {
                inputs.add(Integer.parseInt(sc.nextLine()));
            }

            // TODO: Add an equation where you get the 3rd integer from the inputs list, and the result is the sum of that number and the number 1000.
            equations1.add(new Equation<>(() -> inputs.get(2), i -> i + 1000));

            // TODO: Add an equation where you get the 4th integer from the inputs list, and the result is the maximum of that number and the number 100.
            equations1.add(new Equation<>(() -> inputs.get(3), i -> Math.max(i, 100)));

            EquationProcessor.process(inputs, equations1);

        } else { // Testing with Line, Integer
            List<Equation<Line, Double>> equations2 = new ArrayList<>();
            List<Line> inputs = new ArrayList<>();
            while (sc.hasNext()) {
                inputs.add(Line.createLine(sc.nextLine()));
            }

            //TODO Add an equation where you get the 2nd line, and the result is the value of y in the line equation.
            equations2.add(new Equation<>(() -> inputs.get(1), Line::calculateLine));

            //TODO Add an equation where you get the 1st line, and the result is the sum of all y values for all lines that have a greater y value than that equation.
            equations2.add(new Equation<>(() -> inputs.get(0), line -> {
                double y = line.calculateLine();
                return inputs.stream()
                        .filter(other -> other.calculateLine() > y)
                        .mapToDouble(Line::calculateLine)
                        .sum();
            }));

            EquationProcessor.process(inputs, equations2);
        }
    }
}
