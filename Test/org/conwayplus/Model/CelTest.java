package org.conwayplus.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CelTest {
    @Test
    void testConwayRegels() {
        Cel c = new ConwayCel(0, 0);
        // Conway: moet overleven bij 2 of 3 buren
        assertTrue(c.moetOverleven(2));
        assertTrue(c.moetOverleven(3));
        assertFalse(c.moetOverleven(1));
    }

    @Test
    void testAlternatieveRegels() {
        Cel c = new AlternatieveCel(0, 0);
        // Alternatief: stel dat deze bij 2 t/m 4 overleeft
        assertTrue(c.moetOverleven(4));
        assertFalse(c.moetOverleven(5));
    }
}