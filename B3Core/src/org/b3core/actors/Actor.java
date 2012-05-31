package org.b3core.actors;

import org.b3core.actions.ActorAction;
import org.b3core.actions.NoAction;
import org.b3core.fundamentals.Point;
import org.b3core.stages.Stage;

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
    public Point location;
    public List<ActorAction> actions = new ArrayList<ActorAction>(4);

    public Actor() {
        // for actor cache
    }

    public Actor(ActorId id, Point location) {
        this.id = id;
        this.location = location;
    }

    public Actor move() {
        return this;
    }

    public Actor moveTo(Point newPoint) {
        location = newPoint;
        return this;
    }

    public ActorAction whatNext(Stage originalStage) {
        return new NoAction();
    }

    public void copyOnto(Actor newActor) {
        newActor.id = id;
        newActor.location = location;
    }

    public Actor addAction(ActorAction action) {
        actions.add(action);
        return this;
    }

    public Actor applyActions() {
        for(ActorAction action : actions) {
            action.actOn(this);
        }
        move();
        return this;
    }

    public Actor setLocation(Point point) {
        location = point;
        return this;
    }
}
