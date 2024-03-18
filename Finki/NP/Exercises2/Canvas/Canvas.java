package mk.ukim.finki.NP.ZadaciZaVezbanje2.Canvas;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Canvas {
    private List<Shape> shapes;

    public Canvas() {
        this.shapes = new ArrayList<>();
    }

    public void readShapes(InputStream is) {
        Scanner sc = new Scanner(is);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");
            int shape = Integer.parseInt(parts[0]);
            String id = parts[1];

            if (shape == 1) {
                try {
                    shapes.add(new Circle(id, Double.parseDouble(parts[2])));
                } catch (InvalidIDException e) {
                    System.out.println(e.getMessage());
                } catch (InvalidDimensionException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            } else if (shape == 2) {
                try {
                    shapes.add(new Square(id, Double.parseDouble(parts[2])));
                } catch (InvalidIDException e) {
                    System.out.println(e.getMessage());
                } catch (InvalidDimensionException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            } else {
                try {
                    shapes.add(new Rectangle(id, Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
                } catch (InvalidIDException e) {
                    System.out.println(e.getMessage());
                } catch (InvalidDimensionException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }
    }

    public void scaleShapes(String userID, double coef) {
        shapes.stream()
                .filter(shape -> shape.getId().equals(userID))
                .forEach(shape -> shape.changeDimensions(coef));
    }

    public void printAllShapes(OutputStream os) {
        Comparator<Shape> comparator = Comparator.comparing(Shape::getArea);

        PrintWriter pw = new PrintWriter(os);

        shapes.sort(comparator);

        shapes.forEach(pw::println);

        pw.flush();
    }

    public void printByUserId(OutputStream os) {
        Map<String, List<Shape>> map = new TreeMap<>();
        for (Shape shape : shapes) {
            if (map.containsKey(shape.getId())) {
                map.get(shape.getId()).add(shape);
            } else {
                map.put(shape.getId(), new ArrayList<>());
                map.get(shape.getId()).add(shape);
            }
        }

        PrintWriter pw = new PrintWriter(os);

        map.entrySet().stream()
                .sorted(Comparator.<Map.Entry<String, List<Shape>>, Integer>comparing(e -> e.getValue().size()).reversed()
                        .thenComparing(e -> e.getValue().stream().mapToDouble(Shape::getArea).sum()))
                .forEach(entry -> {
                    String id = entry.getKey();
                    List<Shape> shapesList = entry.getValue();
                    shapesList.sort(Comparator.comparingDouble(Shape::getPerimeter).reversed()
                            .thenComparing(Shape::getId).reversed());

                    pw.println("Shapes of user: " + id);
                    for (Shape shape : shapesList) {
                        pw.println(shape);
                    }
                });

        pw.flush();
    }

    public void statistics (OutputStream os) {
        PrintWriter pw = new PrintWriter(os);

        DoubleSummaryStatistics dss = new DoubleSummaryStatistics();

        for (Shape shape : shapes) {
            dss.accept(shape.getArea());
        }

        pw.printf("count: %d\n", dss.getCount());
        pw.printf("sum: %.2f\n", dss.getSum());
        pw.printf("min: %.2f\n", dss.getMin());
        pw.printf("average: %.2f\n", dss.getAverage());
        pw.printf("max: %.2f\n", dss.getMax());

        pw.flush();
    }
}
