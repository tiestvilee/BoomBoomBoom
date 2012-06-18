package org.b3core.actions.actor;

import org.b3core.actors.Actor;
import org.b3core.actors.ActorId;

/**
 * User: tiestvilee
 * Date: 07/05/2012
 * Time: 20:17
 */
public abstract class ActorAction {

    protected final ActorId actorId;

    protected ActorAction(ActorId actorId) {
        this.actorId = actorId;
    }

    public ActorId getActorId() {
        return actorId;
    }

    public abstract Actor actOn(Actor actor);
}
