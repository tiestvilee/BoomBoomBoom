package org.b3core.command.director;

import org.b3core.actions.actor.ActorAction;
import org.b3core.actions.actor.NewActor;
import org.b3core.actors.Actor;
import org.b3core.actors.ActorFactory;
import org.b3core.command.stages.Stage;
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
            applyAction(myActor.whatNext(originalStage));
        }

        return this;
    }

    public Stage getOriginalStage() {
        return originalStage;
    }

    public Stage getNextStage() {
        return nextStage;
    }

    public void notify(Actor actor) {
        applyAction(actor.whatNext(originalStage));
    }

    public void applyAction(ActorAction action) {
        Actor myActor = nextStage.getActor(action.getActorId());
        Actor oldActor = originalStage.getActor(action.getActorId());
        if(myActor != null && oldActor != null) {
            oldActor.copyOnto(myActor);

            myActor
                .addAction(action)
                .applyActions();
            nextStage.updateActor(myActor);
        } else {
            if(action instanceof NewActor) {
                Actor actor = ((NewActor) action).actor;
                nextStage.addActor(actor);
            } else {
                nextStage.addActor(originalStage.getActor(action.getActorId()));
            }
        }
    }

}
