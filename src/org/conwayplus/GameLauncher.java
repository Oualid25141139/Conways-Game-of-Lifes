package org.conwayplus;

import org.conwayplus.Controller.GameClock;
import org.conwayplus.Controller.InputController;
import org.conwayplus.Controller.TickVerwerker;
import org.conwayplus.Model.Wereld;
import org.conwayplus.View.ControlPaneel;
import org.conwayplus.View.GridPaneel;
import org.conwayplus.View.Hoofdscherm;

public class GameLauncher {

    public void start() {
        // 1. Bouw de data-laag (Model)
        // De game start nu standaard leeg, de speler kan zelf via de UI een patroon laden
        Wereld wereld = new Wereld();

        // 2. Bouw de grafische interface (View)
        GridPaneel gridPaneel = new GridPaneel(wereld);
        ControlPaneel controlPaneel = new ControlPaneel();
        Hoofdscherm hoofdscherm = new Hoofdscherm(gridPaneel, controlPaneel);

        // 3. Bouw de motoren van het spel (Controller)
        GameClock clock = new GameClock();
        TickVerwerker verwerker = new TickVerwerker(wereld, gridPaneel);

        // 4. Bouw de knoppenkoppelaar en geef hem alle benodigde onderdelen mee (Dependency Injection)
        InputController inputController = new InputController(wereld, gridPaneel, controlPaneel, clock, verwerker);

        // 5. Verbind de systemen met elkaar en start de boel op
        clock.addTickListener(verwerker);          // Schrijf de verwerker in op de klok (Observer Pattern)
        inputController.activeerKoppelingen();     // Zet alle muis- en knopacties 'scherp'

        hoofdscherm.toon();                        // Open het venster voor de gebruiker
    }
}