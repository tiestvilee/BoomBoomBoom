package org.b3core.actions.actor;

import org.b3core.actors.Actor;
import org.b3core.actors.ActorId;

/**
 * copyright Tiest Vilee 2012
 * Date: 29/05/2012
 * Time: 22:39
 */
public class NoAction extends ActorAction {

    public NoAction(ActorId actorId) {
        super(actorId);
    }

    public Actor actOn(Actor actor) {
        return actor;
    }
}
