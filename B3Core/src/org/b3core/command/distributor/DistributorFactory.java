package org.b3core.command.distributor;

import org.b3core.actors.ActorFactory;
import org.b3core.command.director.DirectorFactory;
import org.b3core.command.stages.Stage;

/**
 * copyright Tiest Vilee 2012
 * Date: 05/06/2012
 * Time: 22:16
 */
public class DistributorFactory {

    final Distributor distributor;
    final ActorFactory actorFactory;

    public DistributorFactory() {
        actorFactory = new ActorFactory();
        distributor = new Distributor(new DirectorFactory(actorFactory), new Stage());
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public ActorFactory getActorFactory() {
        return actorFactory;
    }


}
