package org.b3core.director;

import org.b3core.actions.ActorAction;
import org.b3core.actions.NoAction;
import org.b3core.actors.Actor;
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

    public Director() {
        nextStage = new Stage();
    }

    public Director nextTickFrom(Stage originalStage) {
        this.originalStage = originalStage;
        originalStage.addListener(this);

        for(ActorId actorId : originalStage.getActors().keySet()) {
            applyAction(actorId, new NoAction());
        }

        return this;
    }

    public Stage getNextStage() {
        return nextStage;
    }

    public void notify(Actor event) {
        nextStage.updateActor(event);
    }

    public void applyAction(ActorId actorId, ActorAction action) {
        Actor updatedActor = action.actOn(originalStage.getActor(actorId));
        updatedActor = updatedActor.move();
        nextStage.updateActor(updatedActor);
    }

}
