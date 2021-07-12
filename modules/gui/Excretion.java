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
    private Rectangle choosingrectangle = null;
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
        repaint();
    }
    public void removeExcretion(){
        setVisible(false);
        repaint();
    }
    public void setChoosingRectangle(Rectangle choosingrectangle){
        this.choosingrectangle = choosingrectangle;
    }
    public Rectangle getChossingRectangle(){
        return choosingrectangle;
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
            r = new Rectangle(component.getComponentLocation()[0] + component.getbounds().x, component.getComponentLocation()[0] + component.getbounds().y, component.getbounds().width, component.getbounds().height);
            E1 = new int[]{(int) (r.x * WorkEnvironmentMain.Scale), (int) (r.y * WorkEnvironmentMain.Scale)};
            E2 = new int[]{(int) ((r.x + r.width) * WorkEnvironmentMain.Scale), (int) (r.y * WorkEnvironmentMain.Scale)};
            E3 = new int[]{(int) ((r.x + r.width) * WorkEnvironmentMain.Scale), (int) ((r.y + r.height) * WorkEnvironmentMain.Scale)};
            E4 = new int[]{(int) (r.x * WorkEnvironmentMain.Scale), (int) ((r.y + r.height) * WorkEnvironmentMain.Scale)};
            g2d.setColor(ColorList.WHITE[0]);
            int rectScale = (int) (WorkEnvironmentMain.Scale * 4);
            int rectCorect = (int) (WorkEnvironmentMain.Scale * 2);
            g2d.setColor(ColorList.WHITE[0]);
            g2d.fillRect(E1[0] - rectCorect, E1[1] - rectCorect, rectScale, rectScale);
            g2d.fillRect(E2[0] - rectCorect, E2[1] - rectCorect, rectScale, rectScale);
            g2d.fillRect(E3[0] - rectCorect, E3[1] - rectCorect, rectScale, rectScale);
            g2d.fillRect(E4[0] - rectCorect, E4[1] - rectCorect, rectScale, rectScale);
            g2d.setColor(ColorList.BLACK[0]);
            g2d.setStroke(new BasicStroke(1, 1, 1));
            g2d.drawRect(E1[0] - rectCorect, E1[1] - rectCorect, rectScale, rectScale);
            g2d.drawRect(E2[0] - rectCorect, E2[1] - rectCorect, rectScale, rectScale);
            g2d.drawRect(E3[0] - rectCorect, E3[1] - rectCorect, rectScale, rectScale);
            g2d.drawRect(E4[0] - rectCorect, E4[1] - rectCorect, rectScale, rectScale);
        }
        if (choosingrectangle != null){
            g2d.setColor(ColorList.ComponentChooser[0]);
            if (choosingrectangle.width >= 0 && choosingrectangle.height >= 0){
                g2d.fillRect(choosingrectangle.x, choosingrectangle.y, choosingrectangle.width, choosingrectangle.height);
            } else if(choosingrectangle.width >= 0){
                g2d.fillRect(choosingrectangle.x, choosingrectangle.y + choosingrectangle.height, choosingrectangle.width, -choosingrectangle.height);
            } else if(choosingrectangle.height >= 0){
                g2d.fillRect(choosingrectangle.x + choosingrectangle.width, choosingrectangle.y, -choosingrectangle.width, choosingrectangle.height);
            } else {
                g2d.fillRect(choosingrectangle.x + choosingrectangle.width, choosingrectangle.y + choosingrectangle.height, -choosingrectangle.width, -choosingrectangle.height);
            }
            g2d.setColor(ColorList.ComponentChooser[1]);
            g2d.setStroke(new BasicStroke(3, 1, 1));
            if (choosingrectangle.width >= 0 && choosingrectangle.height >= 0){
                g2d.drawRect(choosingrectangle.x, choosingrectangle.y, choosingrectangle.width, choosingrectangle.height);
            } else if(choosingrectangle.width >= 0){
                g2d.drawRect(choosingrectangle.x, choosingrectangle.y + choosingrectangle.height, choosingrectangle.width, -choosingrectangle.height);
            } else if(choosingrectangle.height >= 0){
                g2d.drawRect(choosingrectangle.x + choosingrectangle.width, choosingrectangle.y, -choosingrectangle.width, choosingrectangle.height);
            } else {
                g2d.drawRect(choosingrectangle.x + choosingrectangle.width, choosingrectangle.y + choosingrectangle.height, -choosingrectangle.width, -choosingrectangle.height);
            }
        }
        g2d.dispose();
    }
}