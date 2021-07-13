package modules.workenvironment;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
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
import modules.gui.Buttons;
import modules.gui.Dots;
import modules.gui.Excretion;
import modules.languages.language;
import modules.methods.ExcitationParser;
import modules.methods.JTreeNodeRenderer;
import modules.methods.LayoutManagers.ComponentLayoutManager;
import modules.methods.LayoutManagers.FrameScaleLayout;
import modules.methods.LayoutManagers.ScaleFrameButtonsLayout;
import modules.methods.Listeners.ComponentListener;
import modules.standartcomponent.wires.ground;
import modules.standartcomponent.wires.mainwires;
import modules.standartcomponent.wires.power;
import modules.standartcomponent.wires.resistor;
public class WorkEnvironmentMain {
    public String ProjectName;
    public final String DefaultProjectName = language.trnslt("New Project");
    public static ExcitationParser excitationparser = new ExcitationParser();
    public static float Scale = 1.0F;
    public static boolean DotsThere = true;
    public static List<MainComponentcCass> ComponentLibraries = new ArrayList<>(Collections.emptyList());
    public static List<Component> AvaluableComponents = new ArrayList<>(Collections.emptyList());
    public static List<Component> ProjectComponents = new ArrayList<>(Collections.emptyList());
    public static List<Component> ProjectShemes = new ArrayList<>(Collections.emptyList());
    public static Component currentSircut = new Component();
    public JFrame mainframe;
    public JPanel mainworkplace = new JPanel(new BorderLayout());
    public JPanel inframesize = new JPanel(new BorderLayout());
    public JPanel scalebuttonspanel = new JPanel(new ScaleFrameButtonsLayout());
    public JPanel intoolframe = new JPanel(new BorderLayout());
    public JPanel outtoolframe = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public JPanel outframesize = new JPanel(new FrameScaleLayout());
    public JPanel componenttree = new JPanel(new BorderLayout());
    public JPanel componentdata = new JPanel(new BorderLayout());
    public JPanel componentmenu = new JPanel(new BorderLayout());
    public static JPanel incomponentframe = new JPanel(new ComponentLayoutManager());
    public JPanel outcomponentframe = new JPanel(new BorderLayout());
    public static Dots dots = new Dots();
    public static Excretion excretion = new Excretion();
    public JTree componentroottree = new JTree(buildcomponentroottree());
    public JScrollPane scrpanecomponenttree = new JScrollPane(componentroottree);
    public static JScrollPane componentframescrolpane = new JScrollPane(incomponentframe);
    public JSplitPane workplace = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, componentmenu, outcomponentframe);
    public JLabel Scalelabel = new JLabel();
    public WorkEnvironmentMain(JFrame frame){
        //подготовка панелей
        initgui();
        Scale = 1.0F;
        //подготовка экрана
        mainframe = frame;
        mainframe.add(mainworkplace);
        mainframe.setMinimumSize(new Dimension(100, 100));
        //закачка компонентов - для тестов =================================================================
        ProjectComponents.add(new resistor());
        ProjectComponents.get(0).setComponentLocation(100, 100); //- проверка относительных координат
        ProjectComponents.get(0).setRotation(90); //- проверка поворота
        ProjectComponents.add(new ground());
        ProjectComponents.get(1).setComponentLocation(50, 50); //- проверка относительных координат
        ProjectComponents.add(new power());
        ProjectComponents.get(2).setComponentLocation(200, 200); //- проверка относительных координат
        incomponentframe.add(ProjectComponents.get(0), incomponentframe.getComponentCount() - 2);
        incomponentframe.add(ProjectComponents.get(1), incomponentframe.getComponentCount() - 2);
        incomponentframe.add(ProjectComponents.get(2), incomponentframe.getComponentCount() - 2);
        //конец тестовой закачки ===========================================================================
        mainframe.pack();
        //загржаем базовые компоненты
        initbasiccomponents();
    }
    public TreeModel buildcomponentroottree(){
        //корневая панель
        DefaultMutableTreeNode root;
        if (ProjectName != null){
            root = new DefaultMutableTreeNode(ProjectName);
        } else {
            root = new DefaultMutableTreeNode(DefaultProjectName);
        }
        //панель первого порядка
        DefaultMutableTreeNode schemes = new DefaultMutableTreeNode(language.trnslt("Schemes"));
        DefaultMutableTreeNode components = new DefaultMutableTreeNode(language.trnslt("Components"));
        root.add(schemes);
        root.add(components);
        for (Component component : ProjectComponents){
            schemes.add(new DefaultMutableTreeNode(component));
        }
        //панель второго порядка
        DefaultMutableTreeNode basic = new DefaultMutableTreeNode(language.trnslt("Basic Component"));
        DefaultMutableTreeNode imported = new DefaultMutableTreeNode(language.trnslt("Modules"));
        components.add(basic);
        components.add(imported);
        //панель третьего порядка
        DefaultMutableTreeNode wires = new DefaultMutableTreeNode(language.trnslt(new mainwires().libraryname));
        for (Component component : new mainwires().componentlist){
            wires.add(new DefaultMutableTreeNode(component));
        }
        basic.add(wires);
        //продолжить


        for (MainComponentcCass mainclass : ComponentLibraries){
            DefaultMutableTreeNode tmpnode = new DefaultMutableTreeNode(mainclass.libraryname);
            imported.add(tmpnode);
            for (Component component : mainclass.componentlist){
                JPanel tmppanel = new JPanel();
                tmppanel.add(new JLabel(component.getComponentIcon()));
                tmppanel.add(new JLabel(component.getComponentName()));
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
        incomponentframe.setBorder(BorderFactory.createLineBorder(ColorList.BLACK[0]));
        incomponentframe.setBackground(Color.WHITE);
        incomponentframe.setOpaque(true);
        incomponentframe.add(excretion);
        incomponentframe.add(dots);
        incomponentframe.addMouseListener(new ComponentListener());
        incomponentframe.addMouseMotionListener(new ComponentListener());
        incomponentframe.addMouseWheelListener(new ComponentListener());
        outcomponentframe.add(componentframescrolpane);
        componentframescrolpane.addComponentListener(
            new ComponentAdapter() {
                public void componentResized(ComponentEvent e) {
                    updateWorkplaceDimensionAndRerenderAll();
                }
            }
        );
        componentdata.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        componentdata.setOpaque(true);
        componentdata.setPreferredSize(new Dimension(100, 100));
        componentframescrolpane.setWheelScrollingEnabled(false);
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
        scalebuttonspanel.add(new Buttons.DoteButton());
        scalebuttonspanel.add(new Buttons.UPScaleButton());
        scalebuttonspanel.add(new Buttons.DOWNScaleButton());
        inframesize.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inframesize.setOpaque(true);
        inframesize.add(Scalelabel, BorderLayout.WEST);
        inframesize.add(scalebuttonspanel, BorderLayout.EAST);
        outframesize.add(inframesize);
        workplace.setPreferredSize(new Dimension(500, 500));
        mainworkplace.add(workplace);
        componentroottree.setCellRenderer(new JTreeNodeRenderer());
        updateJLableScale();
    }
    public void updateJLableScale(){
        Scalelabel.setText(String.valueOf(Math.round(Scale * 100)) + "%");
    }
    public void updateWorkplaceDimensionAndRerenderAll(){
        boolean i1 = currentSircut.getSize().getWidth() * Scale >= componentframescrolpane.getViewport().getWidth();
        boolean i2 = currentSircut.getSize().getHeight() * Scale >= componentframescrolpane.getViewport().getHeight();
        if (i1 && i2){
            incomponentframe.setLayout(new ComponentLayoutManager(new Dimension((int) (currentSircut.getSize().getWidth() * Scale), (int) (currentSircut.getSize().getHeight() * Scale))));
        } else {
            if (!i1 && i2){
                incomponentframe.setLayout(new ComponentLayoutManager(new Dimension(componentframescrolpane.getViewport().getWidth(), (int) (currentSircut.getSize().getHeight() * Scale))));
            } else if (i1 && !i2){
                incomponentframe.setLayout(new ComponentLayoutManager(new Dimension((int) (currentSircut.getSize().getWidth() * Scale), componentframescrolpane.getViewport().getHeight())));
            } else {
                incomponentframe.setLayout(new ComponentLayoutManager(new Dimension(componentframescrolpane.getViewport().getWidth(), componentframescrolpane.getViewport().getHeight())));
            }
        }
        rerenderAllComponents();
    }
    public void rerenderAllComponents(){
        incomponentframe.repaint();
        updateJLableScale();
    }
    public void addComponent(Component c){
        incomponentframe.add(c, incomponentframe.getComponentCount() - 2);
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