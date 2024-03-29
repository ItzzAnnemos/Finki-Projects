package mk.ukim.finki.NP.lab2.Circles;

public class MovableObjectNotFittableException extends Exception{
    public MovableObjectNotFittableException(Movable m) {
        super(m.toString() + " can not be fitted into the collection");
    }
}
