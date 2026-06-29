package org.conwayplus.Controller;

import org.conwayplus.Model.*;
import org.conwayplus.View.ControlPaneel;
import org.conwayplus.View.GridPaneel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class InputController {
    private final Wereld wereld;
    private final GridPaneel gridPaneel;
    private final ControlPaneel controlPaneel;
    private final GameClock clock;
    private final TickVerwerker verwerker;

    public InputController(Wereld wereld, GridPaneel gridPaneel, ControlPaneel controlPaneel, GameClock clock, TickVerwerker verwerker) {
        this.wereld = wereld;
        this.gridPaneel = gridPaneel;
        this.controlPaneel = controlPaneel;
        this.clock = clock;
        this.verwerker = verwerker;
    }

    public void activeerKoppelingen() {
        // Muis: Links = Conway, Rechts = Alternatief
        gridPaneel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / gridPaneel.getCellSize();
                int y = e.getY() / gridPaneel.getCellSize();

                if (wereld.isLevend(x, y)) {
                    wereld.verwijderCel(x, y);
                } else {
                    CelType type = (e.getButton() == MouseEvent.BUTTON3) ? CelType.ALTERNATIEF : CelType.CONWAY;
                    wereld.plaatsCel(x, y, type);
                }
                gridPaneel.repaint();
            }
        });

        // Knoppen (Start, Stop, Clear, Snelheid)
        controlPaneel.getStartButton().addActionListener(e -> clock.start());
        controlPaneel.getStopButton().addActionListener(e -> clock.stop());
        controlPaneel.getClearButton().addActionListener(e -> {
            wereld.clear();
            verwerker.resetTicks();
            controlPaneel.setTickCount(0);
            gridPaneel.repaint();
        });
        controlPaneel.getVersnelButton().addActionListener(e -> clock.versnel());
        controlPaneel.getVerlangzaamButton().addActionListener(e -> clock.verlangzaam());

        // Patroon laden via de Factory
        controlPaneel.getLaadButton().addActionListener(e -> {
            wereld.clear();
            PatroonType type = (PatroonType) controlPaneel.getPatroonDropdown().getSelectedItem();
            PatroonFactory fabriek = new PatroonFactory();
            Map<Point, Cel> patroon = fabriek.creeerPatroon(type, 30, 30);

            for (Map.Entry<Point, Cel> entry : patroon.entrySet()) {
                CelType cType = (entry.getValue() instanceof ConwayCel) ? CelType.CONWAY : CelType.ALTERNATIEF;
                wereld.plaatsCel(entry.getKey().x, entry.getKey().y, cType);
            }
            gridPaneel.repaint();
        });
    }
}