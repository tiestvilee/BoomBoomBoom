package org.b3core.actions;

import org.b3core.fundamentals.Point;
import org.b3core.actors.Actor;
import org.b3core.actors.DumbActor;

/**
 * Created with IntelliJ IDEA.
 * User: tiestvilee
 * Date: 07/05/2012
 * Time: 20:15
 * To change this template use File | Settings | File Templates.
 */
public class ChangeVelocity implements ActorAction {

    public final Point newVelocity;

    public ChangeVelocity(Point newVelocity) {
        this.newVelocity = newVelocity;
    }

    public Actor actOn(Actor actor) {
        DumbActor dummie = (DumbActor) actor;

        return dummie.changeVelocity(newVelocity);
    }
}
