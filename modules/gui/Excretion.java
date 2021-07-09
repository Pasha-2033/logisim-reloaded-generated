package modules.gui;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
import modules.workenvironment.Component;
public class Excretion extends JPanel {
    private List<Component> excretedcomponents = new ArrayList<Component>(Collections.emptyList());
    public Excretion(){
        setOpaque(false);
    }
}