package modules.workenvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
public class Component extends JPanel {
    public int Scale;
    public int rotation = 0;
    public int[] ScreenLocation;
    public Port[] Ports = {};
    public int[] ComponentLocation = {0, 0};
    public List<String> DrawOder = new ArrayList<>(Collections.emptyList());
    public List<Object[]> LineData = new ArrayList<>(Collections.emptyList());
    public List<Object[]> RectData = new ArrayList<>(Collections.emptyList());
    public List<Object[]> OvalData = new ArrayList<>(Collections.emptyList());
    public List<Object[]> PolyData = new ArrayList<>(Collections.emptyList());
    public List<Object[]> TextData = new ArrayList<>(Collections.emptyList());
    public Icon componenticon = new ImageIcon("resourses/menuicon/undoicon.png");
    public String componentname = "undefind";
    public Component(){}
    public Component(String name){
        componentname = name;
        setOpaque(false);
    }
    public Component(String name, Icon icon){
        componentname = name;
        componenticon = icon;
        setOpaque(false);
    }
    public void changeComponentLocation(int[] changelocation){
        ComponentLocation[0] += changelocation[0];
        ComponentLocation[1] += changelocation[1];
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        new DrawComponent(this, g, ScreenLocation, Scale);
    }
    //стандартные функции компоента, чтобы при вызове их у компонента не вызывало ошибку
    public void step(){}
}
