package org.conwayplus.View;

import org.conwayplus.Model.Cel;
import org.conwayplus.Model.ConwayCel;
import org.conwayplus.Model.Wereld;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;

public class GridPaneel extends JPanel {
    private final Wereld wereld;
    private final int CELL_SIZE = 15;

    public GridPaneel(Wereld wereld) {
        this.wereld = wereld;
        setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 1. Teken de achtergrond grid-lijnen
        g.setColor(Color.GRAY);
        for (int i = 0; i < getWidth(); i += CELL_SIZE) {
            g.drawLine(i, 0, i, getHeight());
        }
        for (int j = 0; j < getHeight(); j += CELL_SIZE) {
            g.drawLine(0, j, getWidth(), j);
        }

        // 2. Vraag de data aan het model en teken de levende cellen
        for (Map.Entry<Point, Cel> entry : wereld.getLevendeCellenMap().entrySet()) {
            Point p = entry.getKey();
            Cel cel = entry.getValue();

            // Bepaal de kleur op basis van het type
            // Hier maken we het onderscheid tussen de ConwayCel en de rest
            if (cel instanceof ConwayCel) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.CYAN); // Een duidelijke kleur voor je Alternatieve cel
            }

            // Teken de cel
            g.fillRect(p.x * CELL_SIZE, p.y * CELL_SIZE, CELL_SIZE - 1, CELL_SIZE - 1);
        }
    }

    public int getCellSize() {
        return CELL_SIZE;
    }
}