package modules.methods.Listeners;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Point;
import modules.workenvironment.Component;
import modules.workenvironment.WorkEnvironmentMain;
public class ComponentListener implements MouseListener{
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x;
        int y = MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y;
        if (!WorkEnvironmentMain.excretion.getExcretedComponents().isEmpty()){
            WorkEnvironmentMain.excretion.removeAllExcretedComponents();
        }
        //for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
        for (Component component : WorkEnvironmentMain.ProjectComponents){
            Rectangle absolutecomponentrect = new Rectangle(component.getComponentLocation()[0] + component.getbounds().x, component.getComponentLocation()[0] + component.getbounds().y, component.getbounds().width, component.getbounds().height);
            if (isDotInRectangle(x, y, absolutecomponentrect)){
                WorkEnvironmentMain.excretion.addExcretedComponents(component);
                WorkEnvironmentMain.excretion.createExcretion();
            }
        }
        if (!isTouchedAnyComponent(x, y)){
            WorkEnvironmentMain.excretion.removeAllExcretedComponents();
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
        return rectangle.contains(new Point(dotx, doty));
    }
    public boolean isDotInRectangle(Point dot, Rectangle rectangle){
        return rectangle.contains(dot);
    }
    public boolean isTouchedAnyComponent(int dotx, int doty){
        for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
            if (isDotInRectangle(dotx, doty, new Rectangle(component.getComponentLocation()[0] + component.getbounds().x, component.getComponentLocation()[0] + component.getbounds().y, component.getbounds().width, component.getbounds().height))){
                return true;
            }
        }
        return false;
    }
}