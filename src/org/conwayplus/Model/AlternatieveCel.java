package org.conwayplus.Model;

// De AlternatieveCel is een aangepaste variant van een cel met eigen overlevingsregels.
// Dit laat zien hoe je het gedrag van cellen kunt variëren zonder de engine aan te passen.
public class AlternatieveCel extends Cel {

    // Constructor die de positie van de cel doorgeeft aan de superklasse.
    public AlternatieveCel(int x, int y) {
        super(x, y);
    }

    // Specifieke overlevingsregel voor deze cel:
    // Deze cel is robuuster en overleeft met 2, 3 of 4 buren.
    @Override
    public boolean moetOverleven(int buren) {
        return buren >= 2 && buren <= 4;
    }

    // Bepaalt of er een nieuwe cel geboren wordt.
    // Deze cel volgt dezelfde geboorteregel als de standaard Conway cel.
    @Override
    public boolean moetGeborenWorden(int buren) {
        return buren == 3;
    }
}