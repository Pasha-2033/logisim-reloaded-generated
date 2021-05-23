package modules.gui;

import javax.swing.*;

public class MainAppWindow extends JFrame {
  public MainAppWindow(int x, int y, int width, int height){
    super("Logisim Reloaded"); //Заголовок окна
    setBounds(x, y, width, height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setJMenuBar(Menu.upmenu());
  }
}