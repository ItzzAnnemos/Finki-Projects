package mk.ukim.finki.NP.ZadaciZaVezbanje.ShapesApplication2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Objects;

public class Canvas {
    String id;
    private List<Shapes> shapes;

    private int totalCircles;
    private int totalSquares;
    public static double TMP_AREA = 0;
    private DoubleSummaryStatistics areaStatistics = new DoubleSummaryStatistics();
    private static DecimalFormat D_F = new DecimalFormat("0.00");

    public Canvas(String id, List<Shapes> shapes, int totalCircles, int totalSquares) {
        this.id = id;
        this.shapes = new ArrayList<>();
        this.shapes.addAll(shapes);
        this.totalCircles = totalCircles;
        this.totalSquares = totalSquares;
        TMP_AREA = 0;
    }

    public static Canvas create(String line) {
        String[] tmp = line.split("\\s+");

        List<Shapes> arr = new ArrayList<>();

        int j = 0;
        int tmpCircles = 0, tmpSquares = 0;

        for (int i = 1; i < tmp.length - 1; i += 2) {
            if (Objects.equals(tmp[i], "C")) {
                try {
                    arr.add(new Circle(Integer.parseInt(tmp[i + 1]), tmp[0]));
                    tmpCircles++;
                    TMP_AREA += arr.get(j).getArea();
                } catch (IrregularCanvasException e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            } else if (Objects.equals(tmp[i], "S")) {
                try {
                    arr.add(new Square(Integer.parseInt(tmp[i + 1]), tmp[0]));
                    tmpSquares++;
                    TMP_AREA += arr.get(j).getArea();
                } catch (IrregularCanvasException e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }
        }

        return new Canvas(tmp[0], arr, tmpCircles, tmpSquares);

    }

    public double getCanvasArea() {
        double sum = 0;
        for (int i = 0; i < shapes.size(); i++) {
            double area = shapes.get(i).getArea();
            areaStatistics.accept(area);
            sum += area;
        }

        return sum;
    }

    public int totalShapes() {
        return shapes.size();
    }

    @Override
    public String toString() {
        return id + " " + totalShapes() + " " + totalCircles + " " + totalSquares + " " +
                D_F.format(areaStatistics.getMin()) + " " + D_F.format(areaStatistics.getMax()) +
                " " + D_F.format(areaStatistics.getAverage());
    }
}
