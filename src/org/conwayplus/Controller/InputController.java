package org.conwayplus.Controller;

import org.conwayplus.Model.*;
import org.conwayplus.View.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.JButton;

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
        if (gridPaneel != null) {
            gridPaneel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX() / gridPaneel.getCellSize();
                    int y = e.getY() / gridPaneel.getCellSize();
                    if (wereld.isLevend(x, y)) wereld.verwijderCel(x, y);
                    else wereld.plaatsCel(x, y, (e.getButton() == MouseEvent.BUTTON3) ? CelType.ALTERNATIEF : CelType.CONWAY);
                    gridPaneel.repaint();
                }
            });
        }

        if (controlPaneel != null) {
            safeAddActionListener(controlPaneel.getStartButton(), e -> clock.start());
            safeAddActionListener(controlPaneel.getStopButton(), e -> clock.stop());
            safeAddActionListener(controlPaneel.getClearButton(), e -> {
                wereld.clear();
                verwerker.resetTicks();
                controlPaneel.setTickCount(0);
                gridPaneel.repaint();
            });
            safeAddActionListener(controlPaneel.getVersnelButton(), e -> clock.versnel());
            safeAddActionListener(controlPaneel.getVerlangzaamButton(), e -> clock.verlangzaam());
        }
    }

    private void safeAddActionListener(JButton btn, java.awt.event.ActionListener al) {
        if (btn != null) btn.addActionListener(al);
    }
}