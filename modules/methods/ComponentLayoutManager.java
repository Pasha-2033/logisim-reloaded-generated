package modules.methods;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
public class ComponentLayoutManager implements LayoutManager{
    public final int MAX_VALUE = 2147483647;
    public final int MIN_VALUE = 0;
    public Dimension Size;
    public void addLayoutComponent(String name, Component component){}
    public void removeLayoutComponent(Component component) {}
    public ComponentLayoutManager(){
        Size = new Dimension(500, 500);
    }
    public ComponentLayoutManager(Dimension Size){
        this.Size = Size;
    }
    public Dimension minimumLayoutSize(Container container) {
        return new Dimension(MIN_VALUE, MIN_VALUE);
    }
    public Dimension preferredLayoutSize(Container container) {
        return Size;
    }
    public Dimension maximumLayoutSize(Container container) {
        return new Dimension(MAX_VALUE, MAX_VALUE);
    }
    public void layoutContainer(Container container){
        Component list[] = container.getComponents();
        for (int i = 0; i < list.length; i++) {
            list[i].setBounds(0, 0, Size.width, Size.height);
        }
    }
}
