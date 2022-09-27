package me.maploop.jsonconverter.ui.famework;

import me.maploop.jsonconverter.JSONConverter;

import javax.swing.*;
import java.awt.*;

public class GuiComboBox extends JComboBox<String>
{
    public GuiComboBox() {
        super();
        setBorder(null);
    }

    public GuiComboBox(String[] items) {
        super(items);
    }

    public GuiComboBox(int x, int y, int width, int height, float fontSize, boolean handCursor, String... options) {
        this.setBounds(x, y, width, height);
        this.setBorder(null);
        this.setBackground(JSONConverter.defaultBackground);
        this.setOpaque(false);
        this.setFont(new Font("DEF", Font.PLAIN, (int) (fontSize * 1.5)));

        if (handCursor)
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        for (String option : options) {
            this.addItem(option);
        }
        initHover();
    }

    private void initHover() {
    }
}
