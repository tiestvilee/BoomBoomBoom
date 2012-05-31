package org.b3core.actors;

import org.b3core.actors.Actor;

/**
 * copyright Tiest Vilee 2012
 * Date: 31/05/2012
 * Time: 22:13
 */
public class ActorFactory {

    public Actor newActor(Actor original) {
        Actor newActor;
        try {
            newActor = original.getClass().newInstance();
            original.copyOnto(newActor);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return newActor;
    }


}
