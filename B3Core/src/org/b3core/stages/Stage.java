package org.b3core.stages;

import org.b3core.actions.ActorAction;
import org.b3core.actors.Actor;
import org.b3core.actors.ActorId;
import org.b3core.support.Listener;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: tiestvilee
 * Date: 05/05/2012
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class Stage {
    private final Map<ActorId, Actor> actors = new HashMap<ActorId, Actor>();
    private List<Listener<Actor>> listeners = new ArrayList<Listener<Actor>>(4);

    public Stage() {
    }

    public void addActor(Actor actor) {
        actors.put(actor.id, actor);
        notifyListeners(actor);
    }

    public void updateActor(Actor actor) {
        addActor(actor);
    }

    public Map<ActorId,Actor> getActors() {
        return Collections.unmodifiableMap(actors);
    }

    public Stage copy() {
        Stage newStage = new Stage();
        for(Actor actor : actors.values()) {
            newStage.addActor(actor);
        }
        return newStage;
    }

    public void addListener(Listener<Actor> listener) {
        listeners.add(listener);
    }

    private void notifyListeners(Actor event) {
        for(Listener<Actor> listener : listeners) {
            listener.notify(event);
        }
    }


}
