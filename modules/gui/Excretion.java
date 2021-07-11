package modules.gui;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.BasicStroke;
import modules.workenvironment.WorkEnvironmentMain;
import modules.workenvironment.ColorList;
import modules.workenvironment.Component;
public class Excretion extends JPanel {
    private List<Component> excretedcomponents = new ArrayList<Component>(Collections.emptyList());
    public Excretion(){
        setOpaque(false);
    }
    public void setExcretedComponents(List<Component> excretedcomponents){
        this.excretedcomponents = excretedcomponents;
    }
    public void setExcretedComponents(Component excretedcomponent, int index){
        try {
            excretedcomponents.set(index, excretedcomponent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addExcretedComponents(Component excretedcomponent){
        try {
            excretedcomponents.add(excretedcomponent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addExcretedComponents(Component excretedcomponent, int index){
        try {
            excretedcomponents.add(index, excretedcomponent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<Component> getExcretedComponents(){
        return excretedcomponents;
    }
    public void removeExcretedComponent(int index){
        try {
            excretedcomponents.remove(index);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void removeExcretedComponent(Component component){
        try {
            excretedcomponents.remove(component);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void removeAllExcretedComponents(){
        excretedcomponents = new ArrayList<Component>(Collections.emptyList());
    }
    public void createExcretion(){
        setVisible(true);
    }
    public void removeExcretion(){
        setVisible(false);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int[] E1;
        int[] E2;
        int[] E3;
        int[] E4;
        Rectangle r;
        for (Component component : excretedcomponents){
            r = component.getbounds();
            E1 = new int[]{(int) ((component.getComponentLocation()[0] + r.x) * WorkEnvironmentMain.Scale), (int) ((component.getComponentLocation()[1] + r.y) * WorkEnvironmentMain.Scale)};
            E2 = new int[]{(int) ((component.getComponentLocation()[0] + r.x + r.width) * WorkEnvironmentMain.Scale), (int) ((component.getComponentLocation()[1] + r.y) * WorkEnvironmentMain.Scale)};
            E3 = new int[]{(int) ((component.getComponentLocation()[0] + r.x + r.width) * WorkEnvironmentMain.Scale), (int) ((component.getComponentLocation()[1] + r.y + r.height) * WorkEnvironmentMain.Scale)};
            E4 = new int[]{(int) ((component.getComponentLocation()[0] + r.x) * WorkEnvironmentMain.Scale), (int) ((component.getComponentLocation()[1] + r.y + r.height) * WorkEnvironmentMain.Scale)};
            g2d.setColor(ColorList.WHITE[0]);
            g2d.fillRect(E1[0], E1[1], 5, 5);
            g2d.fillRect(E2[0], E2[1], 5, 5);
            g2d.fillRect(E3[0], E3[1], 5, 5);
            g2d.fillRect(E4[0], E4[1], 5, 5);
            g2d.setStroke(new BasicStroke(WorkEnvironmentMain.Scale, 1, 1));
            g2d.drawRect(E1[0], E1[1], 5, 5);
            g2d.drawRect(E2[0], E2[1], 5, 5);
            g2d.drawRect(E3[0], E3[1], 5, 5);
            g2d.drawRect(E4[0], E4[1], 5, 5);
        }
        g2d.dispose();
    }
}