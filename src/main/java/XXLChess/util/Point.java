package XXLChess.util;
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /*
    The method first checks if the object being compared is the same as this object, if so, returns true.
    If the compared object is null or not an instance of the same class as the current object, then they are not equal.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Point point = (Point) other;
        return x == point.x && y == point.y;
    }

}
