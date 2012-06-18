package org.b3core.actors;

/**
 * Created with IntelliJ IDEA.
 * User: tiestvilee
 * Date: 05/05/2012
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
public class ActorId {
    public final int id;

    public ActorId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActorId actorId = (ActorId) o;

        if (id != actorId.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "ActorId: " + id;
    }
}
