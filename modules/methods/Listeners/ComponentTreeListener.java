package modules.methods.Listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import mainclassfolder.Main;
import modules.workenvironment.Component;
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
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (node == null) return;
        if (node.getUserObject() instanceof Component){
            Component component = (Component) node.getUserObject();
            if (e.getClickCount() == 1) {
                //дописать добавление компонента (определить тень)
                WorkEnvironmentMain.currentSircut.addintercomponentsandsircuts(component);
                WorkEnvironmentMain.currentSircut.checkisSicut();
                //прописать x, y
                //component.setComponentLocation(x, y);
            } else if (e.getClickCount() == 2 && component.getisSircut()){
                WorkEnvironmentMain.currentSircut = component;
                Main.workenvironment.refreshWorkplace();
            }
        }
    }
}