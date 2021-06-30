package modules.methods.LayoutManagers;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
public class FrameScaleLayout implements LayoutManager{
    private final int MAX_VALUE = 2147483647;
    private final int MIN_VALUE = 0;
    public void addLayoutComponent(String name, Component component){}
    public void removeLayoutComponent(Component component) {}
    public FrameScaleLayout(){}
    public Dimension minimumLayoutSize(Container container) {
        return new Dimension(MIN_VALUE, MIN_VALUE);
    }
    public Dimension preferredLayoutSize(Container container) {
        return new Dimension(100, 50);
    }
    public Dimension maximumLayoutSize(Container container) {
        return new Dimension(MAX_VALUE, MAX_VALUE);
    }
    public void layoutContainer(Container container){
        Component list[] = container.getComponents();
        for (int i = 0; i < list.length; i++) {
            list[i].setBounds(0, 0, container.getWidth(), 50);
        }
    }
}