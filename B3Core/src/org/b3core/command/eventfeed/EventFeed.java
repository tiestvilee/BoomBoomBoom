package org.b3core.command.eventfeed;

import org.b3core.actions.Action;
import org.b3core.command.distributor.Distributor;
import org.b3core.support.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * copyright Tiest Vilee 2012
 * Date: 05/06/2012
 * Time: 22:51
 */
public class EventFeed {
    private final Distributor distributor;
    private final List<Listener<Action>> listeners;

    public EventFeed(Distributor distributor) {
        this.distributor = distributor;
        listeners = new ArrayList<Listener<Action>>();
    }

    public void process(Action action) {
        action.process(distributor);
        for(Listener<Action> listener : listeners) {
            listener.notify(action);
        }
    }

    public void addActionListener(Listener<Action> listener) {
        listeners.add(listener);
    }
}
