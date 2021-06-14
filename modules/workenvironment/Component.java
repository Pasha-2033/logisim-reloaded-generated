package modules.workenvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
public class Component extends JPanel {
    public String componentname;
    public Icon componenticon = new ImageIcon("путь до стандартной иконки");
    public Component(String name){
        componentname = name;
        this.setOpaque(false);
    }
    public Component(String name, Icon icon){
        componentname = name;
        componenticon = icon;
    }
    public Port[] Ports = {};
    public void setPorts(Port[] ports){
        Ports = ports;
    }
    public int[] ComponentLocation = {0, 0};
    public int[] getComponentLocation(){
        return ComponentLocation;
    }
    public void setComponentLocation(int[] changelocation){
        ComponentLocation = changelocation;
    }
    public void changeComponentLocation(int[] changelocation){
        ComponentLocation[0] += changelocation[0];
        ComponentLocation[1] += changelocation[1];
    }
    public List<String> DrawOder = new ArrayList<>(Collections.emptyList());
    public List<Object[]> LineData = new ArrayList<>(Collections.emptyList());
    public List<Object[]> RectData = new ArrayList<>(Collections.emptyList());
    public List<Object[]> OvalData = new ArrayList<>(Collections.emptyList());
    public List<Object[]> PolyData = new ArrayList<>(Collections.emptyList());
    public List<Object[]> TextData = new ArrayList<>(Collections.emptyList());
    public void setDrawOrder(List<String> Data){
        DrawOder = Data;
    }
    public void setLineData(List<Object[]> Data){
        LineData = Data;
    }
    public void setRectData(List<Object[]> Data){
        RectData = Data;
    }
    public void setOvalData(List<Object[]> Data){
        OvalData = Data;
    }
    public void setPolyData(List<Object[]> Data){
        PolyData = Data;
    }
    public void setTextData(List<Object[]> Data){
        TextData = Data;
    }
    public int rotation = 0;
    public void setrotation(int rotation){
        this.rotation = rotation;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        new DrawComponent(this, g, ScreenLocation, Scale);
    }
    public int[] ScreenLocation;
    public int Scale;
}
