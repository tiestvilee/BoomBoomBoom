package org.b3core.acceptance;

import org.b3core.fundamentals.Point;
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

//    private final ActorId actorId = new ActorId(4765);
//    private final Stage aStage = new Stage(new EmptyStage());
//
//    @Test
//    public void aDumbActorCanStayWhereItIs() {
//        // given
//        Actor dumbActor = new DumbActor(actorId, new Point(7, 11), new Point(0, 0));
//
//        // when
//        aStage.addActor(dumbActor);
//
//        // then
//        assertThat(aStage.getPostActor(actorId).location, is(new Point(7, 11)));
//
//        // when
//        Stage nextTick = aStage.tick();
//
//        // then
//        assertThat(aStage.getPostActor(actorId).location, is(new Point(7, 11)));
//    }
//
//    @Test
//    public void aDumbActorCanHaveAVelocity() {
//        // given
//        Actor dumbActor = new DumbActor(actorId, new Point(7, 11), new Point(1, 0));
//
//        // when
//        aStage.addActor(dumbActor);
//
//        // then
//        DumbActor actor = (DumbActor) aStage.getPostActor(actorId);
//
//        assertThat(actor.location, is(new Point(8, 11)));
//        assertThat(actor.velocity, is(new Point(1, 0)));
//
//        // when
//        Stage nextTick = aStage.tick();
//
//        // then
//        actor = (DumbActor) nextTick.getPostActor(actorId);
//
//        assertThat(actor.location, is(new Point(9, 11)));
//        assertThat(actor.velocity, is(new Point(1, 0)));
//    }
//
//    @Test
//    public void aDumbActorCanBeToldToChangeDirection() {
//        // given
//        Actor dumbActor = new DumbActor(actorId, new Point(7, 11), new Point(1, 0));
//        aStage.addActor(dumbActor);
//
//        // when
//        Stage nextTick = aStage.tick();
//        nextTick.addAction(actorId, new ChangeDirection(new Point(0, 1)));
//
//        // then
//        DumbActor actor = (DumbActor) nextTick.getPostActor(actorId);
//
//        assertThat(actor.location, is(new Point(8, 12)));
//        assertThat(actor.velocity, is(new Point(0, 1)));
//    }
//
//    @Test
//    public void aNewDumbActorCanBeToldToChangeDirection() {
//        // given
//        Actor dumbActor = new DumbActor(actorId, new Point(7, 11), new Point(0, 0));
//
//        // when
//        aStage.addActor(dumbActor);
//        aStage.addAction(actorId, new ChangeDirection(new Point(0, 1)));
//
//        // then
//        DumbActor actor = (DumbActor) aStage.getPostActor(actorId);
//
//        assertThat(actor.location, is(new Point(7, 12)));
//        assertThat(actor.velocity, is(new Point(0, 1)));
//    }
}
