package mk.ukim.finki.NP.lab2.Circles;

public class ObjectCanNotBeMovedException extends Exception {

    public ObjectCanNotBeMovedException(int x, int y) {
        super(String.format("Point (%d,%d) is out of bounds", x,y));
    }
}
