package mk.ukim.finki.NP.ZadaciZaVezbanje.ShapesApplication2;

import java.text.DecimalFormat;

public class IrregularCanvasException extends Exception{
    private static DecimalFormat D_F = new DecimalFormat("0.00");

    public IrregularCanvasException(String s) {
        super("Canvas " + s + " has a shape with area larger than " + D_F.format(ShapesApplication.MAX_AREA));
    }
}
