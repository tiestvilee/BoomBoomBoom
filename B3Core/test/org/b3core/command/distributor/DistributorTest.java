package org.b3core.command.distributor;

import org.b3core.actions.actor.ChangeVelocity;
import org.b3core.actors.ActorFactory;
import org.b3core.actors.ActorId;
import org.b3core.actors.DumbActor;
import org.b3core.command.director.DirectorFactory;
import org.b3core.fundamentals.Point;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

/**
 * copyright Tiest Vilee 2012
 * Date: 31/05/2012
 * Time: 23:16
 */
public class DistributorTest {

    public static final ActorId ACTOR_ID = new ActorId(34);

    @Test
    public void shouldCreateDirectorsEveryTick() {
        Distributor distributor = new Distributor(new DirectorFactory(new ActorFactory()));

        assertThat(distributor.getDirector(0), not(nullValue()));
        assertThat(distributor.getDirector(1), nullValue());

        distributor.tick(); // 1
        distributor.tick(); // 2
        distributor.tick(); // 3
        distributor.tick(); // 4

        assertThat(distributor.getDirector(1).getOriginalStage(), is(distributor.getDirector(0).getNextStage()));
        assertThat(distributor.getDirector(2).getOriginalStage(), is(distributor.getDirector(1).getNextStage()));
        assertThat(distributor.getDirector(3).getOriginalStage(), is(distributor.getDirector(2).getNextStage()));
        assertThat(distributor.getDirector(4).getOriginalStage(), is(distributor.getDirector(3).getNextStage()));

    }

    @Test
    public void shouldPropagateActorsThroughAllDirectors() {
        Distributor distributor = new Distributor(new DirectorFactory(new ActorFactory()));

        distributor.getDirector(0).getNextStage().addActor(new DumbActor(ACTOR_ID, new Point(5, 7), new Point(1, 2)));

        distributor.tick(); // 1
        distributor.tick(); // 2
        distributor.tick(); // 3
        distributor.tick(); // 4

        assertThat(distributor.getDirector(4).getNextStage().getActor(ACTOR_ID).location, is(new Point(5+1+1+1+1,7+2+2+2+2)));

    }

    @Test
    public void shouldPropagateActorActionsThroughAllDirectors() {
        Distributor distributor = new Distributor(new DirectorFactory(new ActorFactory()));

        distributor.getDirector(0).getNextStage().addActor(new DumbActor(ACTOR_ID, new Point(7,5), new Point(2,1)));

        distributor.tick(); // 1
        distributor.tick(); // 2
        distributor.tick(); // 3
        distributor.tick(); // 4

        distributor.applyActionAt(2, new ChangeVelocity(ACTOR_ID, new Point(-3, -1)));

        assertThat(distributor.getDirector(4).getNextStage().getActor(ACTOR_ID).location, is(new Point(7+2-3-3-3, 5+1-1-1-1)));

    }

    @Test
    public void shouldRollAroundWhenReachingMaximumTicks() {
        Distributor distributor = new Distributor(new DirectorFactory(new ActorFactory()));
        assertThat(Distributor.MAX_TICKS, is(5));

        distributor.tick(); // 1
        distributor.tick(); // 2
        distributor.tick(); // 3
        distributor.tick(); // 4

        distributor.tick(); // 5

        assertThat(distributor.getDirector(5).getOriginalStage(), is(distributor.getDirector(4).getNextStage()));

        distributor.tick(); // 6
        assertThat(distributor.getDirector(6).getOriginalStage(), is(distributor.getDirector(5).getNextStage()));
    }
}
