package modules.workenvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modules.component.wires.power;
import java.awt.Graphics;
public class WorkEnvironmentMain {
    public int[] ScreenLocation = {0, 0};
    public int Scale = 100;
    private Graphics graphics;
    public WorkEnvironmentMain(){
        Components.add(new power());
    }
    public List<Component> Components = new ArrayList<>(Collections.emptyList());
    public List<Component> ProjectComponents = new ArrayList<>(Collections.emptyList());
    public class componentupdate extends Thread{
        public componentupdate() {
            setDaemon(true);
        }
        public void run(){
            while (true){
                for (Component comp : Components){
                    new DrawComponent(comp, graphics, ScreenLocation, Scale);
                }
            }
        }
    }
    public componentupdate compupdate = new componentupdate();
}