import javax.swing.UnsupportedLookAndFeelException;
import modules.gui.MainAppWindow;
import modules.workenvironment.WorkEnvironmentMain;
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        workenvironment = new WorkEnvironmentMain(new MainAppWindow(0, 0, 800, 600));
    }
    public static WorkEnvironmentMain workenvironment;
}