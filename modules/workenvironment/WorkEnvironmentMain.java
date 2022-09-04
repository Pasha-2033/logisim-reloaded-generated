package modules.workenvironment;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UnsupportedLookAndFeelException;

import modules.gui.Buttons;
import modules.gui.Dots;
import modules.gui.MainAppWindow;
import modules.methods.LayoutManagers.FrameScaleLayout;
import modules.methods.LayoutManagers.ScaleFrameButtonsLayout;
public class WorkEnvironmentMain {
    public static String projectname;
    public static float scale = 1.0F;
    //гуи
    public static boolean dotsthere = true;
    public static Dots dots = new Dots();
    public static JFrame mainframe;
    public static JPanel projectmenu = new JPanel();
    public static JPanel componentcanvas = new JPanel(new BorderLayout());
    public static JPanel scememenu = new JPanel(new BorderLayout());
    public static JPanel componentmenu = new JPanel(new BorderLayout());
    public static JPanel attributemenu = new JPanel(new BorderLayout());
    public static JPanel outscalemenu = new JPanel(new FrameScaleLayout());
    public static JPanel inscalemenu = new JPanel(new BorderLayout());
    public static JPanel scalebuttons = new JPanel(new ScaleFrameButtonsLayout());
    public static JScrollPane componentcanvasscrolpane = new JScrollPane(componentcanvas);
    public static JSplitPane workplace = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, projectmenu, componentcanvasscrolpane);
    public static JLabel scalelabel = new JLabel("100%");
    //схема
    public static int circuitindex = 0;
    //текущая область
    public static List<Component> circuits;
    public static List<Object[]> dependencies;  //List<List<Component> circuits, String name, String path, bolean ismodule>
    public WorkEnvironmentMain() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        initgui();
        createmainwindow();
    }
    public final void createmainwindow() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        int[] xywh = SettingsManager.getXYWHMAW();
        mainframe = new MainAppWindow(xywh[0], xywh[1], xywh[2], xywh[3]);
        mainframe.add(workplace);
        mainframe.setMinimumSize(new Dimension(100, 100));
        mainframe.pack();
    }
    public final void initgui() {
        initcanvas();
        initscemenemu();
        initcomponentmenu();
        initattributemenu();
        initscalemenu();
        componentcanvasscrolpane.setWheelScrollingEnabled(false);
        projectmenu.setLayout(new BoxLayout(projectmenu, BoxLayout.Y_AXIS));
        workplace.setPreferredSize(new Dimension(500, 500));
        workplace.setDividerLocation(200);
    }
    public final void initcanvas() {
        componentcanvas.setPreferredSize(new Dimension(500, 500)); //чтобы была прокрутка нужно установить размер
        componentcanvas.setBackground(Color.WHITE);
        componentcanvas.add(dots);
    }
    public final void initscemenemu() {
        scememenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scememenu.setBackground(Color.YELLOW);  //временно, для ориентации в панелях гуи
        projectmenu.add(scememenu);
    }
    public final void initcomponentmenu() {
        componentmenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        componentmenu.setBackground(Color.GREEN);   //временно, для ориентации в панелях гуи
        projectmenu.add(componentmenu);
    }
    public final void initattributemenu() {
        attributemenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        attributemenu.setBackground(Color.BLUE);    //временно, для ориентации в панелях гуи
        projectmenu.add(attributemenu);
    }
    public final void initscalemenu() {
        scalebuttons.add(new Buttons.DoteButton());
        scalebuttons.add(new Buttons.UPScaleButton());
        scalebuttons.add(new Buttons.DOWNScaleButton());
        inscalemenu.add(scalelabel, BorderLayout.WEST);
        inscalemenu.add(scalebuttons, BorderLayout.EAST);
        inscalemenu.setBackground(Color.RED); //временно, для ориентации в панелях гуи
        inscalemenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        outscalemenu.add(inscalemenu);
        projectmenu.add(outscalemenu);
    }
    public void loadproject() {
        //to do
    }
    public void releaseproject(){
        circuits = null;
    }
}