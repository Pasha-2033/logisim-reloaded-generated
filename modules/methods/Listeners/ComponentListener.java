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
import modules.basecomponent.wire;
import modules.methods.Parsers.PortParser;
import modules.workenvironment.Component;
import modules.workenvironment.ComponentShadow;
import modules.workenvironment.Port;
import modules.workenvironment.WorkEnvironmentMain;
import modules.workenvironment.SettingsManager;
import mainclassfolder.Main;
public class ComponentListener extends MouseInputAdapter{
    public static Point mousePressedPoint = new Point(0, 0);
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x;
        int y = MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y;
        WorkEnvironmentMain.movingcomponentframe.setVisible(false);
        WorkEnvironmentMain.ShadowedComponents = new ArrayList<ComponentShadow>(Collections.emptyList());
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
                    //WorkEnvironmentMain.ShadowedComponents.add(new ComponentShadow(component));
                    WorkEnvironmentMain.excretion.createExcretion();
                    touched = true;
                }
            }
            if (!touched){
                WorkEnvironmentMain.excretion.removeAllExcretedComponents();
                WorkEnvironmentMain.excretion.removeExcretion();
            }
        } else if (SwingUtilities.isRightMouseButton(e)){
            for (Component component : WorkEnvironmentMain.excretion.getExcretedComponents()){
                component.setVisible(true);
            }
        } else {
            for (Component component : WorkEnvironmentMain.excretion.getExcretedComponents()){
                component.setVisible(true);
            }
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
            boolean fromport = false;
            int x = MouseInfo.getPointerInfo().getLocation().x - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().x;
            int y = MouseInfo.getPointerInfo().getLocation().y - WorkEnvironmentMain.incomponentframe.getLocationOnScreen().y;
            for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
                for (Port port : component.getPorts()){
                    //доделать условие
                    if (Math.abs(mousePressedPoint.x / WorkEnvironmentMain.Scale + port.location[0] - component.getRotationFlag()[0] - component.getComponentLocation()[0]) < 5 && Math.abs(mousePressedPoint.y / WorkEnvironmentMain.Scale + port.location[1] - component.getRotationFlag()[1] - component.getComponentLocation()[1]) < 5) {
                        fromport = true;
                        break;
                    }
                }
                if (fromport) break;
            }
            if (!fromport){
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
                        if (component.isgrided()){
                            int xx = component.getComponentBase()[0] + dx;
                            int yy = component.getComponentBase()[1] + dy;
                            component.setComponentLocation(xx - (xx % 10), yy - (yy % 10));
                        } else {
                            component.setComponentLocation(component.getComponentBase()[0] + dx, component.getComponentBase()[1] + dy);
                        }
                        component.repaint();
                    }
                } else {
                    WorkEnvironmentMain.excretion.setChoosingRectangle(new Rectangle(mousePressedPoint.x, mousePressedPoint.y, x - mousePressedPoint.x, y - mousePressedPoint.y));
                    WorkEnvironmentMain.excretion.createExcretion();
                    WorkEnvironmentMain.excretion.removeAllExcretedComponents();
                }
            } else {
                //заменить везде mousePressedPoint.x на WorkEnvironmentMain.portforwireshadow.location[0]
                //рисуем тень провода
                if (WorkEnvironmentMain.wireShadow.length != 2){
                    WorkEnvironmentMain.wireShadow = new wire[]{new wire(), new wire()};
                }
                WorkEnvironmentMain.wireShadowpanel.removeAll();
                int dx = Math.round((x - mousePressedPoint.x) / WorkEnvironmentMain.Scale);
                int dy = Math.round((y - mousePressedPoint.y) / WorkEnvironmentMain.Scale);
                //проверка wireoder
                if (Math.abs(dx) < 5 & Math.abs(dy) < 5){
                    double angle = Math.toDegrees(Math.atan2(dy, dx));
                    if ((angle >= 0 && angle <= 45) || angle <= -135 || angle >= 135 || (angle >= -45 && angle <= 0)){
                        WorkEnvironmentMain.wireoder = true;
                    } else {
                        WorkEnvironmentMain.wireoder = false;
                    }
                    //переопределяем начала координат для проводов, а именно от ближащего порта
                    WorkEnvironmentMain.portforwireshadow = PortParser.getnearestPortCS(mousePressedPoint.x, mousePressedPoint.y);
                }
                if (SettingsManager.griding()){
                    if (WorkEnvironmentMain.wireoder){
                        if (dx >= 0){
                            WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, dx - dx % 10, 0);
                            WorkEnvironmentMain.wireShadow[0].setComponentLocation(Math.round(mousePressedPoint.x / WorkEnvironmentMain.Scale), Math.round(mousePressedPoint.y / WorkEnvironmentMain.Scale));
                            WorkEnvironmentMain.wireShadow[0].setVisible(true);
                        } else {
                            if (dx % 10 != 0){
                                WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, Math.abs(dx - (10 + dx % 10)), 0);
                                WorkEnvironmentMain.wireShadow[0].setComponentLocation(Math.round(x / WorkEnvironmentMain.Scale) - Math.round(x / WorkEnvironmentMain.Scale) % 10, Math.round(mousePressedPoint.y / WorkEnvironmentMain.Scale));
                                WorkEnvironmentMain.wireShadow[0].setVisible(true);
                            } else {
                                WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, Math.abs(dx), 0);
                                WorkEnvironmentMain.wireShadow[0].setComponentLocation(Math.round(x / WorkEnvironmentMain.Scale) - Math.round(x / WorkEnvironmentMain.Scale) % 10, Math.round(mousePressedPoint.y / WorkEnvironmentMain.Scale));
                                WorkEnvironmentMain.wireShadow[0].setVisible(true);
                            }
                        }
                        if (dy >= 0){
                            WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, 0, dy - dy % 10);
                            WorkEnvironmentMain.wireShadow[1].setComponentLocation(Math.round(x / WorkEnvironmentMain.Scale) - Math.round(x / WorkEnvironmentMain.Scale) % 10, Math.round(mousePressedPoint.y / WorkEnvironmentMain.Scale));
                            WorkEnvironmentMain.wireShadow[1].setVisible(true);
                        } else {
                            WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, 0, Math.abs(dy) - Math.abs(dy) % 10);
                            WorkEnvironmentMain.wireShadow[1].setComponentLocation(Math.round(x / WorkEnvironmentMain.Scale) - Math.round(x / WorkEnvironmentMain.Scale) % 10, Math.round(mousePressedPoint.y / WorkEnvironmentMain.Scale) - (Math.abs(dy) - Math.abs(dy) % 10));
                            WorkEnvironmentMain.wireShadow[1].setVisible(true);
                        }
                    } else {
                        if (dy >= 0){
                            WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, 0, dy - dy % 10);
                            WorkEnvironmentMain.wireShadow[0].setComponentLocation(Math.round(mousePressedPoint.x / WorkEnvironmentMain.Scale), Math.round(mousePressedPoint.y / WorkEnvironmentMain.Scale));
                            WorkEnvironmentMain.wireShadow[0].setVisible(true);
                        } else {
                            WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, 0, Math.abs(dy - dy % 10));
                            WorkEnvironmentMain.wireShadow[0].setComponentLocation(Math.round(mousePressedPoint.x / WorkEnvironmentMain.Scale), Math.round(mousePressedPoint.y / WorkEnvironmentMain.Scale) - Math.round(mousePressedPoint.y / WorkEnvironmentMain.Scale) % 10 - Math.abs(dy - dy % 10));
                            WorkEnvironmentMain.wireShadow[0].setVisible(true);
                        }
                        if (dx >= 0){
                            if (dy >= 0){
                                WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, dx - dx % 10, 0);
                                WorkEnvironmentMain.wireShadow[1].setComponentLocation(Math.round(mousePressedPoint.x / WorkEnvironmentMain.Scale), Math.round(y / WorkEnvironmentMain.Scale) -  Math.round(y / WorkEnvironmentMain.Scale) % 10);
                                WorkEnvironmentMain.wireShadow[1].setVisible(true);
                            } else {
                                WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, dx - dx % 10, 0);
                                WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0], WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1]);
                                WorkEnvironmentMain.wireShadow[1].setVisible(true);
                            }
                        } else {
                            if (dy >= 0){
                                WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, Math.abs(dx - dx % 10), 0);
                                WorkEnvironmentMain.wireShadow[1].setComponentLocation(Math.round(mousePressedPoint.x / WorkEnvironmentMain.Scale) - Math.abs(dx - dx % 10), Math.round(y / WorkEnvironmentMain.Scale) -  Math.round(y / WorkEnvironmentMain.Scale) % 10);
                                WorkEnvironmentMain.wireShadow[1].setVisible(true);
                            } else {
                                WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, Math.abs(dx - dx % 10), 0);
                                WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0] - Math.abs(dx - dx % 10), WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1]);
                                WorkEnvironmentMain.wireShadow[1].setVisible(true);
                            }
                        }
                    }
                } else {
                    if (WorkEnvironmentMain.wireoder){
                        if (dx >= 0){
                            WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, Math.abs(dx), 0);
                            WorkEnvironmentMain.wireShadow[0].setComponentLocation(Math.round(mousePressedPoint.x / WorkEnvironmentMain.Scale), Math.round(mousePressedPoint.y / WorkEnvironmentMain.Scale));
                            WorkEnvironmentMain.wireShadow[0].setVisible(true);
                        } else {
                            WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, Math.abs(dx), 0);
                            WorkEnvironmentMain.wireShadow[0].setComponentLocation(Math.round(x / WorkEnvironmentMain.Scale), Math.round(mousePressedPoint.y / WorkEnvironmentMain.Scale));
                            WorkEnvironmentMain.wireShadow[0].setVisible(true);
                        }
                        if (dy >= 0){
                            WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, 0, Math.abs(dy));
                            WorkEnvironmentMain.wireShadow[1].setComponentLocation(Math.round(mousePressedPoint.x / WorkEnvironmentMain.Scale) + dx, Math.round(mousePressedPoint.y / WorkEnvironmentMain.Scale));
                            WorkEnvironmentMain.wireShadow[1].setVisible(true);
                        } else {
                            WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, 0, Math.abs(dy));
                            WorkEnvironmentMain.wireShadow[1].setComponentLocation(Math.round(mousePressedPoint.x / WorkEnvironmentMain.Scale) + dx, Math.round(y / WorkEnvironmentMain.Scale));
                            WorkEnvironmentMain.wireShadow[1].setVisible(true);
                        }
                    } else {
                        if (dy >= 0){
                            WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, 0, dy);
                            WorkEnvironmentMain.wireShadow[0].setComponentLocation(Math.round(mousePressedPoint.x / WorkEnvironmentMain.Scale), Math.round(mousePressedPoint.y / WorkEnvironmentMain.Scale));
                            WorkEnvironmentMain.wireShadow[0].setVisible(true);
                        } else {
                            WorkEnvironmentMain.wireShadow[0] = new wire(0, 0, 0, Math.abs(dy));
                            WorkEnvironmentMain.wireShadow[0].setComponentLocation(Math.round(mousePressedPoint.x / WorkEnvironmentMain.Scale), Math.round(mousePressedPoint.y / WorkEnvironmentMain.Scale) - Math.abs(dy));
                            WorkEnvironmentMain.wireShadow[0].setVisible(true);
                        }
                        if (dx >= 0){
                            if (dy >= 0){
                                WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, dx, 0);
                                WorkEnvironmentMain.wireShadow[1].setComponentLocation(Math.round(mousePressedPoint.x / WorkEnvironmentMain.Scale), Math.round(y / WorkEnvironmentMain.Scale));
                                WorkEnvironmentMain.wireShadow[1].setVisible(true);
                            } else {
                                WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, dx, 0);
                                WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0], WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1]);
                                WorkEnvironmentMain.wireShadow[1].setVisible(true);
                            }
                        } else {
                            if (dy >= 0){
                                WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, Math.abs(dx), 0);
                                WorkEnvironmentMain.wireShadow[1].setComponentLocation(Math.round(mousePressedPoint.x / WorkEnvironmentMain.Scale) - Math.abs(dx), Math.round(y / WorkEnvironmentMain.Scale));
                                WorkEnvironmentMain.wireShadow[1].setVisible(true);
                            } else {
                                WorkEnvironmentMain.wireShadow[1] = new wire(0, 0, Math.abs(dx), 0);
                                WorkEnvironmentMain.wireShadow[1].setComponentLocation(WorkEnvironmentMain.wireShadow[0].getComponentLocation()[0] - Math.abs(dx), WorkEnvironmentMain.wireShadow[0].getComponentLocation()[1]);
                                WorkEnvironmentMain.wireShadow[1].setVisible(true);
                            }
                        }
                    }
                }
                WorkEnvironmentMain.wireShadowpanel.add(WorkEnvironmentMain.wireShadow[0]);
                WorkEnvironmentMain.wireShadowpanel.add(WorkEnvironmentMain.wireShadow[1]);
                WorkEnvironmentMain.wireShadowpanel.revalidate();
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
            boolean fromport = false;
            for (Component component : WorkEnvironmentMain.currentSircut.getintercomponentsandsircuts()){
                for (Port port : component.getPorts()){
                    if (Math.abs(mousePressedPoint.x + port.location[0] - component.getRotationFlag()[0] - component.getComponentLocation()[0]) < 5 * WorkEnvironmentMain.Scale && Math.abs(mousePressedPoint.y + port.location[1] - component.getRotationFlag()[1] - component.getComponentLocation()[1]) < 5 * WorkEnvironmentMain.Scale) {
                        fromport = true;
                        break;
                    }
                }
                if (fromport) break;
            }
            if (!fromport){
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
                        WorkEnvironmentMain.ShadowedComponents = new ArrayList<ComponentShadow>(Collections.emptyList());
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
                            if (component.isgrided()){
                                int xx = component.getComponentLocation()[0] + dx;
                                int yy = component.getComponentLocation()[1] + dy;
                                component.setComponentLocation(xx - (xx % 10), yy - (yy % 10));
                            } else {
                                component.setComponentLocation(component.getComponentLocation()[0] + dx, component.getComponentLocation()[1] + dy);
                            }
                            component.setVisible(true);
                        }
                    }
                    WorkEnvironmentMain.ShadowedComponents = new ArrayList<ComponentShadow>(Collections.emptyList());
                    WorkEnvironmentMain.movingcomponentframe.removeAll();
                    WorkEnvironmentMain.excretion.createExcretion();
                }
                WorkEnvironmentMain.excretion.setChoosingRectangle(null);
            } else {
                //создать провод, удалить тень
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