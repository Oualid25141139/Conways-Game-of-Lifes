package org.conwayplus.Model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;


 // De SimulatieEngine bevat de logica voor de evolutie van de wereld.
 // Het verantwoordelijkheid is het bepalen van de volgende generatie cellen
 //op basis van de huidige staat en de spelregels.

public class SimulatieEngine {


     // Berekent de volgende generatie van de wereld.
     // Gebruikt een 'burenTeller' om efficiënt te bepalen welke cellen
     // overleven en waar nieuwe cellen ontstaan.

    public void evolueer(Wereld wereld) {
        Map<Point, Cel> huidigeCellen = wereld.getLevendeCellenMap();
        Map<Point, Cel> volgendeGeneratie = new HashMap<>();
        Map<Point, Integer> burenTeller = new HashMap<>();

        // 1. Evalueer alle levende cellen: Overleven ze?
        for (Map.Entry<Point, Cel> entry : huidigeCellen.entrySet()) {
            Point p = entry.getKey();
            Cel cel = entry.getValue();
            int buren = telBuren(wereld, p.x, p.y);

            // De cel beslist zelf op basis van zijn eigen regels of hij overleeft.
            // Dit is een mooi voorbeeld van polymorfisme.
            if (cel.moetOverleven(buren)) {
                volgendeGeneratie.put(p, cel);
            }

            // Verzamel buren rondom deze levende cel voor de geboorte-check.
            updateBurenTeller(burenTeller, p);
        }

        // 2. Evalueer geboortes op lege plekken
        for (Map.Entry<Point, Integer> entry : burenTeller.entrySet()) {
            Point p = entry.getKey();
            int buren = entry.getValue();

            // Als de plek nog leeg is, check of hier een cel moet ontstaan.
            if (!wereld.isLevend(p.x, p.y)) {
                // Hier wordt momenteel standaard een ConwayCel aangemaakt bij geboorte.
                Cel nieuwePotentieleCel = new ConwayCel(p.x, p.y);

                if (nieuwePotentieleCel.moetGeborenWorden(buren)) {
                    volgendeGeneratie.put(p, nieuwePotentieleCel);
                }
            }
        }

        // Update de wereld met de nieuwe generatie.
        wereld.setLevendeCellen(volgendeGeneratie);
    }


     // Verhoogt de teller voor alle 8 buurposities rondom een gegeven punt.

    private void updateBurenTeller(Map<Point, Integer> teller, Point p) {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue; // Skip de cel zelf
                Point buur = new Point(p.x + x, p.y + y);
                teller.put(buur, teller.getOrDefault(buur, 0) + 1);
            }
        }
    }


     //Telt het aantal levende buren voor een specifieke cel.

    private int telBuren(Wereld wereld, int celX, int celY) {
        int aantalBuren = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;
                if (wereld.isLevend(celX + x, celY + y)) aantalBuren++;
            }
        }
        return aantalBuren;
    }
}