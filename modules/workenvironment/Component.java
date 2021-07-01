package modules.workenvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import modules.languages.language;
import java.awt.Graphics;
public class Component extends JPanel {
    public boolean isSircut = false;
    public List<Component> intercomponentsandsircuts = new ArrayList<Component>(Collections.emptyList());
    public float Scale = 1.0F;
    public int rotation = 0;
    public List<Port> Ports = new ArrayList<Port>(Collections.emptyList());
    public int[] ComponentLocation = {0, 0};
    public List<String> DrawOder = new ArrayList<String>(Collections.emptyList());
    public List<Object[]> LineData = new ArrayList<Object[]>(Collections.emptyList());
    public List<Object[]> RectData = new ArrayList<Object[]>(Collections.emptyList());
    public List<Object[]> OvalData = new ArrayList<Object[]>(Collections.emptyList());
    public List<Object[]> PolyData = new ArrayList<Object[]>(Collections.emptyList());
    public List<Object[]> TextData = new ArrayList<Object[]>(Collections.emptyList());
    public List<Object[]> Atributes = new ArrayList<Object[]>(Collections.emptyList());
    public Icon componenticon;
    public String componentname;
    public Component(){
        setComponent("undefind", new ImageIcon("resources/menuicon/undoicon.png"), 1.0F);
    }
    public Component(String componentname){
        setComponent(componentname, new ImageIcon("resources/menuicon/undoicon.png"), 1.0F);
    }
    public Component(String componentname, Icon componenticon){
        setComponent(componentname, componenticon, 1.0F);
    }
    public Component(String name, Icon componenticon, float Scale){
        setComponent(componentname, componenticon, Scale);
    }
    public void setComponent(String componentname, Icon componenticon, float Scale){
        this.componentname = language.trnslt(componentname);
        this.componenticon = componenticon;
        this.Scale = Scale;
        setOpaque(false);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        new DrawComponent(this, g, Scale);
    }
    //стандартные функции компоента, чтобы при вызове их у компонента не вызывало ошибку
    public void start(){}
    public void step(){}
    public void stream(){}
    public void generatetick(){}
    public void sircutcheck(){
        if (intercomponentsandsircuts.size() > 0){
            isSircut = true;
        }
    }
}
