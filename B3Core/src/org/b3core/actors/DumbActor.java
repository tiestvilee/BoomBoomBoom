package org.b3core.actors;

/**
 * Created with IntelliJ IDEA.
 * User: tiestvilee
 * Date: 05/05/2012
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class DumbActor extends Actor {

    public final int dx;
    public final int dy;

    public DumbActor(ActorId id, int x, int y, int dx, int dy) {
        super(id, x, y);
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public Actor move() {
        return new DumbActor(id, x+dx, y+dy, dx, dy);
    }
}
