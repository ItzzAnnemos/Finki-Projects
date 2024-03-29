package mk.ukim.finki.NP.lab2.Circles;

import java.util.Arrays;

public class MovablesCollection {
    private Movable[] movables;
    private int size;
    public static int x_MAX = 0;
    public static int y_MAX = 0;

    public MovablesCollection(int x_MAX, int y_MAX) {
        MovablesCollection.x_MAX = x_MAX;
        MovablesCollection.y_MAX = y_MAX;
        this.movables = new Movable[0];
        this.size = 0;
    }

    private void resize() {
        int newSize = size * 2;
        Movable[] newArr = new Movable[newSize];

        if (size >= 0) System.arraycopy(movables, 0, newArr, 0, size);

        movables = newArr;
        size = newSize;
    }

    public static void setxMax(int x_MAX) {
        MovablesCollection.x_MAX = x_MAX;
    }


    public static void setyMax(int y_MAX) {
        MovablesCollection.y_MAX = y_MAX;
    }

    public void addMovableObject(Movable m) throws MovableObjectNotFittableException {
        int x = m.getCurrentXPosition();
        int y = m.getCurrentYPosition();
        int r;

        if (canFit(m)) {
            movables = Arrays.copyOf(movables, movables.length + 1);
            movables[movables.length - 1] = m;
        } else {
            throw new MovableObjectNotFittableException(m);
        }

    }

    private boolean canFit(Movable m) {
        int x = m.getCurrentXPosition();
        int y = m.getCurrentYPosition();
        int r = 0;

        if (x < 0 || x > MovablesCollection.x_MAX || y < 0 || y > MovablesCollection.y_MAX)
            return false;
        if (m.getType() == TYPE.CIRCLE)
            r = ((MovableCircle) m).getRadius();
        if (x - r < 0 || x + r > MovablesCollection.x_MAX || y - r < 0 || y + r > MovablesCollection.y_MAX)
            return false;
        return true;
    }

    public void moveObjectsFromTypeWithDirection(TYPE type, DIRECTION direction) throws ObjectCanNotBeMovedException {
        for (int i = 0; i < movables.length; i++) {
            if (type == movables[i].getType()) {
                switch (direction) {
                    case UP:
                        movables[i].moveUp();
                        break;
                    case DOWN:
                        movables[i].moveDown();
                        break;
                    case LEFT:
                        movables[i].moveLeft();
                        break;
                    case RIGHT:
                        movables[i].moveRight();
                        break;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Collection of movable objects with size ");
        sb.append(movables.length);
        sb.append(":\n");
        for (int i = 0; i < movables.length; i++) {
            sb.append(movables[i].toString());
            sb.append('\n');
        }

        return sb.toString();
    }
}
