package org.conwayplus.Model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SimulatieEngineTest {
    @Test
    void testEvolutieBlinker() {
        Wereld w = new Wereld();
        SimulatieEngine engine = new SimulatieEngine();

        // Zet een verticaal staafje neer
        w.plaatsCel(new Point(5, 4), new ConwayCel(5, 4));
        w.plaatsCel(new Point(5, 5), new ConwayCel(5, 5));
        w.plaatsCel(new Point(5, 6), new ConwayCel(5, 6));

        engine.evolueer(w);

        // Na evolutie moet het staafje horizontaal liggen (4,5), (5,5), (6,5)
        assertTrue(w.isLevend(5, 5)); // Middelpunt blijft
        assertTrue(w.isLevend(4, 5));
        assertTrue(w.isLevend(6, 5));
    }
}