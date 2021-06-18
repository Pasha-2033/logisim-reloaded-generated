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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import modules.standartcomponent.wires.power;
public class WorkEnvironmentMain {
    public int[] ScreenLocation = {0, 0};
    public int Scale = 100;
    public Graphics graphics;
    public WorkEnvironmentMain(JFrame frame){
        //подготовка древа компонентов
        /*      ставим иконки       */
        //подготовка панелей
        initgui();
        //подготовка экрана
        mainframe = frame;
        mainframe.add(mainworkplace);
        mainframe.setMinimumSize(new Dimension(500, 500));
        //закачка компонентов - для тестов
        ProjectComponents.add(new power());
        ProjectComponents.get(0).ScreenLocation = new int[] {0, 0};
        ProjectComponents.get(0).Scale = 100;
        ProjectComponents.get(0).ComponentLocation = new int[] {0, 0}; //- проверка относительных координат
        ProjectComponents.add(new power());
        ProjectComponents.get(1).ScreenLocation = new int[] {0, 0};
        ProjectComponents.get(1).Scale = 100;
        ProjectComponents.get(1).ComponentLocation = new int[] {0, 0}; //- проверка относительных координат
        componentframe.add(ProjectComponents.get(0));
        intoolframe.add(ProjectComponents.get(1));
        //загржаем базовые компоненты
        initbasiccomponents();
        //упаковка экрана
        mainframe.pack();
    }
    public List<List<Component>> Components = new ArrayList<>(Collections.emptyList());
    public List<Component> ProjectComponents = new ArrayList<>(Collections.emptyList());
    public JFrame mainframe;
    public JPanel mainworkplace = new JPanel(new BorderLayout());
    public JPanel framesize = new JPanel(new BorderLayout());
    public JPanel intoolframe = new JPanel(new BorderLayout());
    public JPanel outtoolframe = new JPanel(new BorderLayout());
    public JPanel componenttree = new JPanel(new BorderLayout());
    public JPanel componentdata = new JPanel(new BorderLayout());
    public JPanel componentmenu = new JPanel();
    public JPanel componentframe = new JPanel(new BorderLayout());
    public JSplitPane workplace = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, componentmenu, componentframe);
    public JTree componentroottree = new JTree(buildcomponentroottree());
    public JScrollPane scrpanecomponenttree = new JScrollPane(componentroottree);
    public class movescreen extends Thread{
        public int[] screenlocation;
        public movescreen(int [] screenlocation) {
            setDaemon(true);
            this.screenlocation = screenlocation;
        }
        public void run(){
            for (Component component : ProjectComponents) {
                component.ScreenLocation = screenlocation;
                component.repaint();
            }
        }
    }
    public TreeModel buildcomponentroottree(){
        //корневая панель
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Components");
        //панель первого порядка
        DefaultMutableTreeNode wires = new DefaultMutableTreeNode("Wires");
        DefaultMutableTreeNode elements = new DefaultMutableTreeNode("Elements");
        //панель второго порядка
        DefaultMutableTreeNode power = new DefaultMutableTreeNode("Power");
        DefaultMutableTreeNode ground = new DefaultMutableTreeNode("Ground");
        //иконки
        //загружаем панели
        root.add(wires);
        root.add(elements);
        wires.add(power);
        wires.add(ground);
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
        componentframe.setBorder(BorderFactory.createLineBorder(Color.black));
        componentframe.setBackground(Color.WHITE);
        componentframe.setOpaque(true);
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
    }
}
/*чтобы изменить положение компонента - надо сделать так:
ProjectComponents.get(n).ComponentLocation = new int[] {x, y};
ProjectComponents.get(n).repaint();
*/