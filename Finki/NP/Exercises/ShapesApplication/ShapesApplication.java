package mk.ukim.finki.NP.ZadaciZaVezbanje.ShapesApplication;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ShapesApplication {
    List<Canvas> canvases;

    public ShapesApplication() {
    }

    public int readCanvases(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        canvases = br.lines()
                .map(Canvas::create)
                .collect(Collectors.toList());

        int sum = 0;
        for (int i = 0;i < canvases.size();i++) {
            sum += canvases.get(i).getSize();
        }

        return sum;
    }


    public void printLargestCanvasTo(OutputStream outputStream) {
        Canvas tmp = canvases.get(0);
        for (int i = 1;i < canvases.size();i++) {
            if (canvases.get(i).calcPerimeter() > tmp.calcPerimeter()) {
                tmp = canvases.get(i);
            }
        }

        System.out.println(tmp.toString());
    }
}