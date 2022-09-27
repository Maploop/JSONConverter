package me.maploop.jsonconverter.util;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Dialogs
{
    public static void showExceptionMessage(Component parentComponent,
                                            Exception exception) throws HeadlessException {

        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));

        JLabel message = new JLabel("An error occurred while trying to launch Atlas Admin Terminal. Please report this issue.");
        message.setBorder(BorderFactory.createEmptyBorder(3, 0, 10, 0));

        JTextArea text = new JTextArea();
        text.setEditable(false);
        text.setFont(UIManager.getFont("Label.font"));
        text.setText(stringWriter.toString());
        text.setCaretPosition(0);

        JScrollPane scroller = new JScrollPane(text);
        scroller.setPreferredSize(new Dimension(400, 200));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(message, BorderLayout.NORTH);
        panel.add(scroller, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(parentComponent, panel, "Unexpected Error",
                JOptionPane.ERROR_MESSAGE);

    }
}
