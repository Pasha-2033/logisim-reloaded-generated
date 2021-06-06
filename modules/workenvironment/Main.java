package modules.workenvironment;
import java.util.Collections;
import java.util.List;
//import java.awt.Graphics;
public class Main {
    public int[] ScreenLocation;
    public int Scale;
    //private Graphics graphics;
    public void main(){
        ScreenLocation[0] = 0;
        ScreenLocation[1] = 0;
    }
    public List<Component> Components = Collections.emptyList();
    public List<Component> ProjectComponents = Collections.emptyList();
    public void newprojectcomponent(Component component){
        ProjectComponents.add(component);
    }
    public void deleteprojectcomponent(){

    }
}