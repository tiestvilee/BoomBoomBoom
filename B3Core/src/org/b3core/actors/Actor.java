package org.b3core.actors;

import org.b3core.actions.actor.ActorAction;
import org.b3core.actions.actor.NoAction;
import org.b3core.command.stages.Stage;
import org.b3core.fundamentals.Point;
import org.b3core.fundamentals.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiestvilee
 * Date: 05/05/2012
 * Time: 21:09
 * To change this template use File | Settings | File Templates.
 */
public class Actor {
    public ActorId id;
    public Rectangle location;
    public List<ActorAction> actions = new ArrayList<ActorAction>(4);

    public Actor() {
        // for actor cache
    }

    public Actor(ActorId id, Rectangle location) {
        this.id = id;
        this.location = location;
    }

    public Actor move(Stage stage) {
        return this;
    }

    public Actor moveTo(Point newPoint) {
        location = location.offset(newPoint);
        return this;
    }

    public ActorAction whatNext(Stage originalStage) {
        return new NoAction(this.id);
    }

    public void copyOnto(Actor newActor) {
        newActor.id = id;
        newActor.location = location;
    }

    public Actor addAction(ActorAction action) {
        actions.add(action);
        return this;
    }

    public Actor applyActions(Stage stage) {
        for(ActorAction action : actions) {
            action.actOn(this);
        }
        move(stage);
        return this;
    }

    public Actor setLocation(Point point) {
        location = location.moveTo(point);
        return this;
    }
}
