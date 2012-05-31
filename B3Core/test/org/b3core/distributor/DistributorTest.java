package org.b3core.distributor;

import org.b3core.actors.ActorFactory;
import org.b3core.director.DirectorFactory;
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
    public void shouldRollAroundWhenReachingMaximumTicks() {
        Distributor distributor = new Distributor(new DirectorFactory(new ActorFactory()));

        distributor.tick(); // 1
        distributor.tick(); // 2
        distributor.tick(); // 3
        distributor.tick(); // 4

        distributor.tick(); // 5

        assertThat(distributor.getDirector(5).getOriginalStage(), is(distributor.getDirector(4).getNextStage()));

    }
}
