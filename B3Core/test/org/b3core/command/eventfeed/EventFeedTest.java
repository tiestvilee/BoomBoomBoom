package org.b3core.command.eventfeed;

import org.b3core.actions.Tick;
import org.b3core.actions.WithinTickAction;
import org.b3core.actions.actor.ChangeVelocity;
import org.b3core.actors.ActorId;
import org.b3core.command.distributor.Distributor;
import org.b3core.command.distributor.DistributorFactory;
import org.b3core.fundamentals.Point;
import org.b3core.support.Listener;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * copyright Tiest Vilee 2012
 * Date: 05/06/2012
 * Time: 22:52
 */
public class EventFeedTest {

    @Test
    public void shouldSendTickToDistributor() {
        final Distributor mockDistributor = mock(Distributor.class);

        EventFeed feed = new EventFeed(mockDistributor);

        feed.process(new Tick());

        verify(mockDistributor).tick();
    }

    @Test
    public void shouldSendActorActionToDistributor() {
        final Distributor mockDistributor = mock(Distributor.class);
        ChangeVelocity action = new ChangeVelocity(new ActorId(56), new Point(-1, -1));

        EventFeed feed = new EventFeed(mockDistributor);

        feed.process(new WithinTickAction(0, action));

        verify(mockDistributor).applyActionAt(0, action);
    }

    @Test
    public void shouldNotifyProcessedActions() {
        ChangeVelocity action = new ChangeVelocity(new ActorId(56), new Point(-1, -1));

        Distributor distributor = new DistributorFactory().getDistributor();
        EventFeed feed = new EventFeed(distributor);

        feed.addActionListener(new Listener() {
            public void notify(Object event) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        feed.process(new WithinTickAction(0, action));

    }

}
