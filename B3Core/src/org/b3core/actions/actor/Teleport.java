package org.b3core.actions.actor;

import org.b3core.actors.Actor;
import org.b3core.actors.ActorId;
import org.b3core.fundamentals.Point;

/**
 * copyright Tiest Vilee 2012
 * Date: 31/05/2012
 * Time: 22:52
 */
public class Teleport extends ActorAction {

    private final Point to;

    public Teleport(ActorId actorId, Point to) {
        super(actorId);
        this.to = to;
    }

    public Actor actOn(Actor actor) {
        actor.location = actor.location.moveTo(to);
        return actor;
    }
}
