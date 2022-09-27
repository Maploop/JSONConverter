package me.maploop.jsonconverter.ui.famework;

import me.maploop.jsonconverter.JSONConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GuiButton extends JButton
{
    private boolean disabled;
    private Color defaultColor;
    private Color fadeColor;
    private boolean mouseOver;

    public GuiButton(String text, int x, int y, int width, int height, float fontSize, boolean handCursor) {
        this(text, x, y, width, height, fontSize, handCursor, true);
    }

    public GuiButton(String text, int x, int y, int width, int height, float fontSize, boolean handCursor, boolean mouseOver) {
        super(text);

        this.setBounds(x, y, width, height);
        this.setBorderPainted(false);
        this.setBorder(null);
        this.setBackground(JSONConverter.defaultForeground);
        this.setFont(JSONConverter.defaultFont.deriveFont(Font.PLAIN, (int) (fontSize * 1.5)));
        this.mouseOver = mouseOver;

        if (handCursor)
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (mouseOver)
            initHover();
    }

    private void initHover() {
        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (disabled)
                    return;
                if (!mouseOver)
                    return;

                GuiButton.this.setBackground(GuiButton.this.fadeColor != null ? GuiButton.this.fadeColor : JSONConverter.defaultBackground);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!mouseOver)
                    return;
                GuiButton.this.setBackground(GuiButton.this.defaultColor != null ? GuiButton.this.defaultColor : JSONConverter.defaultForeground);
            }
        });
    }

    public void setDefaultColor(Color color) {
        this.defaultColor = color;
        this.setBackground(color);
    }

    public void setDefaultFadeColor(Color color) {
        this.fadeColor = color;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public GuiButton onClick(ActionListener actionListener) {
        super.addActionListener(actionListener);
        return this;
    }

    public GuiButton onMouseClick(MouseAdapter adapter) {
        super.addMouseListener(adapter);
        return this;
    }

    public void disableButton(boolean disable) {
        this.setEnabled(!disable);
        this.disabled = disable;
        this.setBackground(this.defaultColor != null ? this.defaultColor : new Color(0x47E10C));
    }

    public void setClickableIcon(ImageIcon icon) {
        this.setIcon(icon);
    }

    public void setFontColor(Color color) {
        this.setForeground(color);
    }
}
