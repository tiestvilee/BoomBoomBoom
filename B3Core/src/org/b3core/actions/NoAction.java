package org.b3core.actions;

import org.b3core.actors.Actor;

/**
 * copyright Tiest Vilee 2012
 * Date: 29/05/2012
 * Time: 22:39
 */
public class NoAction implements ActorAction {
    public Actor actOn(Actor actor) {
        return actor;
    }
}
