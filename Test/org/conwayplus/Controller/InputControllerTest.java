package org.conwayplus.Controller;

import org.conwayplus.Model.Wereld;
import org.conwayplus.View.ControlPaneel;
import org.conwayplus.View.GridPaneel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;

import static org.mockito.Mockito.*;

class InputControllerTest {

    private Wereld mockWereld;
    private ControlPaneel mockControlPaneel;
    private GridPaneel mockGridPaneel;
    private GameClock mockClock;
    private TickVerwerker mockVerwerker;
    private InputController controller;

    @BeforeEach
    void setUp() {
        // 1. Initialiseer alle mocks
        mockWereld = mock(Wereld.class);
        mockControlPaneel = mock(ControlPaneel.class);
        mockGridPaneel = mock(GridPaneel.class);
        mockClock = mock(GameClock.class);
        mockVerwerker = mock(TickVerwerker.class);

        // 2. Zorg dat ControlPaneel methodes geen null teruggeven
        // Dit voorkomt de NullPointerException
        when(mockControlPaneel.getStartButton()).thenReturn(new JButton("Start"));
        when(mockControlPaneel.getStopButton()).thenReturn(new JButton("Stop"));
        when(mockControlPaneel.getClearButton()).thenReturn(new JButton("Clear"));
        when(mockControlPaneel.getVersnelButton()).thenReturn(new JButton("Snelheid+"));
        when(mockControlPaneel.getVerlangzaamButton()).thenReturn(new JButton("Snelheid-"));
        when(mockControlPaneel.getLaadButton()).thenReturn(new JButton("Laad"));

        // Mock de ComboBox om te voorkomen dat die ook null geeft
        when(mockControlPaneel.getPatroonDropdown()).thenReturn(new JComboBox<>());

        // 3. Initialiseer de controller en activeer de listeners
        controller = new InputController(mockWereld, mockGridPaneel, mockControlPaneel, mockClock, mockVerwerker);
        controller.activeerKoppelingen();
    }

    @Test
    void testClearKnopRoeptWereldClearAan() {
        // Haal de echte knop uit de mock (die we net hebben ingesteld)
        JButton clearButton = mockControlPaneel.getClearButton();

        // Simuleer een klik op de knop
        for (ActionListener al : clearButton.getActionListeners()) {
            al.actionPerformed(null);
        }

        // Controleer of de methodes zijn aangeroepen
        verify(mockWereld, times(1)).clear();
        verify(mockGridPaneel, times(1)).repaint();
    }
}