package modules.methods.LayoutManagers;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
public class FrameScaleLayout implements LayoutManager{
    public void addLayoutComponent(String name, Component component){}
    public void removeLayoutComponent(Component component) {}
    public FrameScaleLayout(){}
    public Dimension minimumLayoutSize(Container container) {
        return new Dimension(0, 0);
    }
    public Dimension preferredLayoutSize(Container container) {
        return new Dimension(container.getWidth(), 30);
    }
    public Dimension maximumLayoutSize(Container container) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    public void layoutContainer(Container container){
        Component list[] = container.getComponents();
        for (int i = 0; i < list.length; i++) {
            list[i].setBounds(0, 0, container.getWidth(), 30);
        }
    }
}