package org.b3core.actions;

import org.b3core.actions.actor.ActorAction;
import org.b3core.command.distributor.Distributor;

/**
 * copyright Tiest Vilee 2012
 * Date: 05/06/2012
 * Time: 23:15
 */
public class WithinTickAction implements Action {
    private final int whichTick;
    private final ActorAction actorAction;

    public WithinTickAction(int whichTick, ActorAction actorAction) {
        this.whichTick = whichTick;
        this.actorAction = actorAction;
    }

    public void process(Distributor distributor) {
        distributor.applyActionAt(whichTick, actorAction);
    }
}
