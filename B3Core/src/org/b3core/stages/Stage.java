package org.b3core.stages;

import org.b3core.actors.Actor;
import org.b3core.actors.ActorId;
import sun.jvm.hotspot.debugger.linux.x86.LinuxX86ThreadContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private final Map<ActorId, Actor> newActors = new HashMap<ActorId, Actor>();
    private final Map<ActorId, Actor> oldActors = new HashMap<ActorId, Actor>();


    public Stage(Stage stage) {
        this.stage = stage;
    }

    public void addActor(Actor actor) {
        newActors.put(actor.id, actor);
    }

    public Stage tick() {
        // Director?
        Stage newStage = new Stage(this);
        newStage.copyOldActors();
        newStage.moveAllActors();
        return newStage;
    }

    private void copyOldActors() {
        oldActors.putAll(stage.oldActors);
        oldActors.putAll(stage.newActors);
    }

    private void moveAllActors() {
        for(ActorId id:oldActors.keySet()) {
            oldActors.put(id, oldActors.get(id).move());
        }
    }

    public Actor getActor(ActorId id) {
        Actor actor = oldActors.get(id);
        if(actor == null) {
            actor = newActors.get(id);
        }
        return actor;
    }
}
