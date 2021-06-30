package modules.workenvironment;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import java.util.concurrent.TimeUnit;
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
import modules.methods.LayoutManagers.ComponentLayoutManager;
import modules.methods.LayoutManagers.FrameScaleLayout;
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
        //закачка компонентов - для тестов =================================================================
        ProjectComponents.add(new power(Scale));
        ProjectComponents.get(0).ComponentLocation = new int[] {0, 0}; //- проверка относительных координат
        //ProjectComponents.get(0).rotation = 400; //- проверка поворота
        ProjectComponents.add(new power(Scale));
        ProjectComponents.get(1).ComponentLocation = new int[] {0, 0}; //- проверка относительных координат
        ProjectComponents.add(new power(Scale));
        ProjectComponents.get(2).ComponentLocation = new int[] {25, 25}; //- проверка относительных координат
        incomponentframe.add(ProjectComponents.get(0));
        //incomponentframe.add(ProjectComponents.get(2));
        intoolframe.add(ProjectComponents.get(1));
        //конец тестовой закачки ===========================================================================
        mainframe.pack();
        //загржаем базовые компоненты
        initbasiccomponents();
    }
    public List<MainComponentcCass> ComponentLibraries = new ArrayList<>(Collections.emptyList());
    public List<Component> AvaluableComponents = new ArrayList<>(Collections.emptyList());
    public List<Component> ProjectComponents = new ArrayList<>(Collections.emptyList());
    public List<Component> ProjectShemes = new ArrayList<>(Collections.emptyList());
    public JFrame mainframe;
    public JPanel mainworkplace = new JPanel(new BorderLayout());
    public JPanel inframesize = new JPanel(new BorderLayout());
    public JPanel intoolframe = new JPanel(new BorderLayout());
    public JPanel outtoolframe = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public JPanel outframesize = new JPanel(new FrameScaleLayout());
    public JPanel componenttree = new JPanel(new BorderLayout());
    public JPanel componentdata = new JPanel(new BorderLayout());
    public JPanel componentmenu = new JPanel(new BorderLayout());
    public JPanel incomponentframe = new JPanel(new ComponentLayoutManager());
    public JPanel outcomponentframe = new JPanel(new BorderLayout());
    public JTree componentroottree = new JTree(buildcomponentroottree());
    public JScrollPane scrpanecomponenttree = new JScrollPane(componentroottree);
    public JScrollPane componentframescrolpane = new JScrollPane(incomponentframe);
    public JSplitPane workplace = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, componentmenu, outcomponentframe);
    public JLabel Scalelabel = new JLabel();
    public TreeModel buildcomponentroottree(){
        //корневая панель
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Components");
        //панель первого порядка
        DefaultMutableTreeNode basic = new DefaultMutableTreeNode("Basic Component");
        DefaultMutableTreeNode imported = new DefaultMutableTreeNode("Modules");
        //панель второго порядка
        DefaultMutableTreeNode wires = new DefaultMutableTreeNode("Wires");
        //панель третьего порядка
        DefaultMutableTreeNode power = new DefaultMutableTreeNode(new power());
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
        componentmenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        componentmenu.setLayout(new BoxLayout(componentmenu, BoxLayout.Y_AXIS));
        componentmenu.setPreferredSize(new Dimension(100,100));
        componentmenu.setMinimumSize(new Dimension(100,100));
        componentmenu.add(outtoolframe);
        componentmenu.add(componenttree);
        componentmenu.add(componentdata);
        componentmenu.add(outframesize);
        incomponentframe.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        incomponentframe.setBackground(Color.WHITE);
        incomponentframe.setOpaque(true);
        outcomponentframe.add(componentframescrolpane);
        componentdata.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        componentdata.setBackground(Color.WHITE);
        componentdata.setOpaque(true);
        componentdata.setPreferredSize(new Dimension(100, 100));
        intoolframe.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        intoolframe.setBackground(Color.GREEN);
        intoolframe.setOpaque(true);
        intoolframe.setPreferredSize(new Dimension(100, 20));
        outtoolframe.add(intoolframe);
        ((FlowLayout) outtoolframe.getLayout()).setVgap(0);
        ((FlowLayout) outtoolframe.getLayout()).setHgap(0);
        componenttree.add(scrpanecomponenttree);
        componenttree.setPreferredSize(new Dimension(100, 100));
        scrpanecomponenttree.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrpanecomponenttree.setPreferredSize(componenttree.getPreferredSize());
        scrpanecomponenttree.setBackground(Color.WHITE);
        scrpanecomponenttree.setOpaque(true);
        inframesize.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inframesize.setBackground(Color.RED);
        inframesize.setOpaque(true);
        inframesize.add(Scalelabel, BorderLayout.WEST);
        outframesize.add(inframesize);
        workplace.setPreferredSize(new Dimension(500, 500));
        mainworkplace.add(workplace);
        componentroottree.setCellRenderer(new JTreeNodeRenderer());
        updateJLableScale();
    }
    public void updateJLableScale(){
        updateJLableScale("", "", "");
    }
    public void updateJLableScale(String before, String middle, String after){
        Scalelabel.setText(before + String.valueOf(Math.round(Scale * 100)) + middle + "%" + after);
    }
    public void rerenderComponents(boolean isplus, float Scalechange){
        if(isplus){
            updateJLableScale("", "+" + String.valueOf(Math.round(Scale * 100)), "");
        } else {
            updateJLableScale("", "-" + String.valueOf(Math.round(Scale * 100)), "");
        }
        //изменить размер компонентов -> incomponentframe.setLayout(new ComponentLayoutManager(new Dimension(x, y))), где x и у перерасчитаны
        for (Component component : ProjectComponents){
            component.repaint();
        }
        updateJLableScale();
    }
}
/*
Чтобы изменить положение компонента - надо сделать так:
ProjectComponents.get(n).ComponentLocation = new int[] {x, y};
ProjectComponents.get(n).repaint();

Чтобы изменить размер рабочего поля, необходимо ему дать новый LayoutManager, с новым измерением:
incomponentframe.setLayout(new ComponentLayoutManager(new Dimension(x, y)));
по умолчанию стоит измерение на 500х500
*/