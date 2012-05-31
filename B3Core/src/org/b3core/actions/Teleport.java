package org.b3core.actions;

import org.b3core.actors.Actor;
import org.b3core.fundamentals.Point;

/**
 * copyright Tiest Vilee 2012
 * Date: 31/05/2012
 * Time: 22:52
 */
public class Teleport implements ActorAction {

    private final Point to;

    public Teleport(Point to) {
        this.to = to;
    }

    public Actor actOn(Actor actor) {
        actor.location = to;
        return actor;
    }
}
