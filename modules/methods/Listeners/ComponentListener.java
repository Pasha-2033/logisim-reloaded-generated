package modules.methods.Listeners;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Point;
import modules.workenvironment.Component;
import modules.workenvironment.ComponentShadow;
import modules.workenvironment.WorkEnvironmentMain;
import mainclassfolder.Main;
public class ComponentListener extends MouseInputAdapter{
    public static Point mousePressedPoint = new Point(0, 0);
    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)){
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
        } else if (SwingUtilities.isRightMouseButton(e)){

        } else {
            //вряд ли понадобится
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
        if (SwingUtilities.isLeftMouseButton(e)){
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
                //создаем тени компонентов
                if (WorkEnvironmentMain.ShadowedComponents.isEmpty()){
                    for (Component component : WorkEnvironmentMain.excretion.getExcretedComponents()){
                        WorkEnvironmentMain.incomponentframe.getComponent(Arrays.asList(WorkEnvironmentMain.incomponentframe.getComponents()).indexOf(component)).setVisible(false);
                        ComponentShadow shadow = new ComponentShadow(component);
                        int x = Math.round((mousePressedPoint.x - MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x));
                        int y = Math.round((mousePressedPoint.y - MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y));
                        shadow.setComponentLocation(shadow.getComponentLocation()[0] + x, shadow.getComponentLocation()[1] + y);
                        WorkEnvironmentMain.ShadowedComponents.add(new ComponentShadow(shadow));
                    }
                } else {
                    for (Component component : WorkEnvironmentMain.ShadowedComponents){
                        int x = Math.round((MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x));
                        int y = Math.round((MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y));
                        component.setComponentLocation(component.getComponentLocation()[0] + x, component.getComponentLocation()[1] + y);
                    }
                }
            } else {
                int x = MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x;
                int y = MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y;
                WorkEnvironmentMain.excretion.setChoosingRectangle(new Rectangle(mousePressedPoint.x, mousePressedPoint.y, x - mousePressedPoint.x, y - mousePressedPoint.y));
                WorkEnvironmentMain.excretion.createExcretion();
            }
        } else if (SwingUtilities.isRightMouseButton(e)){
            //вряд ли понадобится
        } else {
            int x = MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x;
            int y = MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y;
            int dx = x - mousePressedPoint.x;
            int dy = y - mousePressedPoint.y;
            int vx = WorkEnvironmentMain.componentframescrolpane.getViewport().getViewPosition().x;
            int vy = WorkEnvironmentMain.componentframescrolpane.getViewport().getViewPosition().y;
            if (vx - dx > 0 && vy - dy > 0){
                WorkEnvironmentMain.componentframescrolpane.getViewport().setViewPosition(new Point(vx - dx, vy - dy));
            } else if (vx - dx > 0){
                WorkEnvironmentMain.componentframescrolpane.getViewport().setViewPosition(new Point(vx - dx, 0));
            } else if (vy - dy > 0){
                WorkEnvironmentMain.componentframescrolpane.getViewport().setViewPosition(new Point(0, vy - dy));
            } else {
                WorkEnvironmentMain.componentframescrolpane.getViewport().setViewPosition(new Point(0, 0));
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if (!WorkEnvironmentMain.ShadowedComponents.isEmpty()) {
            for (Component c: WorkEnvironmentMain.excretion.getExcretedComponents()){
            int x = MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x;
            int y = MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y;
            int dx = x - mousePressedPoint.x;
            int dy = y - mousePressedPoint.y;
                c.setComponentLocation(c.getComponentLocation()[0] + dx, c.getComponentLocation()[1] + dy);
            }
            WorkEnvironmentMain.ShadowedComponents = new ArrayList<ComponentShadow>(Collections.emptyList());
            WorkEnvironmentMain.movingcomponentframe.removeAll();
        } else {
            if (!WorkEnvironmentMain.excretion.getExcretedComponents().isEmpty()){
                for (Component component : WorkEnvironmentMain.excretion.getExcretedComponents()){
                    //int x = WorkEnvironmentMain.ShadowedComponents.get()
                    //component.setComponentLocation(x, y);
                    component.setVisible(true);
                }
                WorkEnvironmentMain.excretion.removeAllExcretedComponents();
            }
            if (SwingUtilities.isLeftMouseButton(e)){
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
        }
        Main.workenvironment.updateWorkplaceDimensionAndRerenderAll();
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseWheelMoved(MouseWheelEvent e){
        if (e.getWheelRotation() < 0){
            if (WorkEnvironmentMain.Scale < 10.0F) WorkEnvironmentMain.Scale += 0.1F;
        } else {
            if (WorkEnvironmentMain.Scale > 0.1F) WorkEnvironmentMain.Scale -= 0.1F;
        }
        Main.workenvironment.updateWorkplaceDimensionAndRerenderAll();
    }
    //далее логические вспомогательные методы
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