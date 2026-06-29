package org.conwayplus.Model;

public abstract class Cel {
    protected int x, y;

    public Cel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Elke cel bepaalt zelf zijn regels voor overleven en geboorte
    public abstract boolean moetOverleven(int buren);
    public abstract boolean moetGeborenWorden(int buren);
}