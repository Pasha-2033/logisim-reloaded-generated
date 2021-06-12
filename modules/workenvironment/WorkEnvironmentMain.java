package modules.workenvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import modules.standartcomponent.wires.power;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Dimension;
public class WorkEnvironmentMain {
    public int[] ScreenLocation = {0, 0};
    public int Scale = 100;
    public Graphics graphics;
    public WorkEnvironmentMain(JFrame mainframe){
        this.mainframe = mainframe;
        mainframe.add(workplace, BorderLayout.WEST);
        ProjectComponents.add(new power());
        ProjectComponents.get(0).ScreenLocation = new int[] {0,0};
        ProjectComponents.get(0).Scale = 100;
        //ProjectComponents.get(0).ComponentLocation = new int[] {1, 1}; - проверка относительных координат
        //ProjectComponents.get(0).setBounds(0, 0, 500, 500); - вряд ли понадобится (и строка ниже)
        //ProjectComponents.get(0).setPreferredSize(new Dimension(500,500));
        workplace.setPreferredSize(new Dimension(500,500));
        workplace.add(ProjectComponents.get(0));
        //(new componentupdate()).start(); - изменить метод, потом использовать
        this.mainframe.pack();
    }
    public List<Component> Components = new ArrayList<>(Collections.emptyList());
    public List<Component> ProjectComponents = new ArrayList<>(Collections.emptyList());
    public class componentupdate extends Thread{
        public componentupdate() {
            setDaemon(true);
        }
        public void run(){
            while (true){
                for (Component comp : ProjectComponents){
                    new DrawComponent(comp, graphics, ScreenLocation, Scale);
                }
            }
        }
    }
    public componentupdate compupdate = new componentupdate();
    public JFrame mainframe;
    public JPanel workplace = new JPanel(new BorderLayout());
}