package org.b3core.director;

import org.b3core.actors.ActorFactory;

/**
 * copyright Tiest Vilee 2012
 * Date: 31/05/2012
 * Time: 23:21
 */
public class DirectorFactory {
    private ActorFactory actorFactory;

    public DirectorFactory(ActorFactory actorFactory) {
        this.actorFactory = actorFactory;
    }

    public Director newDirector() {
        return new Director(actorFactory);
    }
}
