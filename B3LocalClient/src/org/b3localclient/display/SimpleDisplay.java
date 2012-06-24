package org.b3localclient.display;

import org.b3core.actors.Actor;
import org.b3core.command.distributor.Distributor;
import org.b3core.command.stages.Stage;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * copyright Tiest Vilee 2012
 * Date: 15/06/2012
 * Time: 22:46
 */
public class SimpleDisplay extends JPanel {

    public static final int TICKS_PER_SECOND = 20;
    public static final int ICON_SIZE_PX = 32;
    public static final int MAP_SIZE = 20;
    public static final int MAP_SIZE_PX = MAP_SIZE * ICON_SIZE_PX;
    public static final int STEP_SIZE = 4; // ICON_SIZE_PX * 2 / TICKS_PER_SECOND;

    private final String name;
    private final Distributor distributor;
    private final int frameOffset;
    public AtomicBoolean shouldRepaint = new AtomicBoolean(true);

    SimpleDisplay(String name, Distributor distributor, int frameOffset) {
        this.name = name;
        this.distributor = distributor;
        this.frameOffset = frameOffset;
        setSize(new Dimension(MAP_SIZE_PX,MAP_SIZE_PX));
        setMinimumSize(new Dimension(MAP_SIZE_PX,MAP_SIZE_PX));
        setPreferredSize(new Dimension(MAP_SIZE_PX,MAP_SIZE_PX));
    }

    @Override
    public void paint(Graphics graphics) {
        if(shouldRepaint.get()) {
            graphics.clearRect(0,0,MAP_SIZE_PX,MAP_SIZE_PX);
            graphics.drawString(name, 20, 30);

            for(int i=0; i<=MAP_SIZE; i++) {
                int offset = i * ICON_SIZE_PX;
                graphics.drawLine(offset, 0, offset, MAP_SIZE_PX);
                graphics.drawLine(0, offset, MAP_SIZE_PX, offset);
            }

            Stage stage = distributor.getLatestDirector(frameOffset).getOriginalStage();

            for(Actor actor : stage.getActors().values()) {
                graphics.fillRect(actor.location.x * STEP_SIZE, actor.location.y * STEP_SIZE, ICON_SIZE_PX, ICON_SIZE_PX);
            }
            shouldRepaint.set(false);
        }
    }
}
