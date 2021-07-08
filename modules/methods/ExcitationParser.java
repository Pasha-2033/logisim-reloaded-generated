package modules.methods;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modules.workenvironment.Component;
import modules.workenvironment.Port;
public class ExcitationParser {
    private static List<Component> ComponentParser = new ArrayList<Component>(Collections.emptyList());
    private static List<Port> PortParser = new ArrayList<Port>(Collections.emptyList());
    public static void startSimulation(){
        ComponentParser = new ArrayList<Component>(Collections.emptyList());
        PortParser = new ArrayList<Port>(Collections.emptyList());
    }
    public void initParser(){
        //доделать
    }
    public static void addStepedComponent(Component component){
        ComponentParser.add(component);
    }
    public static void addActivePort(Port ActivePort){
        PortParser.add(ActivePort);
    }
}