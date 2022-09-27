package me.maploop.jsonconverter;

import me.maploop.jsonconverter.ui.MainGui;
import me.maploop.jsonconverter.ui.famework.GuiScreen;
import me.maploop.jsonconverter.util.ALog;
import me.maploop.jsonconverter.util.Dialogs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JSONConverter
{
    public static Color defaultBackground = new Color(64, 78, 237);
    public static Color defaultForeground = new Color(88, 101, 242);
    public static Color defaultAppBackground = new Color(44, 47, 51);
    public static Font defaultFont = null;

    private static JSONConverter atlasAdminTerminal;
    public static final List<GuiScreen> openGuiScreens = new ArrayList<>();

    private GuiScreen currentGuiScreen;

    public GuiScreen getCurrentGuiScreen() {
        return currentGuiScreen;
    }

    public static JSONConverter getInstance() {
        return atlasAdminTerminal;
    }

    public void displayGuiScreen(GuiScreen screen) {
        screen.display();
        this.currentGuiScreen = screen;
        openGuiScreens.add(screen);
    }

    public JSONConverter() {
        atlasAdminTerminal = this;
        ALog.info("Starting up...");

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ALog.severe("An error occurred white setting up the look and feel.");
            Dialogs.showExceptionMessage(null, ex);
        }
        try {
            defaultFont = Font.createFont(Font.TRUETYPE_FONT, JSONConverter.class.getClassLoader().getResourceAsStream("Girard Sky.ttf"));
        } catch (Exception e) {
            Dialogs.showExceptionMessage(null, e);
            System.exit(0);
        }

        ALog.info("Atlas Admin Terminal post-load setup done.");

        displayGuiScreen(new MainGui());
    }

    public static void main(String[] args) {
        new JSONConverter();
    }
}
