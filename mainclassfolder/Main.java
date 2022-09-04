package mainclassfolder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.UnsupportedLookAndFeelException;

import modules.methods.ComponentAttribute;
import modules.workenvironment.Component;
import modules.workenvironment.Port;
import modules.workenvironment.WorkEnvironmentMain;
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        System.setProperty("file.encoding","UTF-8");
        //System.setProperty("file.encoding","CP-1251");
        workenvironment = new WorkEnvironmentMain();
        Component c = new Component(new ArrayList<Port<?>>(Collections.emptyList()), new ArrayList<ComponentAttribute>(Collections.emptyList()), "", Component.DEFAULT_ICON, new Component.GraphicData());
        Component.handleportdata(new ArrayList<Port<?>>(Collections.emptyList()), c::f, 0, 10);
    }
    public static WorkEnvironmentMain workenvironment;
    public static final String DefaultProjectName = "Новый проект";
    public static String projectname;
}
//для оптимизации методов необходимо заменить .indexOf(...) == -1 на .contains(...)
//везде заменить int на Integer