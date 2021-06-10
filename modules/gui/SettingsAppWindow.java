package modules.gui;
import javax.swing.*;
public class SettingsAppWindow extends JFrame {
  public SettingsAppWindow(int x, int y, int width, int height){
    super("Settings"); //Заголовок окна
    setBounds(x, y, width, height);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setIconImage(new ImageIcon("resourses/programicon.png").getImage());
    setJMenuBar(Menu.settingsmenubar());
  }
}
