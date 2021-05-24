package modules.methods;

import java.awt.event.*;
import javax.swing.*;
import modules.languages.language;

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
}