package org.conwayplus.Controller;

import org.conwayplus.Model.SimulatieEngine;
import org.conwayplus.Model.Wereld;
import org.conwayplus.View.ControlPaneel;
import org.conwayplus.View.GridPaneel;

// Deze klasse luistert naar de GameClock en voert acties uit wanneer er een 'tik' is.
public class TickVerwerker implements TickListener {
    private final Wereld wereld;
    private final SimulatieEngine engine;
    private final GridPaneel gridPaneel;
    private final ControlPaneel controlPaneel;
    private int tickTeller = 0; // Houdt bij hoeveel stappen de simulatie al heeft gezet.

    public TickVerwerker(Wereld wereld, GridPaneel gridPaneel) {
        this.wereld = wereld;
        this.engine = new SimulatieEngine();
        this.gridPaneel = gridPaneel;
        this.controlPaneel = new ControlPaneel();
    }

    // Dit wordt aangeroepen door de GameClock.
    @Override
    public void verwerkTick() {
        // 1. Laat de wereld één generatie verder evolueren.
        engine.evolueer(wereld);
        tickTeller++;

        // 2. Update de interface zodat de speler het resultaat ziet.
        if (controlPaneel != null) {
            controlPaneel.setTickCount(tickTeller); // Toon het aantal stappen.
        }
        if (gridPaneel != null) {
            gridPaneel.repaint(); // Teken het scherm opnieuw met de nieuwe data.
        }
    }

    // Reset de teller naar nul (bijvoorbeeld als je op 'Clear' drukt).
    public void resetTicks() {
        this.tickTeller = 0;
        if (controlPaneel != null) {
            controlPaneel.setTickCount(0);
        }
    }
    //Voor tests
    public int getTickTeller() {
        return tickTeller;
    }
}