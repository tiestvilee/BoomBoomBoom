package org.b3localclient.display;

import org.b3core.actions.actor.ChangeVelocity;
import org.b3core.actions.actor.NewActor;
import org.b3core.actors.ActorId;
import org.b3core.actors.DumbActor;
import org.b3core.command.distributor.Distributor;
import org.b3core.command.distributor.DistributorFactory;
import org.b3core.command.eventfeed.EventFeed;
import org.b3core.fundamentals.Point;
import org.b3core.fundamentals.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * copyright Tiest Vilee 2012
 * Date: 15/06/2012
 * Time: 22:48
 */
public class Main {

    public static void main(String args[]) {
        DistributorFactory distributorFactory = new DistributorFactory();
        final Distributor distributor = distributorFactory.getDistributor();
        EventFeed feed = new EventFeed(distributor);

        final SimpleDisplay display = new SimpleDisplay("Latest", distributor, 0);
        final SimpleDisplay oldDisplay = new SimpleDisplay("Oldest", distributor, -4);

        distributor.getLatestDirector(0).applyAction(new NewActor(new DumbActor(new ActorId(42), new Rectangle(50, 50), new Point(0, 0))));
        distributor.tick();
        distributor.tick();
        distributor.tick();
        distributor.tick();
        int tick = distributor.tick();
        distributor.applyActionAt(tick, new ChangeVelocity(new ActorId(42), new Point(1,1)));

        JFrame frame = new JFrame("Local Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p = new JPanel(new FlowLayout());
        frame.getContentPane().add(p, BorderLayout.CENTER);
        p.add(display);
        p.add(oldDisplay);
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
                distributor.applyActionAt(distributor.currentTick(), new ChangeVelocity(new ActorId(42), new Point(horizontal, vertical)));
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
                    int tick = distributor.tick();

                    if(random.nextInt(10) == 0) {
                        distributor.applyActionAt(tick, new ChangeVelocity(new ActorId(42), new Point(random.nextInt(3)-1,random.nextInt(3)-1)));
                    }

                    display.shouldRepaint.set(true);
                    display.repaint();
                    oldDisplay.shouldRepaint.set(true);
                    oldDisplay.repaint();

                    try {
                        Thread.sleep(1000 / SimpleDisplay.TICKS_PER_SECOND * 5);
                    } catch (InterruptedException e) {
                        // whatever
                    }
                }
            }
        }).run();
    }

}
