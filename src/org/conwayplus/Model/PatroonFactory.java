package org.conwayplus.Model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PatroonFactory {

    /**
     * Genereert een set cellen op basis van het gekozen patroon.
     * De return-waarde is nu Map<Point, Cel> in plaats van CelType.
     */
    public Map<Point, Cel> creeerPatroon(PatroonType type, int breedte, int hoogte) {
        Map<Point, Cel> cellen = new HashMap<>();

        switch (type) {
            case GLIDER:
                // Een Glider is een vormpje dat diagonaal over het scherm wandelt
                cellen.put(new Point(1, 0), new ConwayCel(5, 0));
                cellen.put(new Point(2, 1), new ConwayCel(6, 1));
                cellen.put(new Point(0, 2), new ConwayCel(0, 8));
                cellen.put(new Point(1, 2), new ConwayCel(5, 2));
                cellen.put(new Point(2, 2), new ConwayCel(6, 2));
                break;

            case BLINKER:
                // Een Blinker is een simpel staafje van 3 dat constant kantelt
                cellen.put(new Point(5, 4), new ConwayCel(5, 4));
                cellen.put(new Point(5, 5), new ConwayCel(5, 5));
                cellen.put(new Point(5, 6), new ConwayCel(5, 6));
                break;

            case RANDOM:
                // Vult het bord voor 30% met willekeurige Conway of Alternatieve cellen
                Random rand = new Random();
                for (int x = 0; x < breedte; x++) {
                    for (int y = 0; y < hoogte; y++) {
                        if (rand.nextDouble() < 0.3) {
                            // Kies willekeurig tussen ConwayCel of AlternatieveCel
                            if (rand.nextBoolean()) {
                                cellen.put(new Point(x, y), new ConwayCel(x, y));
                            } else {
                                cellen.put(new Point(x, y), new AlternatieveCel(x, y));
                            }
                        }
                    }
                }
                break;
        }
        return cellen;
    }
}