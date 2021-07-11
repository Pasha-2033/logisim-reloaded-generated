package modules.methods.Listeners;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Rectangle;
import java.awt.Point;
import modules.workenvironment.Component;
import modules.workenvironment.WorkEnvironmentMain;
public class ComponentListener implements MouseListener{
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x;
        int y = WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y;
        if (!WorkEnvironmentMain.excretion.getExcretedComponents().isEmpty()){
            WorkEnvironmentMain.excretion.removeAllExcretedComponents();
        }
        for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
            if (isDotInRectangle(x, y, component.getbounds())){
                WorkEnvironmentMain.excretion.addExcretedComponents(component);
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    public boolean isDotInRectangle(int dotx, int doty, Rectangle rectangle){
        Point dot = new Point(dotx, doty);
        return (dot.x > rectangle.x && dot.x < rectangle.x + rectangle.width) && (dot.y < rectangle.y && dot.y > rectangle.y + rectangle.height);
    }
    public boolean isDotInRectangle(Point dot, Rectangle rectangle){
        return (dot.x > rectangle.x && dot.x < rectangle.x + rectangle.width) && (dot.y < rectangle.y && dot.y > rectangle.y + rectangle.height);
    }
    public boolean isTouchedAnyComponent(int dotx, int doty){
        for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
            if (isDotInRectangle(dotx, doty, component.getbounds())){
                return true;
            }
        }
        return false;
    }
}