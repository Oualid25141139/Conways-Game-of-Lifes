package org.conwayplus.Model;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WereldTest {
    @Test
    void testPlaatsenEnVerwijderen() {
        Wereld w = new Wereld();
        w.plaatsCel(new Point(1, 1), new ConwayCel(1, 1));

        assertTrue(w.isLevend(1, 1));
        w.verwijderCel(1, 1);
        assertFalse(w.isLevend(1, 1));
    }
}