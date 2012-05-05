package org.b3core.acceptance;

import org.b3core.actors.Actor;
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
        Actor dumbActor = new DumbActor(id, 7, 11, 0, 0);

        // when
        aStage.addActor(dumbActor);

        // then
        assertThat(aStage.getActor(id).x, is(7));
        assertThat(aStage.getActor(id).y, is(11));

        // when
        Stage nextTick = aStage.tick();

        // then
        assertThat(nextTick.getActor(id).x, is(7));
        assertThat(nextTick.getActor(id).y, is(11));
    }

    @Test
    public void aDumbActorStaysCanHaveAVelocity() {
        // given
        ActorId id = new ActorId(4765);
        Stage aStage = new Stage(new EmptyStage());
        Actor dumbActor = new DumbActor(id, 7, 11, 1, 0);
        aStage.addActor(dumbActor);

        // when
        Stage nextTick = aStage.tick();

        // then
        assertThat(nextTick.getActor(id).x, is(8));
        assertThat(nextTick.getActor(id).y, is(11));
        assertThat(((DumbActor) nextTick.getActor(id)).dx, is(1));
        assertThat(((DumbActor) nextTick.getActor(id)).dy, is(0));
    }
}
