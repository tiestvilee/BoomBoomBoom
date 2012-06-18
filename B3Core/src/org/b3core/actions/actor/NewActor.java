package org.b3core.actions.actor;

import org.b3core.actors.Actor;

/**
 * copyright Tiest Vilee 2012
 * Date: 15/06/2012
 * Time: 23:22
 */
public class NewActor extends ActorAction {

    public final Actor actor;

    public NewActor(Actor actor) {
        super(actor.id);
        this.actor = actor;
    }

    @Override
    public Actor actOn(Actor empty) {
        return actor;
    }

    @Override
    public String toString() {
        return String.format("NewActor on: %s to: %s", actorId, actor);
    }
}
