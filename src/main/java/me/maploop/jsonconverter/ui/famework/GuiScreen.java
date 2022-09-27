package me.maploop.jsonconverter.ui.famework;

import me.maploop.jsonconverter.JSONConverter;

import javax.swing.*;
import java.awt.*;

public abstract class GuiScreen extends JFrame
{
    private JPanel contentPane;
    private int width, height;

    public GuiScreen(String title, int width, int height)
    {
        super(title);
        this.width = width;
        this.height = height;
    }

    {
        loadDefaultComponents();

        setSize(width, height);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void display() {
        load();
        super.add(this.contentPane);
        super.pack();
        setVisible(true);
    }

    private void loadDefaultComponents() {
        this.contentPane = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(width, height);
            }
        };
        this.contentPane.setLayout(null);
    }

    // To be overriden by subclasses
    public void load() { }

    protected void add(JComponent component) {
        this.contentPane.add(component);
    }

    protected void add(JComponent component, int x, int y) {
        this.contentPane.add(component, x, y);
    }

    protected void add(JComponent component, Object constraints) {
        this.contentPane.add(component, constraints);
    }

    public void setBackgroundColour(Color colour) {
        this.contentPane.setBackground(colour);
    }

    public void close() {
        this.dispose();
    }

    public void setBorder(Color colour) {
        this.contentPane.setBorder(BorderFactory.createLineBorder(colour));
    }

    public JPanel getCustomContentPane() {
        return contentPane;
    }
}
