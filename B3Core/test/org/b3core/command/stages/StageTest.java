package org.b3core.command.stages;

import org.b3core.actors.Actor;
import org.b3core.actors.ActorId;
import org.b3core.fundamentals.Point;
import org.b3core.fundamentals.Rectangle;
import org.b3core.support.Listener;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * copyright Tiest Vilee 2012
 * Date: 21/05/2012
 * Time: 22:56
 */
public class StageTest {
    private ActorId actorId = new ActorId(232344);
    Rectangle location = new Rectangle(3, 5);
    public Actor updatedActor;

    @Test
    public void stageShouldAcceptNewActors() {
        Stage stage = new Stage();
        Actor actor = new Actor(actorId, location);

        stage.addActor(actor);

        assertThat(stage.getActors().containsKey(actor.id), is(true));
    }

    @Test
    public void stageCanBeCopiedAlongWithItsActors() {
        Stage stage = new Stage();
        Actor actor = new Actor(actorId, location);
        stage.addActor(actor);

        Stage newStage = stage.copy();

        assertThat(newStage.getActors().containsKey(actor.id), is(true));
        assertThat(newStage.getActors().get(actor.id), sameInstance(actor)); // is immutable object, so can be same
    }

    @Test
    public void canListenForChangesToActors() {
        Stage stage = new Stage();
        Actor actor = new Actor(actorId, location);
        updatedActor = null;

        Listener<Actor> listener = new Listener<Actor>() {
            public void notify(Actor actor) {
                updatedActor = actor;
            }
        };
        stage.addListener(listener);

        // when
        stage.addActor(actor);

        // then
        assertThat(updatedActor, is(actor));

        updatedActor = null;

        // when
        Actor movedActor = actor.moveTo(new Point(7, 13));
        stage.updateActor(movedActor);

        // then
        assertThat(updatedActor, is(movedActor));
    }
}
