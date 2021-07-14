package modules.methods;
import java.awt.event.*;
import javax.swing.*;
import modules.languages.Language;
import modules.gui.FileChosserWindow;
import modules.gui.SettingsAppWindow;
public class GUIMethod {
    public static class exit extends AbstractAction {
        public exit(){putValue(NAME, Language.trnslt("Exit"));}
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    public static class settings extends AbstractAction {
        public settings(){putValue(NAME, Language.trnslt("Settings") + "...");}
        public void actionPerformed(ActionEvent e) {
            SettingsAppWindow settings = new SettingsAppWindow(0, 0, 400, 300);
            settings.setVisible(true);
        }
    }
    public static class open extends AbstractAction {
        public open(){putValue(NAME, Language.trnslt("Open") + "...");}
        public void actionPerformed(ActionEvent e) {
            new FileChosserWindow();
        }
    }
    public static class newfile extends AbstractAction {
        public newfile(){putValue(NAME, Language.trnslt("New") + "...");}
        public void actionPerformed(ActionEvent e) {
            //
        }
    }
    public static class close extends AbstractAction {
        public close(){putValue(NAME, Language.trnslt("Close"));}
        public void actionPerformed(ActionEvent e) {
            //
        }
    }
    public static class openrecent extends AbstractAction {
        public openrecent(String name){
            putValue(NAME, name);
            if (name != "Openrecent"){
                //
            } else {
                //
            }
        }
        public void actionPerformed(ActionEvent e) {
            //
        }
    }
    public static class save extends AbstractAction {
        public save(){putValue(NAME, Language.trnslt("Save")); /*do*/}
        public void actionPerformed(ActionEvent e) {
            //
        }
    }
    public static class saveas extends AbstractAction {
        public saveas(){putValue(NAME, Language.trnslt("Saveas") + "..."); /*do*/}
        public void actionPerformed(ActionEvent e) {
            //
        }
    }
    public static class exportimage extends AbstractAction {
        public exportimage(){putValue(NAME, Language.trnslt("Exportimage") + "..."); /*do*/}
        public void actionPerformed(ActionEvent e) {
            //
        }
    }
    public static class print extends AbstractAction {
        public print(){putValue(NAME, Language.trnslt("Print") + "..."); /*do*/}
        public void actionPerformed(ActionEvent e) {
            //
        }
    }
}