package mk.ukim.finki.NP.ZadaciZaVezbanje.ShapesApplication2;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShapesApplication {
    public static double MAX_AREA;

    List<Canvas> canvases;

    public static double getMaxArea() {
        return MAX_AREA;
    }

    ShapesApplication(double maxArea) {
        MAX_AREA = maxArea;
    }

    public void sortCanvasesByTotalArea() {
        canvases.sort(Comparator.comparingDouble(Canvas::getCanvasArea).reversed());
    }

    public void readCanvases (InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        canvases = br.lines()
                .map(Canvas::create)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public void printCanvases (OutputStream os) {
        PrintWriter pw =  new PrintWriter(os);

        sortCanvasesByTotalArea();

        for (int i = 0;i < canvases.size();i++) {
            pw.println(canvases.get(i).toString());
        }

        pw.flush();
    }
}
