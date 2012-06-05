package org.b3core.command.distributor;

import org.b3core.actions.actor.ActorAction;
import org.b3core.command.director.Director;
import org.b3core.command.director.DirectorFactory;

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

    public Distributor(DirectorFactory directorFactory) {
        this.directorFactory = directorFactory;
        directors[currentTick++] = directorFactory.newDirector();
    }

    public void tick() {
        directors[currentTick % MAX_TICKS] = directorFactory.newDirector().nextTickFrom(directors[(currentTick - 1) % MAX_TICKS].getNextStage());
        currentTick++;
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
}
