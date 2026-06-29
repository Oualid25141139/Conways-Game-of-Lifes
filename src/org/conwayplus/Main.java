package org.conwayplus;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // SwingUtilities zorgt ervoor dat het venster op de juiste manier
        // door het systeem wordt aangemaakt (thread-safe).
        // Het start de GameLauncher om de rest van het spel op te bouwen.
        SwingUtilities.invokeLater(() -> new GameLauncher().start());
    }
}