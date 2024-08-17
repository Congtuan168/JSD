package tut01;

/**
 * this class is going to implement the standing position of the character
 */
public class Point2D {
    private int x;
    private int y;

    //getter and setter

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

    //constructor
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
