package modules.methods;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import modules.workenvironment.Component;
public class treenoderenderer implements TreeCellRenderer {
    private JLabel label = new JLabel();
    @Override
    public java.awt.Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        try {
            Component component = (Component) node.getUserObject();
            if (node.isLeaf()){
                label.setIcon(component.componenticon);
                label.setText(component.componentname);
            } else {
                label.setIcon(new ImageIcon("resourses/menuicon/foldericon.png")); //указать иконку папки
            }
        } catch (Exception e){
            label.setText(node.toString());
            label.setIcon(new ImageIcon("resourses/menuicon/foldericon.png")); //указать иконку папки
        }
        label.setOpaque(false);
        return label;
    }
}