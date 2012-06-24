package org.b3core.actors;

import org.b3core.command.stages.Stage;
import org.b3core.fundamentals.Point;
import org.b3core.fundamentals.Rectangle;

import java.util.List;

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

    public DumbActor(ActorId id, Rectangle location, Point velocity) {
        super(id, location);
        this.velocity = velocity;
    }

    @Override
    public Actor move(Stage stage) {
        Rectangle newLocation = location.offset(velocity);

        List<Actor> actors = stage.whatsAt(newLocation);

        for(Actor actor:actors) {
            if(actor.id.equals(id)) {
                continue;
            }
            if(!location.offset(new Point(velocity.x, 0)).intersects(actor.location)) {
                newLocation = location.offset(new Point(velocity.x, 0));
            } else if(!location.offset(new Point(0, velocity.y)).intersects(actor.location)) {
                newLocation = location.offset(new Point(0, velocity.y));
            } else {
                newLocation = location; // if anything intersects this, can't go there...
            }
        }

        location = newLocation;

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
