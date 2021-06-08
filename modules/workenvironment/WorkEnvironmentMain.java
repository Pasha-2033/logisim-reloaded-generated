package modules.workenvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modules.component.wires.power;
import java.awt.Graphics;
public class WorkEnvironmentMain {
    public int[] ScreenLocation = {0, 0};
    public int Scale = 100;
    public Graphics graphics;
    public WorkEnvironmentMain(){
        ProjectComponents.add(new power());
        componentupdate i = new componentupdate();
        i.start();
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
}