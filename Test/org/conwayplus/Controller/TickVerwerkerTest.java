package org.conwayplus.Controller;

import org.conwayplus.Model.Wereld;
import org.conwayplus.View.ControlPaneel;
import org.conwayplus.View.GridPaneel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TickVerwerkerTest {

    private TickVerwerker tickVerwerker;
    private Wereld mockWereld;
    private GridPaneel mockGrid;
    private ControlPaneel mockControl;

    @BeforeEach
    public void setup() {
        // We maken 'nep' versies van de onderdelen zodat we ze kunnen testen
        mockWereld = mock(Wereld.class);
        mockGrid = mock(GridPaneel.class);
        mockControl = mock(ControlPaneel.class);

        // Initialiseer de verwerker
        tickVerwerker = new TickVerwerker(mockWereld, mockGrid);

        // TIP: Injecteer hier je controlPaneel via een setter als je dat in de klasse aanpast,
        // anders kan je deze mock niet testen.
    }

    @Test
    public void testVerwerkTickVerhoogtTeller() {
        // 1. ARRANGE: Zet de situatie klaar
        // (De teller staat standaard op 0)

        // 2. ACT: Voer de actie uit die je wilt testen
        tickVerwerker.verwerkTick();

        // 3. ASSERT: Controleer of het resultaat klopt
        assertEquals(1, tickVerwerker.getTickTeller(), "De teller zou na 1 tick op 1 moeten staan.");

        // Controleer of de engine ook echt is aangeroepen
        verify(mockGrid, times(1)).repaint();
    }

    @Test
    public void testResetTicksZetTellerOpNul() {
        // 1. ARRANGE
        tickVerwerker.verwerkTick();
        tickVerwerker.verwerkTick();
        assertEquals(2, tickVerwerker.getTickTeller());

        // 2. ACT
        tickVerwerker.resetTicks();

        // 3. ASSERT
        assertEquals(0, tickVerwerker.getTickTeller(), "Na een reset moet de teller weer op 0 staan.");
    }
}