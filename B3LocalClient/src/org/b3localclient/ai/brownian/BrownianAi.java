package org.b3localclient.ai.brownian;

import org.b3core.actions.actor.ActorAction;
import org.b3localclient.ai.Ai;

import java.util.Random;

/**
 * copyright Tiest Vilee 2012
 * Date: 05/06/2012
 * Time: 22:26
 */
public class BrownianAi implements Ai {

    Random random = new Random((System.currentTimeMillis() & 0x0FFFFFFFF) & (System.currentTimeMillis() & 0x0FFFFFFFF << 32));

    public ActorAction processTick() {
//        if(random.nextInt(100) < 7) {
//            return new ChangeVelocity(new Point(random.nextInt(3)-1, random.nextInt(3)-1));
//        }
//        return new NoAction();
        return null;
    }
}
