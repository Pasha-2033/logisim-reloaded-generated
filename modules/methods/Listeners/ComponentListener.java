package modules.methods.Listeners;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
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
        int x = MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x;
        int y = MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y;
        WorkEnvironmentMain.movingcomponentframe.setVisible(false);
        if (SwingUtilities.isLeftMouseButton(e)){
            if (WorkEnvironmentMain.newone.getComponentCount() != 0){
                Main.workenvironment.addComponent(WorkEnvironmentMain.newComponent, Math.round(x / WorkEnvironmentMain.Scale), Math.round(y / WorkEnvironmentMain.Scale));
                Main.workenvironment.rerenderAllComponents();
                WorkEnvironmentMain.newone.removeAll();
                WorkEnvironmentMain.incomponentframe.revalidate();
            }
            boolean touched = false;
            if (!WorkEnvironmentMain.excretion.getExcretedComponents().isEmpty()){
                WorkEnvironmentMain.excretion.removeAllExcretedComponents();
            }
            for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
                Rectangle absolutecomponentrect = new Rectangle(Math.round((component.getComponentLocation()[0] + component.getbounds().x) * WorkEnvironmentMain.Scale), Math.round((component.getComponentLocation()[1] + component.getbounds().y) * WorkEnvironmentMain.Scale), Math.round(component.getbounds().width * WorkEnvironmentMain.Scale), Math.round(component.getbounds().height * WorkEnvironmentMain.Scale));
                if (isTouchedComponent(x, y, absolutecomponentrect)){
                    WorkEnvironmentMain.excretion.addExcretedComponents(component);
                    WorkEnvironmentMain.ShadowedComponents.add(new ComponentShadow(component));
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
        if (WorkEnvironmentMain.newone.getComponentCount() != 0){
            Main.workenvironment.addComponent(WorkEnvironmentMain.newComponent, Math.round(x / WorkEnvironmentMain.Scale), Math.round(y / WorkEnvironmentMain.Scale));
            Main.workenvironment.rerenderAllComponents();
            WorkEnvironmentMain.newone.removeAll();
            WorkEnvironmentMain.incomponentframe.revalidate();
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)){
            int x = MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x;
            int y = MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y;
            if (WorkEnvironmentMain.newone.getComponentCount() != 0){
                Main.workenvironment.addComponent(WorkEnvironmentMain.newComponent, Math.round(x / WorkEnvironmentMain.Scale), Math.round(y / WorkEnvironmentMain.Scale));
                Main.workenvironment.rerenderAllComponents();
                WorkEnvironmentMain.newone.removeAll();
                WorkEnvironmentMain.incomponentframe.revalidate();
            }
            WorkEnvironmentMain.movingcomponentframe.setVisible(true);
            boolean touched = false;
            for (Component component : WorkEnvironmentMain.excretion.getExcretedComponents()){
                Rectangle absolutecomponentrect = new Rectangle(Math.round((component.getComponentLocation()[0] + component.getbounds().x) * WorkEnvironmentMain.Scale), Math.round((component.getComponentLocation()[1] + component.getbounds().y) * WorkEnvironmentMain.Scale), Math.round(component.getbounds().width * WorkEnvironmentMain.Scale), Math.round(component.getbounds().height * WorkEnvironmentMain.Scale));
                if (isTouchedComponent(mousePressedPoint.x, mousePressedPoint.y, absolutecomponentrect)){
                    touched = true;
                    break;
                }
            }
            if (touched){
                WorkEnvironmentMain.movingcomponentframe.setVisible(true);
                for (Component component : WorkEnvironmentMain.excretion.getExcretedComponents()){
                    component.setVisible(false);
                }
                if (WorkEnvironmentMain.excretion.getExcretedComponents().size() != WorkEnvironmentMain.ShadowedComponents.size()){
                    WorkEnvironmentMain.ShadowedComponents = new ArrayList<ComponentShadow>(Collections.emptyList());
                    for (Component component : WorkEnvironmentMain.excretion.getExcretedComponents()){
                        WorkEnvironmentMain.ShadowedComponents.add(new ComponentShadow(component));
                    }
                }
                for (ComponentShadow component : WorkEnvironmentMain.ShadowedComponents){
                    int dx = Math.round((x - mousePressedPoint.x) / WorkEnvironmentMain.Scale);
                    int dy = Math.round((y - mousePressedPoint.y) / WorkEnvironmentMain.Scale);
                    component.setComponentLocation(component.getComponentBase()[0] + dx, component.getComponentBase()[1] + dy);
                    component.repaint();
                }
            } else {
                WorkEnvironmentMain.excretion.setChoosingRectangle(new Rectangle(mousePressedPoint.x, mousePressedPoint.y, x - mousePressedPoint.x, y - mousePressedPoint.y));
                WorkEnvironmentMain.excretion.createExcretion();
                WorkEnvironmentMain.excretion.removeAllExcretedComponents();
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
        if (SwingUtilities.isLeftMouseButton(e)){
            WorkEnvironmentMain.movingcomponentframe.setVisible(false);
            boolean touched;
            if (WorkEnvironmentMain.excretion.getExcretedComponents().isEmpty()){
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
                touched = false;
                for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
                    Rectangle absolutecomponentrect = new Rectangle(Math.round((component.getComponentLocation()[0] + component.getbounds().x) * WorkEnvironmentMain.Scale), Math.round((component.getComponentLocation()[1] + component.getbounds().y) * WorkEnvironmentMain.Scale), Math.round(component.getbounds().width * WorkEnvironmentMain.Scale), Math.round(component.getbounds().height * WorkEnvironmentMain.Scale));
                    if (isTouchedComponent(mouseRectangle, absolutecomponentrect)){
                        WorkEnvironmentMain.excretion.addExcretedComponents(component);
                        WorkEnvironmentMain.ShadowedComponents.add(new ComponentShadow(component));
                        WorkEnvironmentMain.excretion.createExcretion();
                        touched = true;
                    }
                }
                if (!touched){
                    WorkEnvironmentMain.excretion.removeAllExcretedComponents();
                    WorkEnvironmentMain.excretion.removeExcretion();
                }
            } else {
                touched = false;
                for (Component component : WorkEnvironmentMain.excretion.getExcretedComponents()){
                    component.setVisible(true);
                    Rectangle absolutecomponentrect = new Rectangle(Math.round((component.getComponentLocation()[0] + component.getbounds().x) * WorkEnvironmentMain.Scale), Math.round((component.getComponentLocation()[1] + component.getbounds().y) * WorkEnvironmentMain.Scale), Math.round(component.getbounds().width * WorkEnvironmentMain.Scale), Math.round(component.getbounds().height * WorkEnvironmentMain.Scale));
                    if (isTouchedComponent(mousePressedPoint.x, mousePressedPoint.y, absolutecomponentrect)){
                        touched = true;
                        break;
                    }
                }
                if (touched && !WorkEnvironmentMain.ShadowedComponents.isEmpty()) {
                    for (Component component : WorkEnvironmentMain.excretion.getExcretedComponents()){
                        int x = MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x;
                        int y = MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y;
                        int dx = Math.round((x - mousePressedPoint.x) / WorkEnvironmentMain.Scale);
                        int dy =  Math.round((y -mousePressedPoint.y) / WorkEnvironmentMain.Scale);
                        component.setComponentLocation(component.getComponentLocation()[0] + dx, component.getComponentLocation()[1] + dy);
                        component.setVisible(true);
                    }
                }
                WorkEnvironmentMain.ShadowedComponents = new ArrayList<ComponentShadow>(Collections.emptyList());
                WorkEnvironmentMain.movingcomponentframe.removeAll();
                for (Component component : WorkEnvironmentMain.excretion.getExcretedComponents()){
                    WorkEnvironmentMain.ShadowedComponents.add(new ComponentShadow(component));
                    WorkEnvironmentMain.excretion.createExcretion();
                }
            }
            WorkEnvironmentMain.excretion.setChoosingRectangle(null);
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
    @Override
    public void mouseMoved(MouseEvent e){
        if (WorkEnvironmentMain.newone.getComponentCount() > 0){
            int x = MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x;
            int y = MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y;
            WorkEnvironmentMain.newComponentShadow.setComponentLocation(Math.round(x / WorkEnvironmentMain.Scale), Math.round(y / WorkEnvironmentMain.Scale));
            WorkEnvironmentMain.incomponentframe.revalidate();
            Main.workenvironment.updateWorkplaceDimensionAndRerenderAll();
        }
    }
    //далее логические вспомогательные методы
    public boolean isTouchedComponent(int dotx, int doty, Rectangle rectangle){
        return rectangle.contains(dotx, doty);
    }
    public boolean isTouchedComponent(Point dot, Rectangle rectangle){
        return rectangle.contains(dot.x, dot.y);
    }
    public boolean isTouchedComponent(Rectangle mouseRectangle, Rectangle componentRectangle){
        return mouseRectangle.intersects(componentRectangle);
    }
    public boolean isTouchedComponent(int dotx, int doty){
        for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
            if (isTouchedComponent(Math.round(dotx * WorkEnvironmentMain.Scale), Math.round(doty * WorkEnvironmentMain.Scale), new Rectangle(Math.round((component.getComponentLocation()[0] + component.getbounds().x) * WorkEnvironmentMain.Scale), Math.round((component.getComponentLocation()[1] + component.getbounds().y) * WorkEnvironmentMain.Scale), Math.round(component.getbounds().width * WorkEnvironmentMain.Scale), Math.round(component.getbounds().height * WorkEnvironmentMain.Scale)))){
                return true;
            }
        }
        return false;
    }
}