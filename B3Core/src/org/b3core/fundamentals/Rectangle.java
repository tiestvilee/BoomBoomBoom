package org.b3core.fundamentals;

/**
 * copyright Tiest Vilee 2012
 * Date: 24/06/2012
 * Time: 22:19
 */
public class Rectangle {
    public final int x, y, width, height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 32;
        this.height = 32;
    }

    public Rectangle offset(Point offset) {
        return new Rectangle(this.x + offset.x, this.y + offset.y, width, height);
    }

    public Rectangle moveTo(Point point) {
        return new Rectangle(point.x, point.y, width, height);
    }

    public boolean intersects(Rectangle location) {
        int left = x, right = x + width, top = y, bottom = y + height;
        int left2 = location.x, right2 = location.x + location.width, top2 = location.y, bottom2 = location.y + location.height;
        return !(left   >= right2  ||
                 right  <= left2   ||
                 top    >= bottom2 ||
                 bottom <= top2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        if (height != rectangle.height) return false;
        if (width != rectangle.width) return false;
        if (x != rectangle.x) return false;
        if (y != rectangle.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
            "x=" + x +
            ", y=" + y +
            ", width=" + width +
            ", height=" + height +
            '}';
    }
}
