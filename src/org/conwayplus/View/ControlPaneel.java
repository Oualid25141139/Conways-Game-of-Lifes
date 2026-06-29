package org.conwayplus.View;

import org.conwayplus.Model.PatroonType;
import javax.swing.*;
import java.awt.FlowLayout;

public class ControlPaneel extends JPanel {
    private final JButton startButton = new JButton("Start");
    private final JButton stopButton = new JButton("Stop");
    private final JButton clearButton = new JButton("Clear");
    private final JButton versnelButton = new JButton("Snelheid +");
    private final JButton verlangzaamButton = new JButton("Snelheid -");

    // De dropdown en laadknop voor de Factory
    private final JComboBox<PatroonType> patroonDropdown = new JComboBox<>(PatroonType.values());
    private final JButton laadButton = new JButton("Laad Patroon");

    private final JLabel tickLabel = new JLabel("Ticks: 0");

    public ControlPaneel() {
        setLayout(new FlowLayout());
        add(startButton);
        add(stopButton);
        add(clearButton);
        add(versnelButton);
        add(verlangzaamButton);

        // Dropdown sectie
        add(new JLabel(" Kies:"));
        add(patroonDropdown);
        add(laadButton);

        add(tickLabel);
    }

    public void setTickCount(int count) { tickLabel.setText("Ticks: " + count); }

    // Getters voor de Controller
    public JButton getStartButton() { return startButton; }
    public JButton getStopButton() { return stopButton; }
    public JButton getClearButton() { return clearButton; }
    public JButton getVersnelButton() { return versnelButton; }
    public JButton getVerlangzaamButton() { return verlangzaamButton; }
    public JComboBox<PatroonType> getPatroonDropdown() { return patroonDropdown; }
    public JButton getLaadButton() { return laadButton; }
}