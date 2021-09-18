package mainclassfolder;
import javax.swing.UnsupportedLookAndFeelException;
import modules.workenvironment.WorkEnvironmentMain;
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        workenvironment = new WorkEnvironmentMain();
    }
    public static WorkEnvironmentMain workenvironment;
}
//для оптимизации методов необходимо заменить .indexOf(...) == -1 на .contains(...)
//везде заменить int на Integer