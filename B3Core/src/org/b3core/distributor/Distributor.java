package org.b3core.distributor;

import org.b3core.director.Director;
import org.b3core.director.DirectorFactory;

/**
 * copyright Tiest Vilee 2012
 * Date: 31/05/2012
 * Time: 23:15
 */
public class Distributor {
    private final DirectorFactory directorFactory;

    private static final int MAX_TICKS = 5;

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
}
