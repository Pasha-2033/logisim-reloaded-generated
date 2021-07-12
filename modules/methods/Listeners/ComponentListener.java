package modules.methods.Listeners;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Point;
import modules.workenvironment.Component;
import modules.workenvironment.WorkEnvironmentMain;
public class ComponentListener extends MouseInputAdapter{
    public static Point mousePressedPoint = new Point(0, 0);
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x;
        int y = MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y;
        boolean touched = false;
        if (!WorkEnvironmentMain.excretion.getExcretedComponents().isEmpty()){
            WorkEnvironmentMain.excretion.removeAllExcretedComponents();
        }
        //for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
        for (Component component : WorkEnvironmentMain.ProjectComponents){
            Rectangle absolutecomponentrect = new Rectangle(Math.round((component.getComponentLocation()[0] + component.getbounds().x) * WorkEnvironmentMain.Scale), Math.round((component.getComponentLocation()[0] + component.getbounds().y) * WorkEnvironmentMain.Scale), Math.round(component.getbounds().width * WorkEnvironmentMain.Scale), Math.round(component.getbounds().height * WorkEnvironmentMain.Scale));
            if (isTouchedComponent(x, y, absolutecomponentrect)){
                WorkEnvironmentMain.excretion.addExcretedComponents(component);
                WorkEnvironmentMain.excretion.createExcretion();
                touched = true;
            }
        }
        if (!touched){
            WorkEnvironmentMain.excretion.removeAllExcretedComponents();
            WorkEnvironmentMain.excretion.removeExcretion();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int x = MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x;
        int y = MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y;
        mousePressedPoint = new Point(x, y);
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        boolean touched = false;
        //for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
        for (Component component : WorkEnvironmentMain.ProjectComponents){
            Rectangle absolutecomponentrect = new Rectangle(Math.round((component.getComponentLocation()[0] + component.getbounds().x) * WorkEnvironmentMain.Scale), Math.round((component.getComponentLocation()[0] + component.getbounds().y) * WorkEnvironmentMain.Scale), Math.round(component.getbounds().width * WorkEnvironmentMain.Scale), Math.round(component.getbounds().height * WorkEnvironmentMain.Scale));
            if (isTouchedComponent(mousePressedPoint.x, mousePressedPoint.y, absolutecomponentrect)){
                touched = true;
                break;
            }
        }
        if (touched){
            //прописать изменение положения компонентов
        } else {
            int x = MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x;
            int y = MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y;
            WorkEnvironmentMain.excretion.setChoosingRectangle(new Rectangle(mousePressedPoint.x, mousePressedPoint.y, x - mousePressedPoint.x, y - mousePressedPoint.y));
            WorkEnvironmentMain.excretion.createExcretion();
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        int x = MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x;
        int y = MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y;
        Rectangle mouseRectangle;
        if (x - mousePressedPoint.x >= 0 && y - mousePressedPoint.y >= 0){
            mouseRectangle = new Rectangle(mousePressedPoint.x, mousePressedPoint.y, x - mousePressedPoint.x, y - mousePressedPoint.y);
        } else if (x - mousePressedPoint.x >= 0){
            mouseRectangle = new Rectangle(mousePressedPoint.x, mousePressedPoint.y + y - mousePressedPoint.y, x - mousePressedPoint.x, -(y - mousePressedPoint.y));
        } else if (y - mousePressedPoint.y >= 0){
            mouseRectangle = new Rectangle(mousePressedPoint.x + x - mousePressedPoint.x, mousePressedPoint.y, -(x - mousePressedPoint.x), y - mousePressedPoint.y);
        } else {
            mouseRectangle = new Rectangle(mousePressedPoint.x + x - mousePressedPoint.x, mousePressedPoint.y + (y - mousePressedPoint.y), -(x - mousePressedPoint.x), -(y - mousePressedPoint.y));
        }
        boolean touched = false;
        if (!WorkEnvironmentMain.excretion.getExcretedComponents().isEmpty()){
            WorkEnvironmentMain.excretion.removeAllExcretedComponents();
        }
        //for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
        for (Component component : WorkEnvironmentMain.ProjectComponents){
            Rectangle absolutecomponentrect = new Rectangle(Math.round((component.getComponentLocation()[0] + component.getbounds().x) * WorkEnvironmentMain.Scale), Math.round((component.getComponentLocation()[0] + component.getbounds().y) * WorkEnvironmentMain.Scale), Math.round(component.getbounds().width * WorkEnvironmentMain.Scale), Math.round(component.getbounds().height * WorkEnvironmentMain.Scale));
            if (isTouchedComponent(mouseRectangle, absolutecomponentrect)){
                WorkEnvironmentMain.excretion.addExcretedComponents(component);
                WorkEnvironmentMain.excretion.createExcretion();
                touched = true;
            }
        }
        if (!touched){
            WorkEnvironmentMain.excretion.removeAllExcretedComponents();
            WorkEnvironmentMain.excretion.removeExcretion();
        }
        WorkEnvironmentMain.excretion.setChoosingRectangle(null);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    public boolean isTouchedComponent(int dotx, int doty, Rectangle rectangle){
        return rectangle.contains(new Point(dotx, doty));
    }
    public boolean isTouchedComponent(Point dot, Rectangle rectangle){
        return rectangle.contains(dot);
    }
    public boolean isTouchedComponent(Rectangle mouseRectangle, Rectangle componentRectangle){
        return mouseRectangle.intersects(componentRectangle);
    }
    public boolean isTouchedComponent(int dotx, int doty){
        for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
            if (isTouchedComponent(dotx, doty, new Rectangle(component.getComponentLocation()[0] + component.getbounds().x, component.getComponentLocation()[0] + component.getbounds().y, component.getbounds().width, component.getbounds().height))){
                return true;
            }
        }
        return false;
    }
}
/*
закоментированы for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){, потому что считываться должно с него, ProjectComponents - набор схем вообще, а не схема, которая просматривается на данный момент
*/