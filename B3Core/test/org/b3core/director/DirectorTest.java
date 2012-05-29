package org.b3core.director;

import org.b3core.actions.ChangeVelocity;
import org.b3core.actors.Actor;
import org.b3core.actors.ActorId;
import org.b3core.actors.DumbActor;
import org.b3core.fundamentals.Point;
import org.b3core.stages.Stage;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

/**
 * copyright Tiest Vilee 2012
 * Date: 29/05/2012
 * Time: 21:25
 */
public class DirectorTest {

    @Test
    public void nextStageShouldHaveSameActorsAsCurrentStage() {
        Stage originalStage = new Stage();
        originalStage.addActor(new Actor(new ActorId(23), new Point(4,5)));

        Director director = new Director().nextTickFrom(originalStage);

        assertThat(director.getNextStage().getActors().get(new ActorId(23)), not(nullValue()));
    }

    @Test
    public void shouldUpdateNextStageWhenCurrentStageChanges() {
        Stage originalStage = new Stage();
        originalStage.addActor(new Actor(new ActorId(23), new Point(2, 5)));

        Director director = new Director().nextTickFrom(originalStage);

        originalStage.updateActor(new Actor(new ActorId(23), new Point(7, 3)));

        assertThat(director.getNextStage().getActor(new ActorId(23)).location, is(new Point(7, 3)));
    }

    @Test
    public void shouldUpdateActorsUsingDefaultActions() {
        Stage originalStage = new Stage();
        originalStage.addActor(new DumbActor(new ActorId(23), new Point(2, 5), new Point(1, 1)));

        Director director = new Director().nextTickFrom(originalStage);

        assertThat(director.getNextStage().getActor(new ActorId(23)).location, is(new Point(3, 6)));
    }

    @Test
    public void shouldApplyActionToActor() {
        Stage originalStage = new Stage();
        originalStage.addActor(new DumbActor(new ActorId(23), new Point(2, 5), new Point(1, 1)));

        Director director = new Director().nextTickFrom(originalStage);

        director.applyAction(new ActorId(23), new ChangeVelocity(new Point(-1,-1)));

        assertThat(director.getNextStage().getActor(new ActorId(23)).location, is(new Point(1, 4)));
    }
}
