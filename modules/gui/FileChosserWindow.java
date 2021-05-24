package modules.gui;

//import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

public class FileChosserWindow {
    public FileChosserWindow(){
        JFileChooser chooser = new JFileChooser();
        /*FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);*/
        int returnVal = chooser.showOpenDialog(chooser);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           System.out.println("You chose to open this file: " +
                chooser.getSelectedFile().getName());
        }
    }
}
