package org.b3core.actions;

import org.b3core.command.distributor.Distributor;

/**
 * copyright Tiest Vilee 2012
 * Date: 05/06/2012
 * Time: 22:50
 */
public interface Action {
    public void process(Distributor distributor);
}
