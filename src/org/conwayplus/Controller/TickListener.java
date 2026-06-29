package org.conwayplus.Controller;
//Een interface (contract) die ervoor zorgt
// dat iedereen die naar de klok wil luisteren, dezelfde Methode Gebruikt.
public interface TickListener {
    void verwerkTick();
}