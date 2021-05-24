package modules.methods;

import java.awt.event.*;
import javax.swing.*;
import modules.languages.language;
import modules.gui.SettingsAppWindow;

public class guimethod {
    public guimethod(){
        super();
    }
    public static class exit extends AbstractAction {
        public exit(){
            putValue(NAME, language.trnslt("Exit"));
        }
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    public static class settings extends AbstractAction {
        public settings(){
            putValue(NAME, language.trnslt("Settings"));
        }
        public void actionPerformed(ActionEvent e) {
            SettingsAppWindow settings = new SettingsAppWindow(0, 0, 400, 300);
            settings.setVisible(true);
        }
    }
}