package modules.workenvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import modules.standartcomponent.wires.power;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
public class WorkEnvironmentMain {
    public int[] ScreenLocation = {0, 0};
    public int Scale = 100;
    public Graphics graphics;
    public WorkEnvironmentMain(JFrame frame){
        //подготовка панелей
        componentframe.setBorder(BorderFactory.createLineBorder(Color.black));
        componentframe.setBackground(Color.WHITE);
        componentframe.setOpaque(true);
        toolframe.setBorder(BorderFactory.createLineBorder(Color.black));
        toolframe.setBackground(Color.RED);
        toolframe.setOpaque(true);
        toolframe.setPreferredSize(new Dimension(100,100));
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
    public JPanel mainworkplace = new JPanel(new BorderLayout());
    public JPanel componentframe = new JPanel(new BorderLayout());
    public JPanel toolframe = new JPanel(new BorderLayout());
    public JPanel componentdata = new JPanel(new BorderLayout());
    public JSplitPane workplace = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, toolframe, componentframe);
    public class workplaceupdate extends Thread{
        public workplaceupdate() {
            setDaemon(true);
        }
        public void run(){
            while (true){
                //repaint() - для всех панелей - особенно для компонента
            }
        }
    }
    public workplaceupdate update = new workplaceupdate();
}
//ProjectComponents.get(0).setBounds(0, 0, 500, 500); - вряд ли понадобится (и строка ниже)
//ProjectComponents.get(0).setPreferredSize(new Dimension(500,500));