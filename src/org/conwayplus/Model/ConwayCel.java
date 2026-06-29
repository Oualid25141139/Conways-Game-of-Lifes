package org.conwayplus.Model;

// ConwayCel implementeert de standaardregels zoals bedacht door John Conway.
// Het erft van de abstracte klasse 'Cel'.
public class ConwayCel extends Cel {

    // Constructor die de positie van de cel doorgeeft aan de superklasse.
    public ConwayCel(int x, int y) {
        super(x, y);
    }

    // Bepaalt of de cel overleeft in de volgende generatie.
    // Volgens de regels overleeft een cel met precies 2 of 3 buren.
    @Override
    public boolean moetOverleven(int buren) {
        return buren == 2 || buren == 3;
    }

    // Bepaalt of er een nieuwe cel geboren wordt op een lege plek.
    // Volgens de regels gebeurt dit alleen bij precies 3 buren.
    @Override
    public boolean moetGeborenWorden(int buren) {
        return buren == 3;
    }
}