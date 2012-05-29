package org.b3core.fundamentals;

/**
 * Created with IntelliJ IDEA.
 * User: tiestvilee
 * Date: 07/05/2012
 * Time: 20:47
 * To change this template use File | Settings | File Templates.
 */
public class Point {
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point move(Point by) {
        return new Point(x + by.x, y + by.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
