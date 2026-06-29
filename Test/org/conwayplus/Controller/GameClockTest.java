package org.conwayplus.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameClockTest {

    private GameClock clock;
    private TickListener mockListener;

    @BeforeEach
    void setUp() {
        clock = new GameClock();
        mockListener = mock(TickListener.class);
        clock.addTickListener(mockListener);
    }

    @Test
    void testStartEnStop() throws InterruptedException {
        clock.start();
        Thread.sleep(600);
        clock.stop();
        verify(mockListener, atLeastOnce()).verwerkTick();
    }

    @Test
    void testVersnelEnVerlangzaam() {
        int startDelay = clock.getDelay(); // 500

        // Test versnellen
        clock.versnel();
        assertTrue(clock.getDelay() < startDelay, "Delay zou kleiner moeten zijn na versnellen");

        // Test verlangzamen
        clock.verlangzaam();
        assertEquals(startDelay, clock.getDelay(), "Delay zou weer terug op startwaarde moeten zijn");
    }

    @Test
    void testGrenswaarden() {
        // Test of versnellen niet onder de 100ms gaat
        for(int i = 0; i < 20; i++) { clock.versnel(); } // Forceer naar minimum
        int minDelay = clock.getDelay();
        assertTrue(minDelay >= 100, "Delay mag niet onder de 100ms komen");

        clock.versnel(); // Nog een keer proberen
        assertEquals(minDelay, clock.getDelay(), "Delay mag niet verder afnemen onder 100ms");
    }
}