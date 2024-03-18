package mk.ukim.finki.NP.lab2.Circles;

public interface Movable {
    public TYPE getType();

    public int getRadius();

    public int getxSpeed();

    public int getySpeed();

    public void moveUp() throws ObjectCanNotBeMovedException;

    public void moveLeft() throws ObjectCanNotBeMovedException;

    public void moveRight() throws ObjectCanNotBeMovedException;

    public void moveDown() throws ObjectCanNotBeMovedException;

    public int getCurrentXPosition();

    public int getCurrentYPosition();
}
