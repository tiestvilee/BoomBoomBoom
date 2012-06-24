package org.b3localclient.display;

import org.b3core.actions.Action;
import org.b3core.actions.Tick;
import org.b3core.actions.WithinTickAction;
import org.b3core.actions.actor.ChangeVelocity;
import org.b3core.actions.actor.NewActor;
import org.b3core.actions.actor.Teleport;
import org.b3core.actors.Actor;
import org.b3core.actors.ActorId;
import org.b3core.actors.DumbActor;
import org.b3core.command.distributor.Distributor;
import org.b3core.command.distributor.DistributorFactory;
import org.b3core.command.eventfeed.EventFeed;
import org.b3core.fundamentals.Point;
import org.b3core.support.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * copyright Tiest Vilee 2012
 * Date: 15/06/2012
 * Time: 22:48
 */
public class Main2 {

    private static int serverTick;
    private static int clientTick;
    private static final int SLOW_DOWN = 1;
    private static final int DELAY = 4;

    public static void main(String args[]) {
        final List<Action> unprocessedEvents = Collections.synchronizedList(new ArrayList<Action>());
        final AtomicBoolean keypressed = new AtomicBoolean(false);

        DistributorFactory serverDistributorFactory = new DistributorFactory();
        final Distributor serverDistributor = serverDistributorFactory.getDistributor();
        final EventFeed serverFeed = new EventFeed(serverDistributor);

        DistributorFactory clientDistributorFactory = new DistributorFactory();
        final Distributor clientDistributor = clientDistributorFactory.getDistributor();
        final EventFeed clientFeed = new EventFeed(clientDistributor);

        serverFeed.addActionListener(new Listener<org.b3core.actions.Action>() {
            public void notify(Action event) {
                if(event instanceof WithinTickAction) {
                    System.out.println("Adding event " + event);
                    unprocessedEvents.add(event);
                }
            }
        });

        final SimpleDisplay serverDisplay = new SimpleDisplay("Server", serverDistributor, 0);
        final SimpleDisplay clientDisplay = new SimpleDisplay("Client", clientDistributor, 0);

        serverFeed.process(new WithinTickAction(0, new NewActor(new DumbActor(new ActorId(42), new org.b3core.fundamentals.Rectangle(50, 50, 8, 8), new Point(0, 0)))));
        serverFeed.process(new WithinTickAction(0, new NewActor(new DumbActor(new ActorId(24), new org.b3core.fundamentals.Rectangle(100, 100, 8, 8), new Point(0, 0)))));
        serverFeed.process(new Tick());
        serverFeed.process(new Tick());
        serverFeed.process(new Tick());
        serverFeed.process(new Tick());
        serverFeed.process(new Tick());
        serverTick = 5;
        serverFeed.process(new WithinTickAction(serverTick, new ChangeVelocity(new ActorId(42), new Point(1, 1))));

        clientFeed.process(new Tick());
        clientTick = serverTick - DELAY;

        JFrame frame = new JFrame("Local Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p = new JPanel(new FlowLayout());
        frame.getContentPane().add(p, BorderLayout.CENTER);
        p.add(serverDisplay);
        p.add(clientDisplay);
        frame.pack();
        frame.setVisible(true);

        frame.addKeyListener(new KeyAdapter() {
            boolean up
                ,
                down
                ,
                left
                ,
                right;

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case 37: // left
                        if (!left) {
                            left = true;
                            adjustDirection();
                        }
                        break;
                    case 38: // up
                        if (!up) {
                            up = true;
                            adjustDirection();
                        }
                        break;
                    case 39: // right
                        if (!right) {
                            right = true;
                            adjustDirection();
                        }
                        break;
                    case 40: // down
                        if (!down) {
                            down = true;
                            adjustDirection();
                        }
                        break;
                }
            }

            private void adjustDirection() {
                int vertical = 0, horizontal = 0;
                if (up && !down) {
                    vertical = -1;
                }
                if (down && !up) {
                    vertical = 1;
                }
                if (left && !right) {
                    horizontal = -1;
                }
                if (right && !left) {
                    horizontal = 1;
                }
                serverFeed.process(new WithinTickAction(clientTick, new ChangeVelocity(new ActorId(42), new Point(horizontal, vertical))));
                clientFeed.process(new WithinTickAction(clientTick, new ChangeVelocity(new ActorId(42), new Point(horizontal, vertical))));

                keypressed.set(vertical != 0 || horizontal != 0);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case 37: // left
                        if (left) {
                            left = false;
                            adjustDirection();
                        }
                        break;
                    case 38: // up
                        if (up) {
                            up = false;
                            adjustDirection();
                        }
                        break;
                    case 39: // right
                        if (right) {
                            right = false;
                            adjustDirection();
                        }
                        break;
                    case 40: // down
                        if (down) {
                            down = false;
                            adjustDirection();
                        }
                        break;
                }
            }
        });

        new Thread(new Runnable() {
            Random random = new Random();
            public void run() {
                while(true) {
                    serverFeed.process(new Tick());
                    clientFeed.process(new Tick());
                    serverTick++;
                    clientTick++;

//                    System.out.println(String.format("server: %s (%s) client: %s (%s)", serverTick, serverDistributor.currentTick(), clientTick, clientDistributor.currentTick()));

                    List<Action> processedEvents = new ArrayList<Action>();
                    synchronized (unprocessedEvents) {
                        for(Action action : unprocessedEvents) {
                            if(((WithinTickAction) action).whichTick < clientTick) {
                                System.out.println("processing event " + action);
                                clientFeed.process(action);
                                processedEvents.add(action);
                            }
                        }
                        for(Action action: processedEvents) {
                            unprocessedEvents.remove(action);
                        }
                    }

//                    if(random.nextInt(10) == 0 && !keypressed.get()) {
//                        Point newVelocity = new Point(random.nextInt(3) - 1, random.nextInt(3) - 1);
//                        clientFeed.process(new WithinTickAction(clientTick, new ChangeVelocity(new ActorId(42), newVelocity)));
//                        serverFeed.process(new WithinTickAction(clientTick, new ChangeVelocity(new ActorId(42), newVelocity)));
//                    }

                    if(random.nextInt(10) == 0) {
                        serverFeed.process(new WithinTickAction(serverTick, new ChangeVelocity(new ActorId(24), new Point(random.nextInt(3) - 1, random.nextInt(3) - 1))));
                    }
                    Actor actor24 = serverDistributor.getLatestDirector(0).getOriginalStage().getActor(new ActorId(24));
                    if(actor24.location.x < 0 || actor24.location.y < 0
                        || actor24.location.x > 8 * 20 || actor24.location.y > 8 * 20 ) {
                        serverFeed.process(new WithinTickAction(serverTick, new Teleport(new ActorId(24), new Point(50,50))));
                    }


                    serverDisplay.shouldRepaint.set(true);
                    serverDisplay.repaint();
                    clientDisplay.shouldRepaint.set(true);
                    clientDisplay.repaint();

                    try {
                        Thread.sleep(1000 / SimpleDisplay.TICKS_PER_SECOND * SLOW_DOWN);
                    } catch (InterruptedException e) {
                        // whatever
                    }
                }
            }
        }).run();
    }

}
