package org.b3core.actors;

/**
 * Created with IntelliJ IDEA.
 * User: tiestvilee
 * Date: 05/05/2012
 * Time: 21:09
 * To change this template use File | Settings | File Templates.
 */
public class Actor {
    public final ActorId id;
    public final int x;
    public final int y;

    public Actor(ActorId id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public Actor move() {
        return this;
    }
}
