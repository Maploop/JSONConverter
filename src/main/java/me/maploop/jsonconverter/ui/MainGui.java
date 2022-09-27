package me.maploop.jsonconverter.ui;

import javafx.stage.FileChooser;
import me.maploop.jsonconverter.JSONConverter;
import me.maploop.jsonconverter.ui.famework.*;
import me.maploop.jsonconverter.util.Dialogs;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;

public class MainGui extends GuiScreen
{
    private GuiLabel questionTitleLabel;
    private GuiInputBox titleInput;
    private GuiInputBox answerInput;
    private GuiLabel questionAnswerLabel;
    private JCheckBox alignmentRight;
    private GuiComboBox difficulty;
    private GuiButton save;

    public MainGui() {
        super("JSON Question Converter", 800, 500);
        setResizable(false);
        this.setBackgroundColour(JSONConverter.defaultAppBackground);
        setLayout(new BorderLayout(0, 0));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void load() {
        questionTitleLabel = new GuiLabel("Soorat Soal", 10, 20, 200, 30, 14, false, false);
        questionTitleLabel.setDefaultColor(Color.white);
        add(questionTitleLabel);

        titleInput = new GuiInputBox("Enter text here", 10, 60, 500, 50, 14, false);
        add(titleInput);

        alignmentRight = new JCheckBox("Rast Chin Kardan Matn");
        alignmentRight.setBounds(520, 60, 300, 30);
        alignmentRight.setFont(JSONConverter.defaultFont.deriveFont(Font.PLAIN, 14));
        alignmentRight.setForeground(Color.white);
        alignmentRight.setBackground(JSONConverter.defaultAppBackground);

        alignmentRight.addActionListener((al) -> {
            if (alignmentRight.isSelected()) {
                StyledDocument doc = titleInput.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);

                StyledDocument doc1 = answerInput.getStyledDocument();
                SimpleAttributeSet center1 = new SimpleAttributeSet();
                StyleConstants.setAlignment(center1, StyleConstants.ALIGN_RIGHT);
                doc1.setParagraphAttributes(0, doc1.getLength(), center1, false);
            } else {
                StyledDocument doc = titleInput.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);

                StyledDocument doc1 = answerInput.getStyledDocument();
                SimpleAttributeSet center1 = new SimpleAttributeSet();
                StyleConstants.setAlignment(center1, StyleConstants.ALIGN_LEFT);
                doc1.setParagraphAttributes(0, doc1.getLength(), center1, false);
            }
        });
        add(alignmentRight);

        questionAnswerLabel = new GuiLabel("Javab Soal", 10, 140, 300, 30, 14, false, false);
        questionAnswerLabel.setDefaultColor(Color.white);
        add(questionAnswerLabel);

        answerInput = new GuiInputBox("Enter text here", 10, 180, 500, 50, 14, false);
        add(answerInput);

        GuiLabel difLb = new GuiLabel("Sakhti Soal", 10, 250, 300, 30, 14, false, false);
        difLb.setDefaultColor(Color.white);
        add(difLb);

        difficulty = new GuiComboBox(10, 290, 300, 30, 14, true, "", "Asan", "Motvaset", "Doshvar");
        add(difficulty);

        save = new GuiButton("Zakhire", 10, 340, 200, 50, 14, true);
        add(save);

        save.addActionListener((e) -> {
            if (titleInput.getText().equals("")) {
                titleInput.setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(this, "Lotfan field haye highlight shode ro por konid!", "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (answerInput.getText().equals("")) {
                answerInput.setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(this, "Lotfan field haye highlight shode ro por konid!", "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (difficulty.getSelectedIndex() == 0) {
                difficulty.setBorder(BorderFactory.createLineBorder(Color.red));
                JOptionPane.showMessageDialog(this, "Lotfan field haye highlight shode ro por konid!", "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String title = titleInput.getText();
            String answer = answerInput.getText();
            String difficultyS = "EASY";
            switch (difficulty.getSelectedItem().toString()) {
                case "Asan":
                    difficultyS = "EASY";
                    break;
                case "Motevaset":
                    difficultyS = "MEDIUM";
                    break;
                case "Doshvar":
                    difficultyS = "HARD";
                    break;
            }

            JSONObject object = new JSONObject();
            object.put("title", title);
            object.put("answer", answer);
            object.put("difficulty", difficultyS);

            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (!file.getPath().endsWith(".json"))
                    file = new File(file.getPath() + ".json");

                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(object.toString());
                    writer.flush();

                    JOptionPane.showMessageDialog(this, "Soal shoma ba movafaghiyat dar file \"" + file.getPath() + "\" zakhire shod!", "Success!"
                            , JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    Dialogs.showExceptionMessage(this, ex);
                }
            }
        });
    }
}
