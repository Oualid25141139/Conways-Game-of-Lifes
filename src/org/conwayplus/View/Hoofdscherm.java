package org.conwayplus.View;

import javax.swing.JFrame;
import java.awt.BorderLayout;

//Hij weet hoe groot het venster is en klikt de binnenpanelen op hun plek.

public class Hoofdscherm extends JFrame {

    public Hoofdscherm(GridPaneel gridPaneel, ControlPaneel controlPaneel) {
        super("Conway Plus - Absolute SRP Edition");

        // Richt het venster in
        setLayout(new BorderLayout());
        add(gridPaneel, BorderLayout.CENTER);
        add(controlPaneel, BorderLayout.SOUTH);

        // Venster eigenschappen
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void toon() {
        setVisible(true);
    }
}