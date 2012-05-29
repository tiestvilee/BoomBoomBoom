package org.b3core.entities;

import org.b3core.fundamentals.Direction;

/**
 * Created with IntelliJ IDEA.
 * User: tiestvilee
 * Date: 05/05/2012
 * Time: 20:34
 * To change this template use File | Settings | File Templates.
 */
public class RectangularEntity {
    private final int x;
    private final int y;
    private final Direction direction;

    public RectangularEntity(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}
