package modules.methods.Listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import mainclassfolder.Main;
import modules.workenvironment.Component;
import modules.workenvironment.ComponentShadow;
import modules.workenvironment.WorkEnvironmentMain;
public class ComponentTreeListener extends MouseAdapter{
    private JTree tree;
    public ComponentTreeListener(JTree tree){
        this.tree = tree;
    }
    public final void updatetree(JTree tree){
        this.tree = tree;
    }
    public void mouseClicked (MouseEvent e){
        WorkEnvironmentMain.ShadowedComponents = new ArrayList<ComponentShadow>(Collections.emptyList());
        WorkEnvironmentMain.movingcomponentframe.removeAll();
        WorkEnvironmentMain.excretion.removeAllExcretedComponents();
        WorkEnvironmentMain.excretion.removeExcretion();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (node == null) return;
        if (node.getUserObject() instanceof Component){
            Component component = (Component) node.getUserObject();
            if (e.getClickCount() == 1 && WorkEnvironmentMain.newone.getComponentCount() == 0) {
                WorkEnvironmentMain.newComponent = component;
                WorkEnvironmentMain.newComponentShadow = new ComponentShadow(WorkEnvironmentMain.newComponent);
                WorkEnvironmentMain.newComponentShadow.setComponentBase(0, 0);
                WorkEnvironmentMain.newComponentShadow.setComponentLocation(MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y);
                WorkEnvironmentMain.newone.add(WorkEnvironmentMain.newComponentShadow);
            } else if (e.getClickCount() == 1 && WorkEnvironmentMain.newone.getComponentCount() == 1){
                WorkEnvironmentMain.newComponent = component;
                WorkEnvironmentMain.newComponentShadow = new ComponentShadow(WorkEnvironmentMain.newComponent);
                WorkEnvironmentMain.newComponentShadow.setComponentBase(0, 0);
                WorkEnvironmentMain.newone.removeAll();
                WorkEnvironmentMain.newone.add(WorkEnvironmentMain.newComponentShadow);
            } else if (e.getClickCount() == 2 && component.getisSircut()){
                WorkEnvironmentMain.currentSircut = component;
                Main.workenvironment.refreshWorkplace();
            }
        }
    }
}