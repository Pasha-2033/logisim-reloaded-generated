package modules.workenvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import modules.standartcomponent.wires.power;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
public class WorkEnvironmentMain {
    public int[] ScreenLocation = {0, 0};
    public int Scale = 100;
    public Graphics graphics;
    public WorkEnvironmentMain(JFrame mainframe){
        this.mainframe = mainframe;
        mainframe.add(workplace);
        workplace.add(componentframe, BorderLayout.CENTER);
        workplace.add(toolframe, BorderLayout.WEST);
        ProjectComponents.add(new power());
        ProjectComponents.get(0).ScreenLocation = new int[] {0,0};
        ProjectComponents.get(0).Scale = 100;
        ProjectComponents.get(0).ComponentLocation = new int[] {0, 0}; //- проверка относительных координат
        componentframe.add(ProjectComponents.get(0));
        workplace.setPreferredSize(new Dimension(800,600));
        componentframe.setBorder(BorderFactory.createLineBorder(Color.black));
        power i = new power();
        i.Scale = 100;
        i.ScreenLocation = new int[] {0,0};
        i.ComponentLocation = new int[] {0,1};
        toolframe.add(i);
        toolframe.setPreferredSize(new Dimension(100,100));
        toolframe.setBorder(BorderFactory.createLineBorder(Color.black));
        toolframe.setVisible(true);
        componentframe.setLocation(100, 100);
        this.mainframe.pack();
        //update.start();
    }
    public List<Component> Components = new ArrayList<>(Collections.emptyList());
    public List<Component> ProjectComponents = new ArrayList<>(Collections.emptyList());
    public JFrame mainframe;
    public JPanel workplace = new JPanel(new BorderLayout());
    public JPanel componentframe = new JPanel(new BorderLayout());
    public JPanel toolframe = new JPanel(new BorderLayout());
    public JPanel componentdata = new JPanel(new BorderLayout());
    public class workplaceupdate extends Thread{
        public workplaceupdate() {
            setDaemon(true);
        }
        public void run(){
            while (true){
                //workplace.setPreferredSize(new Dimension((int) mainframe.getSize().getWidth(), (int) mainframe.getSize().getHeight()));
            }
        }
    }
    public workplaceupdate update = new workplaceupdate();
}
//ProjectComponents.get(0).setBounds(0, 0, 500, 500); - вряд ли понадобится (и строка ниже)
//ProjectComponents.get(0).setPreferredSize(new Dimension(500,500));