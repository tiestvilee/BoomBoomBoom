package org.b3core.stages;

import org.b3core.actions.ActorAction;
import org.b3core.actors.Actor;
import org.b3core.actors.ActorId;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tiestvilee
 * Date: 05/05/2012
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class Stage {
    private final Stage stage;
    private final Map<ActorId, Actor> postActors = new HashMap<ActorId, Actor>();
    private final Map<ActorId, Actor> newActors = new HashMap<ActorId, Actor>();


    public Stage(Stage stage) {
        this.stage = stage;
    }

    public void addActor(Actor actor) {
        newActors.put(actor.id, actor);
        postActors.put(actor.id, processActor(actor));
    }

    public Stage tick() {
        // Director?
        Stage newStage = new Stage(this);
        newStage.copyOldActors();
        newStage.processAllActors();
        return newStage;
    }

    private void copyOldActors() {
        postActors.putAll(stage.postActors);
    }

    private void processAllActors() {
        for(ActorId id: postActors.keySet()) {
            postActors.put(id, processActor(postActors.get(id)));
        }
    }

    private Actor processActor(Actor actor) {
        return actor.move();
    }

    public Actor getPostActor(ActorId id) {
        return postActors.get(id);
    }

    public void addAction(ActorId id, ActorAction actorAction) {
        Actor oldTickActor = getPreActor(id);
        postActors.put(id, processActor(actorAction.actOn(oldTickActor)));

    }

    private Actor getPreActor(ActorId id) {
        Actor oldTickActor = stage.getPostActor(id);
        if(oldTickActor == null) {
            oldTickActor = newActors.get(id);
        }
        return oldTickActor;
    }
}
