package modules.gui;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import modules.languages.language;

public class FileChosserWindow {
    public FileChosserWindow(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(language.trnslt("CIRC Files"), "circ");
        chooser.setFileFilter(filter);
        if(chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
           //System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
        }
    }
}
