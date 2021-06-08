package modules.gui;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.GridLayout;
import modules.workenvironment.WorkEnvironmentMain;

public class MainAppWindow extends JFrame {
    public MainAppWindow(int x, int y, int width, int height) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
        super("Logisim Reloaded"); //Заголовок окна
        setBounds(x, y, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setIconImage(new ImageIcon("resourses/programicon.png").getImage());
        setLayout(new GridLayout(2, 2));
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // если есть лаги выключи
        //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        setJMenuBar(Menu.upmenubar());
        setVisible(true);
        workenvironment = new WorkEnvironmentMain();
        workenvironment.graphics = this.getGraphics();
    }
    public WorkEnvironmentMain workenvironment;
}