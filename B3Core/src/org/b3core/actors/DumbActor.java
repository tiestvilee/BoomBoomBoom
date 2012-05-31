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

    public Point velocity;

    public DumbActor() {
        // for actor cache
    }

    public DumbActor(ActorId id, Point location, Point velocity) {
        super(id, location);
        this.velocity = velocity;
    }

    @Override
    public Actor move() {
        location = location.move(velocity);
        return this;
    }

    public Actor changeVelocity(Point newVelocity) {
        velocity = newVelocity;
        return this;
    }

    public void copyOnto(Actor newActor) {
        super.copyOnto(newActor);
        ((DumbActor) newActor).velocity = velocity;
    }
}
