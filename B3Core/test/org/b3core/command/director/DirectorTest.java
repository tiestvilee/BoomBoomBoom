package org.b3core.command.director;

import org.b3core.actions.actor.ActorAction;
import org.b3core.actions.actor.ChangeVelocity;
import org.b3core.actions.actor.NewActor;
import org.b3core.actions.actor.Teleport;
import org.b3core.actors.Actor;
import org.b3core.actors.ActorFactory;
import org.b3core.actors.ActorId;
import org.b3core.actors.DumbActor;
import org.b3core.fundamentals.Point;
import org.b3core.command.stages.Stage;
import org.junit.Before;
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


    public static final ActorId ACTOR_ID = new ActorId(23);
    Stage originalStage;

    @Before
    public void setup() {
        originalStage = new Stage();
    }

    @Test
    public void nextStageShouldHaveSameActorsAsCurrentStage() {
        originalStage.addActor(new Actor(ACTOR_ID, new Point(4,5)));

        Director director = new Director(new ActorFactory()).nextTickFrom(originalStage);

        assertThat(director.getNextStage().getActors().get(ACTOR_ID), not(nullValue()));
    }

    @Test
    public void shouldUpdateNextStageWhenCurrentStageChanges() {
        originalStage.addActor(new Actor(ACTOR_ID, new Point(2, 5)));

        Director director = new Director(new ActorFactory()).nextTickFrom(originalStage);

        originalStage.updateActor(new Actor(ACTOR_ID, new Point(7, 3)));

        assertThat(director.getNextStage().getActor(ACTOR_ID).location, is(new Point(7, 3)));
    }

    @Test
    public void shouldUpdateActorsUsingDefaultActions() {
        originalStage.addActor(new DumbActor(ACTOR_ID, new Point(2, 5), new Point(1, 1)));

        Director director = new Director(new ActorFactory()).nextTickFrom(originalStage);

        assertThat(director.getNextStage().getActor(ACTOR_ID).location, is(new Point(2 + 1, 5 + 1)));
    }

    @Test
    public void shouldApplyActionToActor() {
        originalStage.addActor(new DumbActor(ACTOR_ID, new Point(2, 5), new Point(1, 1)));

        Director director = new Director(new ActorFactory()).nextTickFrom(originalStage);

        director.applyAction(new ChangeVelocity(ACTOR_ID, new Point(-1, -1)));

        assertThat(originalStage.getActor(ACTOR_ID).location, is(new Point(2, 5)));
        assertThat(director.getNextStage().getActor(ACTOR_ID).location, is(new Point(2 - 1, 5 - 1)));
    }

    @Test
    public void shouldGetActorToDecideItsNewAction() {
        originalStage.addActor(new DumbActorWithAction(new ChangeVelocity(ACTOR_ID, new Point(3, -1))));

        Director director = new Director(new ActorFactory()).nextTickFrom(originalStage);

        assertThat(director.getNextStage().getActor(ACTOR_ID).location, is(new Point(2 + 3, 5 - 1)));
    }

    @Test
    public void shouldUpdateNextStageWhenCurrentStageChangesAndApplyActors() {
        originalStage.addActor(new DumbActorWithAction(new ChangeVelocity(ACTOR_ID, new Point(-5, 7))));

        Director director = new Director(new ActorFactory()).nextTickFrom(originalStage);

        Actor originalStageActor = originalStage.getActor(ACTOR_ID);
        originalStage.updateActor(originalStageActor.setLocation(new Point(7, 3)));

        assertThat(director.getNextStage().getActor(ACTOR_ID).location, is(new Point(-5 + 7, 7 + 3)));
    }

    @Test
    public void shouldApplyMultipleActionsToOneActor() {
        originalStage.addActor(new DumbActorWithAction(new Teleport(ACTOR_ID, new Point(11, 3))));

        Director director = new Director(new ActorFactory()).nextTickFrom(originalStage);

        director.applyAction(new ChangeVelocity(ACTOR_ID, new Point(-2, -5)));

        assertThat(director.getNextStage().getActor(ACTOR_ID).location, is(new Point(11 - 2, 3 - 5)));
    }

    @Test
    public void canAddNewActorsToStage() {
        Director director = new Director(new ActorFactory()).nextTickFrom(originalStage);

        director.applyAction(new NewActor(new Actor(ACTOR_ID, new Point(-2, -5))));

        assertThat(director.getNextStage().getActor(ACTOR_ID).location, is(new Point(-2, -5)));
    }

    public static class DumbActorWithAction extends DumbActor {

        private ActorAction action;

        public DumbActorWithAction() {
            // for cacheing factor
        }

        public DumbActorWithAction(ActorAction action) {
            super(ACTOR_ID, new Point(2, 5), new Point(1, 1));
            this.action = action;
        }

        @Override
        public ActorAction whatNext(Stage originalStage) {
            return action;
        }

        @Override
        public void copyOnto(Actor newActor) {
            super.copyOnto(newActor);
            ((DumbActorWithAction) newActor).action = action;
        }
    }
}
