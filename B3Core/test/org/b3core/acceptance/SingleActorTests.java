package org.b3core.acceptance;

import org.b3core.Point;
import org.b3core.actions.ChangeDirection;import org.b3core.actors.Actor;
import org.b3core.actors.ActorId;
import org.b3core.actors.DumbActor;
import org.b3core.stages.Stage;
import org.b3core.stages.EmptyStage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: tiestvilee
 * Date: 05/05/2012
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
public class SingleActorTests {
    @Test
    public void aDumbActorCanStayWhereItIs() {
        // given
        ActorId id = new ActorId(4765);
        Stage aStage = new Stage(new EmptyStage());
        Actor dumbActor = new DumbActor(id, new Point(7, 11), new Point(0, 0));

        // when
        aStage.addActor(dumbActor);

        // then
        assertThat(aStage.getPostActor(id).location, is(new Point(7, 11)));

        // when
        Stage nextTick = aStage.tick();

        // then
        assertThat(aStage.getPostActor(id).location, is(new Point(7, 11)));
    }

    @Test
    public void aDumbActorCanHaveAVelocity() {
        // given
        ActorId id = new ActorId(4765);
        Stage aStage = new Stage(new EmptyStage());
        Actor dumbActor = new DumbActor(id, new Point(7, 11), new Point(1, 0));

        // when
        aStage.addActor(dumbActor);

        // then
        DumbActor actor = (DumbActor) aStage.getPostActor(id);

        assertThat(actor.location, is(new Point(8, 11)));
        assertThat(actor.velocity, is(new Point(1, 0)));

        // when
        Stage nextTick = aStage.tick();

        // then
        actor = (DumbActor) nextTick.getPostActor(id);

        assertThat(actor.location, is(new Point(9, 11)));
        assertThat(actor.velocity, is(new Point(1, 0)));
    }

    @Test
    public void aDumbActorCanBeToldToChangeDirection() {
        // given
        ActorId id = new ActorId(4765);
        Stage aStage = new Stage(new EmptyStage());
        Actor dumbActor = new DumbActor(id, new Point(7, 11), new Point(1, 0));
        aStage.addActor(dumbActor);

        // when
        Stage nextTick = aStage.tick();
        nextTick.addAction(id, new ChangeDirection(new Point(0, 1)));

        // then
        DumbActor actor = (DumbActor) nextTick.getPostActor(id);

        assertThat(actor.location, is(new Point(8, 12)));
        assertThat(actor.velocity, is(new Point(0, 1)));
    }

    @Test
    public void aNewDumbActorCanBeToldToChangeDirection() {
        // given
        ActorId id = new ActorId(4765);
        Stage aStage = new Stage(new EmptyStage());
        Actor dumbActor = new DumbActor(id, new Point(7, 11), new Point(0, 0));

        // when
        aStage.addActor(dumbActor);
        aStage.addAction(id, new ChangeDirection(new Point(0, 1)));

        // then
        DumbActor actor = (DumbActor) aStage.getPostActor(id);

        assertThat(actor.location, is(new Point(7, 12)));
        assertThat(actor.velocity, is(new Point(0, 1)));
    }
}
