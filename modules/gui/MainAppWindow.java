package modules.gui;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
public class MainAppWindow extends JFrame {
    public MainAppWindow(int x, int y, int width, int height) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
        super("Logisim Reloaded");
        setBounds(x, y, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("resources/programicon.png").getImage());
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        setJMenuBar(Menu.upmenubar());
        setVisible(true);
    }
}