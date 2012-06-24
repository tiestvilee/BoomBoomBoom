package org.b3core.command.stages;

import org.b3core.actors.Actor;
import org.b3core.actors.ActorId;
import org.b3core.fundamentals.Rectangle;
import org.b3core.support.Listener;

import java.util.ArrayList;
import java.util.Collections;
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

    public Actor getActor(ActorId actorId) {
        return actors.get(actorId);
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

    public List<Actor> whatsAt(Rectangle location) {
        List<Actor> intersection = new ArrayList<Actor>();
        for(Actor actor:actors.values()) {
            if(actor.location.intersects(location)) {
                intersection.add(actor);
            }
        }
        return intersection;
    }
}
