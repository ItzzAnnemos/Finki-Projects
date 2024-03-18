package mk.ukim.finki.NP.lab2.Circles;

public class MovablePoint implements Movable {
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private TYPE type;

    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.type = TYPE.POINT;
    }

    @Override
    public String toString() {
        return "Movable point with coordinates (" + x + "," + y + ")";
    }

    @Override
    public TYPE getType() {
        return type;
    }

    @Override
    public int getRadius() {
        return 0;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        if (y + ySpeed < MovablesCollection.y_MAX)
            this.y += ySpeed;
        else
            throw new ObjectCanNotBeMovedException(x, y + ySpeed);
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        if (x - xSpeed >= 0)
            this.x -= xSpeed;
        else
            throw new ObjectCanNotBeMovedException(x - xSpeed, y);
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        if (x + xSpeed < MovablesCollection.x_MAX)
            this.x += xSpeed;
        else
            throw new ObjectCanNotBeMovedException(x + xSpeed, y);
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        if (y - ySpeed >= 0)
            this.y -= ySpeed;
        else
            throw new ObjectCanNotBeMovedException(x, y - ySpeed);
    }

    @Override
    public int getCurrentXPosition() {
        return x;
    }

    @Override
    public int getCurrentYPosition() {
        return y;
    }
}
