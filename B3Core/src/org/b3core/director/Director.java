package org.b3core.director;

import org.b3core.actions.ActorAction;
import org.b3core.actors.Actor;
import org.b3core.actors.ActorFactory;
import org.b3core.actors.ActorId;
import org.b3core.stages.Stage;
import org.b3core.support.Listener;

/**
 * copyright Tiest Vilee 2012
 * Date: 29/05/2012
 * Time: 21:18
 */
public class Director implements Listener<Actor> {

    private final Stage nextStage;
    private Stage originalStage;
    private final ActorFactory factory;

    public Director(ActorFactory factory) {
        this.factory = factory;
        nextStage = new Stage();
    }

    public Director nextTickFrom(Stage originalStage) {
        this.originalStage = originalStage;
        originalStage.addListener(this);

        for(Actor actor : originalStage.getActors().values()) {
            Actor myActor = factory.newActor(actor);
            nextStage.addActor(myActor);
            applyAction(myActor.id, myActor.whatNext(originalStage));
        }

        return this;
    }

    public Stage getNextStage() {
        return nextStage;
    }

    public void notify(Actor event) {
        applyAction(event.id, event.whatNext(originalStage));
    }

    public void applyAction(ActorId actorId, ActorAction action) {
        Actor myActor = nextStage.getActor(actorId);
        Actor oldActor = originalStage.getActor(actorId);
        oldActor.copyOnto(myActor);

        myActor
            .addAction(action)
            .applyActions();
        nextStage.updateActor(myActor);
    }

}
