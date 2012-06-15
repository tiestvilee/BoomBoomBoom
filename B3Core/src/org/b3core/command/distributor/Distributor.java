package org.b3core.command.distributor;

import org.b3core.actions.actor.ActorAction;
import org.b3core.command.director.Director;
import org.b3core.command.director.DirectorFactory;
import org.b3core.command.stages.Stage;

/**
 * copyright Tiest Vilee 2012
 * Date: 31/05/2012
 * Time: 23:15
 */
public class Distributor {
    static final int MAX_TICKS = 5;

    private final DirectorFactory directorFactory;
    private final Director[] directors = new Director[MAX_TICKS];
    private int currentTick = 0;

    public Distributor(DirectorFactory directorFactory, Stage originalStage) {
        this.directorFactory = directorFactory;
        directors[currentTick] = directorFactory.newDirector().nextTickFrom(originalStage);
    }

    public int tick() {
        int newTick = currentTick + 1;
        directors[newTick % MAX_TICKS] = directorFactory.newDirector().nextTickFrom(getLatestDirector(0).getNextStage());
        currentTick = newTick;
        return currentTick;
    }

    public int currentTick() {
        return currentTick;
    }

    public Director getDirector(int i) {
        return directors[i % MAX_TICKS];
    }

    public void applyActionAt(int tick, ActorAction actorAction) {
        if(tick < currentTick - MAX_TICKS || tick > currentTick) {
            throw new RuntimeException(String.format("Tried to apply action %s at %s, but that time is in valid for currentTick of %s", actorAction, tick, currentTick));
        }
        getDirector(tick).applyAction(actorAction);
    }

    public Director getLatestDirector(int frameOffset) {
        return directors[(currentTick + frameOffset) % MAX_TICKS];
    }
}
