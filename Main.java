import javax.swing.UnsupportedLookAndFeelException;
import modules.gui.MainAppWindow;
import modules.workenvironment.WorkEnvironmentMain;
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        frame = new MainAppWindow(200, 200, 800, 600);
        workenvironment = new WorkEnvironmentMain();
        workenvironment.graphics = frame.getGraphics();
    }
    public static WorkEnvironmentMain workenvironment;
    public static MainAppWindow frame;
}