package org.conwayplus.Model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;


 // De Wereld klasse fungeert als de centrale datastructuur (Model) van de simulatie.
 // Het beheert de verzameling van alle momenteel levende cellen en hun posities.

public class Wereld {

    // Een Map koppelt een coördinaat (Point) aan een Cel-object voor efficiënte lookup.
    private Map<Point, Cel> levendeCellen = new HashMap<>();


     // Voegt een nieuwe cel toe aan de wereld op basis van het type.
     // Gebruikt door de InputController bij muisKlikken.
    public void plaatsCel(int x, int y, CelType type) {
        Point p = new Point(x, y);
        switch (type) {
            case CONWAY:
                levendeCellen.put(p, new ConwayCel(x, y));
                break;
            case ALTERNATIEF:
                levendeCellen.put(p, new AlternatieveCel(x, y));
                break;
        }
    }


     // Voegt een bestaand cel-object toe.
     // Wordt gebruikt door de PatroonFactory om complexe structuren te laden.
    public void plaatsCel(Point p, Cel cel) {
        levendeCellen.put(p, cel);
    }


     //Verwijdert een cel op de opgegeven coördinaten.
    public void verwijderCel(int x, int y) {
        levendeCellen.remove(new Point(x, y));
    }

     // Controleert of er op de opgegeven locatie een cel aanwezig is.
    public boolean isLevend(int x, int y) {
        return levendeCellen.containsKey(new Point(x, y));
    }



     // Geeft een kopie van de map met levende cellen terug.
     // Het retourneren van een nieuwe HashMap (defensive copy) voorkomt
     // dat externe klassen de interne structuur onbedoeld aanpassen.
    public Map<Point, Cel> getLevendeCellenMap() {
        return new HashMap<>(levendeCellen);
    }


     // Overschrijft de huidige populatie met een nieuwe generatie cellen.
    public void setLevendeCellen(Map<Point, Cel> nieuweCellen) {
        this.levendeCellen = new HashMap<>(nieuweCellen);
    }


     // Leegt de volledige wereld.
    public void clear() {
        levendeCellen.clear();
    }
}