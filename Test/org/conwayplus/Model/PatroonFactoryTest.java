package org.conwayplus.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import java.awt.Point;

public class PatroonFactoryTest {

    @Test
    public void testPatroonGeneratie() {
        PatroonFactory factory = new PatroonFactory();

        // Test Glider
        Map<Point, Cel> glider = factory.creeerPatroon(PatroonType.GLIDER, 10, 10);
        assertFalse(glider.isEmpty(), "Glider mag niet leeg zijn.");

        // Test Random
        Map<Point, Cel> random = factory.creeerPatroon(PatroonType.RANDOM, 10, 10);
        assertTrue(random.size() > 0, "Random patroon moet cellen bevatten.");
    }
}