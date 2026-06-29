package org.conwayplus.Controller;

import org.conwayplus.Model.*;
import org.conwayplus.View.*;

public class TickVerwerker implements TickListener {
    private final Wereld wereld;
    private final SimulatieEngine engine;
    private final GridPaneel gridPaneel;
    private final ControlPaneel controlPaneel;
    private int tickTeller = 0;

    // Constructor voor productie
    public TickVerwerker(Wereld wereld, GridPaneel gridPaneel) {
        this(wereld, gridPaneel, new ControlPaneel());
    }

    // Constructor voor tests (Dependency Injection)
    public TickVerwerker(Wereld wereld, GridPaneel gridPaneel, ControlPaneel controlPaneel) {
        this.wereld = wereld;
        this.engine = new SimulatieEngine();
        this.gridPaneel = gridPaneel;
        this.controlPaneel = controlPaneel;
    }

    @Override
    public void verwerkTick() {
        engine.evolueer(wereld);
        tickTeller++;
        if (controlPaneel != null) controlPaneel.setTickCount(tickTeller);
        if (gridPaneel != null) gridPaneel.repaint();
    }

    public void resetTicks() {
        this.tickTeller = 0;
        if (controlPaneel != null) controlPaneel.setTickCount(0);
    }

    public int getTickTeller() { return tickTeller; }
}