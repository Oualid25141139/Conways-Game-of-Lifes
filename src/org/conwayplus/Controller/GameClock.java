package org.conwayplus.Controller;

import javax.swing.Timer;
import java.util.ArrayList;
import java.util.List;

public class GameClock {
    private final Timer timer;
    private int delay = 500; // Startsnelheid in milliseconden
    private final List<TickListener> listeners = new ArrayList<>();

    public GameClock() {
        // De timer voert elke 'delay' milliseconden de code in de {} uit.
        // Hij vertelt aan alle 'listeners' (zoals de TickVerwerker) dat het tijd is voor een nieuwe stap.
        this.timer = new Timer(delay, e -> {
            for (TickListener listener : listeners) {
                listener.verwerkTick();
            }
        });
        this.timer.setRepeats(true); // De timer moet blijven lopen
    }

    // Hiermee kan je objecten toevoegen die een seintje willen krijgen bij elke 'tik'.
    public void addTickListener(TickListener listener) {
        listeners.add(listener);
    }

    public void start() {
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    public void stop() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }

    // Maakt de tijd tussen tikken korter (spel gaat sneller).
    public void versnel() {
        if (delay > 100) {
            delay -= 50;
            timer.setDelay(delay);
        }
    }

    // Maakt de tijd tussen tikken langer (spel gaat langzamer).
    public void verlangzaam() {
        if (delay < 2000) {
            delay += 50;
            timer.setDelay(delay);
        }
    }

    // Handig voor tests om te checken of de snelheid goed wordt aangepast.
    public int getDelay() {
        return delay;
    }
}