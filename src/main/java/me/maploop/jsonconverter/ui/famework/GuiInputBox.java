package me.maploop.jsonconverter.ui.famework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class GuiInputBox extends JTextPane
{
    private JScrollPane scrollPane;
    private String placeholder;
    private boolean required;

    public GuiInputBox(String ghostText, int x, int y, int width, int height, float fontSize, boolean handCursor) {
        this(ghostText, null, x, y, width, height, fontSize, handCursor);
    }

    public GuiInputBox(String placeholder, JComponent parent, int x, int y, int width, int height, float fontSize, boolean handCursor) {
        this.setBounds(x, y, width, height);
        this.setBackground(Color.darkGray);
        this.setForeground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setOpaque(true);
        this.setFont(new Font("DEF", Font.PLAIN, (int) (fontSize * 1.5)));
        this.placeholder = placeholder;

        this.scrollPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.setBounds(x + (width - 20), y, 20, height);
        this.scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
        this.scrollPane.setOpaque(true);
        this.scrollPane.setBackground(Color.darkGray);
        this.scrollPane.setForeground(Color.white);
        this.scrollPane.setPreferredSize(new Dimension(width, height));

        if (handCursor)
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (parent != null)
            parent.add(scrollPane);

        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
    }

    public void setKeyListener(String key, Consumer<ActionEvent> action) {
        this.getKeymap().addActionForKeyStroke(KeyStroke.getKeyStroke(key), new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.accept(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
                .getMaxAscent() + getInsets().top);
    }
}
