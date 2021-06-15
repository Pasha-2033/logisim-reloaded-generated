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
import javax.swing.JSplitPane;
//import javax.swing.border.Border;
import modules.standartcomponent.wires.power;
public class WorkEnvironmentMain {
    public int[] ScreenLocation = {0, 0};
    public int Scale = 100;
    public Graphics graphics;
    public WorkEnvironmentMain(JFrame frame){
        //подготовка панелей
        componentmenu.setBorder(BorderFactory.createLineBorder(Color.black));
        componentmenu.setLayout(new BoxLayout(componentmenu, BoxLayout.Y_AXIS));
        componentmenu.setPreferredSize(new Dimension(100,100));
        componentmenu.setMinimumSize(new Dimension(100,100));
        componentmenu.add(toolframe);
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
        componenttree.setBorder(BorderFactory.createLineBorder(Color.black));
        componenttree.setBackground(Color.BLUE);
        componenttree.setOpaque(true);
        componenttree.setPreferredSize(new Dimension(100,100));
        toolframe.setBorder(BorderFactory.createLineBorder(Color.black));
        toolframe.setBackground(Color.RED);
        toolframe.setOpaque(true);
        toolframe.setPreferredSize(new Dimension(100,100));
        toolframe.setMinimumSize(new Dimension(100,100));
        toolframe.setMaximumSize(new Dimension(100,100));
        framesize.setBorder(BorderFactory.createLineBorder(Color.black));
        framesize.setBackground(Color.RED);
        framesize.setOpaque(true);
        framesize.setPreferredSize(new Dimension(100,100));
        workplace.setPreferredSize(new Dimension(500, 500));
        mainworkplace.add(workplace);
        //подготовка экрана
        mainframe = frame;
        mainframe.add(mainworkplace);
        mainframe.setMinimumSize(new Dimension(500, 500));
        //закачка компонентов
        ProjectComponents.add(new power());
        ProjectComponents.get(0).ScreenLocation = new int[] {0, 0};
        ProjectComponents.get(0).Scale = 100;
        ProjectComponents.get(0).ComponentLocation = new int[] {0, 0}; //- проверка относительных координат
        ProjectComponents.add(new power());
        ProjectComponents.get(1).ScreenLocation = new int[] {0, 0};
        ProjectComponents.get(1).Scale = 100;
        ProjectComponents.get(1).ComponentLocation = new int[] {0, 0}; //- проверка относительных координат
        componentframe.add(ProjectComponents.get(0));
        toolframe.add(ProjectComponents.get(1));
        //упаковка экрана
        mainframe.pack();
        //update.start();
        
    }
    public List<Component> Components = new ArrayList<>(Collections.emptyList());
    public List<Component> ProjectComponents = new ArrayList<>(Collections.emptyList());
    public JFrame mainframe;
    public JPanel framesize = new JPanel(new BorderLayout());
    public JPanel mainworkplace = new JPanel(new BorderLayout());
    public JPanel toolframe = new JPanel(new BorderLayout());
    public JPanel componentdata = new JPanel(new BorderLayout());
    public JPanel componentmenu = new JPanel();
    public JPanel componenttree = new JPanel(new BorderLayout());
    public JPanel componentframe = new JPanel(new BorderLayout());
    public JSplitPane workplace = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, componentmenu, componentframe);
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
    public movescreen update = new movescreen(ScreenLocation);
}
//ProjectComponents.get(0).setBounds(0, 0, 500, 500); - вряд ли понадобится (и строка ниже)
//ProjectComponents.get(0).setPreferredSize(new Dimension(500,500));

/*чтобы изменить положение компонента - надо сделать так:
ProjectComponents.get(n).ComponentLocation = new int[] {x, y};
ProjectComponents.get(n).repaint();
*/