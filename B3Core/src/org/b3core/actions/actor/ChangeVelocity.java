package org.b3core.actions.actor;

import org.b3core.actors.ActorId;
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
public class ChangeVelocity extends ActorAction {

    public final Point newVelocity;

    public ChangeVelocity(ActorId actorId, Point newVelocity) {
        super(actorId);
        this.newVelocity = newVelocity;
    }

    public Actor actOn(Actor actor) {
        DumbActor dummie = (DumbActor) actor;

        return dummie.changeVelocity(newVelocity);
    }

    @Override
    public String toString() {
        return String.format("ChangeVelocity on: %s to: %s", actorId, newVelocity);
    }
}
