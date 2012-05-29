package org.b3core.actors;

import org.b3core.fundamentals.Point;

/**
 * Created with IntelliJ IDEA.
 * User: tiestvilee
 * Date: 05/05/2012
 * Time: 21:09
 * To change this template use File | Settings | File Templates.
 */
public class Actor {
    public final ActorId id;
    public final Point location;

    public Actor(ActorId id, Point location) {
        this.id = id;
        this.location = location;
    }

    public Actor move() {
        return this;
    }

    public Actor moveTo(Point newPoint) {
        return new Actor(id, newPoint);
    }
}
