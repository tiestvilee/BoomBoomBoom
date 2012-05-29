package org.b3core.actors;

import org.b3core.fundamentals.Point;

/**
 * Created with IntelliJ IDEA.
 * User: tiestvilee
 * Date: 05/05/2012
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class DumbActor extends Actor {

    public final Point velocity;

    public DumbActor(ActorId id, Point location, Point velocity) {
        super(id, location);
        this.velocity = velocity;
    }

    @Override
    public Actor move() {
        return new DumbActor(id, location.move(velocity), velocity);
    }

    public Actor changeVelocity(Point newVelocity) {
        return new DumbActor(id, location, newVelocity);
    }
}
