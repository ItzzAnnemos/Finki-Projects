package mk.ukim.finki.NP.lab2.Circles;

public class MovableCircle implements Movable {
    private int radius;
    private MovablePoint center;
    private TYPE type;

    public MovableCircle(int radius, MovablePoint center) {
        this.radius = radius;
        this.center = center;
        this.type = TYPE.CIRCLE;
    }

    @Override
    public String toString() {
        return "Movable circle with center coordinates (" + center.getCurrentXPosition() + "," + center.getCurrentYPosition() + ") and radius " + radius;
    }

    @Override
    public TYPE getType() {
        return type;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public int getxSpeed() {
        return center.getxSpeed();
    }

    @Override
    public int getySpeed() {
        return center.getySpeed();
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        if (center.getCurrentYPosition() + getySpeed() + radius < MovablesCollection.y_MAX)
            center.moveUp();
        else
            throw new ObjectCanNotBeMovedException(getCurrentXPosition(), getCurrentYPosition() + getySpeed() + radius);
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        if (center.getCurrentXPosition() - getxSpeed() - radius >= 0)
            center.moveLeft();
        else
            throw new ObjectCanNotBeMovedException(getCurrentXPosition() - getxSpeed() - radius, getCurrentYPosition());
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        if (center.getCurrentXPosition() + getxSpeed() + radius < MovablesCollection.x_MAX)
            center.moveRight();
        else
            throw new ObjectCanNotBeMovedException(getCurrentXPosition() + getxSpeed() + radius, getCurrentYPosition());
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        if (center.getCurrentYPosition() - getySpeed() - radius >= 0)
            center.moveDown();
        else
            throw new ObjectCanNotBeMovedException(getCurrentXPosition(), getCurrentYPosition() - getySpeed() - radius);
    }

    @Override
    public int getCurrentXPosition() {
        return center.getCurrentXPosition();
    }

    @Override
    public int getCurrentYPosition() {
        return center.getCurrentYPosition();
    }
}
