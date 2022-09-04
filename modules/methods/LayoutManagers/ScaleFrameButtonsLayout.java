package modules.methods.LayoutManagers;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
public class ScaleFrameButtonsLayout implements LayoutManager{
    private static final int WIDTH = 60; //настроить
    public void addLayoutComponent(String name, Component comp) {}
    public void removeLayoutComponent(Component comp) {}
    public ScaleFrameButtonsLayout(){}
    public Dimension preferredLayoutSize(Container parent) {
        return new Dimension(WIDTH, 50);
    }
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(WIDTH, 50);
    }
    public Dimension maximumLayoutSize(Container container) {
        return new Dimension(WIDTH, 50);
    }
    public void layoutContainer(Container container) {
        Component list[] = container.getComponents();
        if (list.length == 3){
            list[0].setBounds(0, 0, 30, 30);
            list[1].setBounds(30, 0, 30, 15);
            list[2].setBounds(30, 15, 30, 15);
        }
    }
}