package modules.gui;

import javax.swing.*;
import java.awt.*;
import modules.component.draw.power;

public class MainAppWindow extends JFrame {
    public MainAppWindow(int x, int y, int width, int height){
        super("Logisim Reloaded"); //Заголовок окна
        setBounds(x, y, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("resourses/programicon.png").getImage());
        setLayout(new GridLayout(2, 2));

        setJMenuBar(Menu.upmenubar());
        
        //Graphics g = this.getGraphics();
        add(new power(15,20));
    }
}