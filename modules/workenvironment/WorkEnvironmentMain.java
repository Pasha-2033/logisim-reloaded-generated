package modules.workenvironment;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import modules.methods.JTreeNodeRenderer;
import modules.standartcomponent.wires.ground;
import modules.standartcomponent.wires.power;
public class WorkEnvironmentMain {
    public float Scale = 1.0F;
    public Graphics graphics;
    public WorkEnvironmentMain(JFrame frame){
        //подготовка панелей
        initgui();
        //подготовка экрана
        mainframe = frame;
        mainframe.add(mainworkplace);
        mainframe.setMinimumSize(new Dimension(500, 500));
        //закачка компонентов - для тестов
        ProjectComponents.add(new power(Scale));
        ProjectComponents.get(0).ComponentLocation = new int[] {0, 0}; //- проверка относительных координат
        ProjectComponents.add(new power(Scale));
        ProjectComponents.get(1).ComponentLocation = new int[] {0, 0}; //- проверка относительных координат
        incomponentframe.add(ProjectComponents.get(0));
        intoolframe.add(ProjectComponents.get(1));
        //загржаем базовые компоненты
        initbasiccomponents();
        //упаковка экрана
        mainframe.pack();
    }
    public List<MainComponentcCass> ComponentLibraries = new ArrayList<>(Collections.emptyList());
    public List<Component> AvaluableComponents = new ArrayList<>(Collections.emptyList());
    public List<Component> ProjectComponents = new ArrayList<>(Collections.emptyList());
    public List<Component> ProjectShemes = new ArrayList<>(Collections.emptyList());
    public JFrame mainframe;
    public JPanel mainworkplace = new JPanel(new BorderLayout());
    public JPanel framesize = new JPanel(new BorderLayout());
    public JPanel intoolframe = new JPanel(new BorderLayout());
    public JPanel outtoolframe = new JPanel(new BorderLayout());
    public JPanel componenttree = new JPanel(new BorderLayout());
    public JPanel componentdata = new JPanel(new BorderLayout());
    public JPanel componentmenu = new JPanel(new BorderLayout());
    public JPanel incomponentframe = new JPanel(new BorderLayout());
    public JPanel outcomponentframe = new JPanel(new BorderLayout());
    public JTree componentroottree = new JTree(buildcomponentroottree());
    public JScrollPane scrpanecomponenttree = new JScrollPane(componentroottree);
    public JScrollPane componentframescrolpane = new JScrollPane(incomponentframe);
    public JSplitPane workplace = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, componentmenu, outcomponentframe);
    public TreeModel buildcomponentroottree(){
        //корневая панель
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Components");
        //панель первого порядка
        DefaultMutableTreeNode basic = new DefaultMutableTreeNode("Basic Component");
        DefaultMutableTreeNode imported = new DefaultMutableTreeNode("Modules");
        //панель второго порядка
        DefaultMutableTreeNode wires = new DefaultMutableTreeNode("Wires");
        //панель третьего порядка
        DefaultMutableTreeNode power = new DefaultMutableTreeNode(new power(Scale));
        DefaultMutableTreeNode ground = new DefaultMutableTreeNode(new ground());
        //загружаем панели
        root.add(basic);
        root.add(imported);
        basic.add(wires);
        wires.add(power);
        wires.add(ground);
        for (MainComponentcCass mainclass : ComponentLibraries){
            DefaultMutableTreeNode tmpnode = new DefaultMutableTreeNode(mainclass.libraryname);
            imported.add(tmpnode);
            for (Component component : mainclass.componentlist){
                JPanel tmppanel = new JPanel();
                tmppanel.add(new JLabel(component.componenticon));
                tmppanel.add(new JLabel(component.componentname));
                tmpnode.add((MutableTreeNode) tmppanel);
            }
        }
        //добавляем базовые папочки - упростим настройки - нельзя выгрузить базовые компоненты программы
        return new DefaultTreeModel(root);
    }
    public void initbasiccomponents(){
        //закачиваем сюда список компонентов
    }
    public void initgui(){
        componentmenu.setBorder(BorderFactory.createLineBorder(Color.black));
        componentmenu.setLayout(new BoxLayout(componentmenu, BoxLayout.Y_AXIS));
        componentmenu.setPreferredSize(new Dimension(100,100));
        componentmenu.setMinimumSize(new Dimension(100,100));
        componentmenu.add(outtoolframe);
        componentmenu.add(componenttree);
        componentmenu.add(componentdata);
        componentmenu.add(framesize);
        incomponentframe.setPreferredSize(new Dimension(600,600));
        incomponentframe.setBorder(BorderFactory.createLineBorder(Color.black));
        incomponentframe.setBackground(Color.WHITE);
        incomponentframe.setOpaque(true);
        outcomponentframe.add(componentframescrolpane);
        componentdata.setBorder(BorderFactory.createLineBorder(Color.black));
        componentdata.setBackground(Color.WHITE);
        componentdata.setOpaque(true);
        componentdata.setPreferredSize(new Dimension(100,100));
        intoolframe.setBorder(BorderFactory.createLineBorder(Color.black));
        intoolframe.setBackground(Color.RED);
        intoolframe.setOpaque(true);
        intoolframe.setPreferredSize(new Dimension(100,100));
        intoolframe.setMinimumSize(new Dimension(100,100));
        intoolframe.setMaximumSize(new Dimension(100,100));
        outtoolframe.add(intoolframe, BorderLayout.WEST);
        componenttree.add(scrpanecomponenttree);
        componenttree.setPreferredSize(new Dimension(100,100));
        scrpanecomponenttree.setPreferredSize(componenttree.getPreferredSize());
        scrpanecomponenttree.setBackground(Color.WHITE);
        scrpanecomponenttree.setOpaque(true);
        framesize.setBorder(BorderFactory.createLineBorder(Color.black));
        framesize.setBackground(Color.RED);
        framesize.setOpaque(true);
        framesize.setPreferredSize(new Dimension(100,100));
        workplace.setPreferredSize(new Dimension(500, 500));
        mainworkplace.add(workplace);
        componentroottree.setCellRenderer(new JTreeNodeRenderer());
    }
}
/*чтобы изменить положение компонента - надо сделать так:
ProjectComponents.get(n).ComponentLocation = new int[] {x, y};
ProjectComponents.get(n).repaint();
*/