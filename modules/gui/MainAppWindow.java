package modules.gui;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
//import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
//import java.awt.GridLayout;
//import java.awt.BorderLayout;
public class MainAppWindow extends JFrame {
    public MainAppWindow(int x, int y, int width, int height) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
        super("Logisim Reloaded");
        setBounds(x, y, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("resourses/programicon.png").getImage());
        //setLayout(new GridLayout(1, 1));
        //setLayout(new BorderLayout());
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        setJMenuBar(Menu.upmenubar());
        setVisible(true);
    }
}